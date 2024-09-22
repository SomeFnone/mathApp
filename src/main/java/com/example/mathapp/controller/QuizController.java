package com.example.mathapp.controller;

import com.example.mathapp.model.*;
import com.example.mathapp.service.QuestionGenerator;
import com.example.mathapp.service.QuestionGeneratorFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 试卷控制器类，处理试卷生成、答题导航和答案提交的请求。
 */
@Controller
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 处理初始页面的GET请求，展示可选择的年级。
     *
     * @param model 传递到视图的数据
     * @return 返回quiz.html页面
     */
    @GetMapping("/quiz")
    public String quiz(Model model) {
        model.addAttribute("grades", new String[]{"小学", "初中", "高中"});
        return "quiz";
    }

    /**
     * 处理试卷生成的POST请求，根据用户选择的年级和题目数量生成题目。
     *
     * @param quizRequest 包含用户的选择（年级和题目数量）
     * @param model       传递到视图的数据
     * @param session     当前用户会话
     * @return 返回quiz-questions.html页面，展示生成的题目
     */
    @PostMapping("/generate-quiz")
    public String generateQuiz(@ModelAttribute QuizRequest quizRequest, Model model, HttpSession session) {
        QuestionGenerator generator = QuestionGeneratorFactory.createGenerator(quizRequest.getGrade());
        List<Question> questions = List.of(generator.generateQuestions(quizRequest.getQuestionCount()));
        QuizSession quizSession = new QuizSession(questions);
        session.setAttribute("quizSession", quizSession);
        model.addAttribute("question", quizSession.getCurrentQuestion());
        model.addAttribute("currentIndex", quizSession.getCurrentQuestionIndex());
        model.addAttribute("totalQuestions", quizSession.getTotalQuestions());
        model.addAttribute("userAnswers", quizSession.getUserAnswers());
        return "quiz-questions";
    }

    /**
     * 处理导航题目请求，支持上一题、下一题的切换，并保存用户答案。
     *
     * @param action 用户选择的操作（next, previous, submit）
     * @param answer 用户提交的当前题目答案
     * @param model  传递到视图的数据
     * @param session 当前用户会话
     * @return 返回quiz-questions.html页面，展示切换后的题目
     */
    @PostMapping("/navigate-question")
    public String navigateQuestion(@RequestParam String action, @RequestParam(required = false) Double answer, Model model, HttpSession session) {
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz";
        }

        // Log the incoming request parameters
        logger.debug("Action: {}", action);
        logger.debug("Answer: {}", answer);

        if (answer != null) {
            logger.debug("Setting answer for current question: {}", answer);
            quizSession.setUserAnswer(answer);
        }

        // 如果用户点击了“提交”按钮，检查是否有未回答的题目
        if ("submit".equals(action)) {
            Double[] userAnswers = quizSession.getUserAnswers();
            boolean hasUnansweredQuestions = false;

            for (Double userAnswer : userAnswers) {
                if (userAnswer == null) {
                    hasUnansweredQuestions = true;
                    break;
                }
            }

            if (hasUnansweredQuestions) {
                model.addAttribute("errorMessage", "您还有未回答的题目，请完成所有题目后再提交。");
                model.addAttribute("question", quizSession.getCurrentQuestion());
                model.addAttribute("currentIndex", quizSession.getCurrentQuestionIndex());
                model.addAttribute("totalQuestions", quizSession.getTotalQuestions());
                model.addAttribute("userAnswers", quizSession.getUserAnswers());
                return "quiz-questions";
            }

            // 确保最后一题的答案在提交前被保存
            if (quizSession.getCurrentQuestionIndex() == quizSession.getTotalQuestions() - 1 && answer != null) {
                quizSession.setUserAnswer(answer);
            }
            return "redirect:/submit-answers";
        }

        // 处理"下一题"和"上一题"
        if ("next".equals(action)) {
            quizSession.nextQuestion();
        } else if ("previous".equals(action)) {
            quizSession.previousQuestion();
        }

        model.addAttribute("question", quizSession.getCurrentQuestion());
        model.addAttribute("currentIndex", quizSession.getCurrentQuestionIndex());
        model.addAttribute("totalQuestions", quizSession.getTotalQuestions());
        model.addAttribute("userAnswers", quizSession.getUserAnswers());
        return "quiz-questions";
    }

    /**
     * 处理用户提交答案的请求，并计算得分。
     *
     * @param model         传递到视图的数据
     * @param session       当前用户会话
     * @return 返回score.html页面，展示得分
     */
    @GetMapping("/submit-answers")
    public String submitAnswers(Model model, HttpSession session) {
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz";
        }

        int score = 0;
        Double[] correctAnswers = quizSession.getQuestions().stream()
                .map(Question::getCorrectAnswer)
                .toArray(Double[]::new);

        Double[] userAnswers = quizSession.getUserAnswers();

        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i].equals(userAnswers[i])) {
                score += 10;
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("totalQuestions", correctAnswers.length);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("userAnswers", userAnswers);
        model.addAttribute("questions", quizSession.getQuestions());
        return "score";
    }

    /**
     * 处理错题查看的请求，展示错题及用户选择的答案。
     *
     * @param model   传递到视图的数据
     * @param session 当前用户会话
     * @return 返回error-questions.html页面，展示错题
     */
    @GetMapping("/check-errors")
    public String checkErrors(Model model, HttpSession session) {
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz";
        }

        List<Question> questions = quizSession.getQuestions();
        Double[] userAnswers = quizSession.getUserAnswers();
        List<Question> errorQuestions = new ArrayList<>();
        List<Double> correctAnswers = new ArrayList<>();
        List<Double> userWrongAnswers = new ArrayList<>();

        // 只筛选出用户答错的题目
        for (int i = 0; i < questions.size(); i++) {
            if (!questions.get(i).getCorrectAnswer().equals(userAnswers[i])) {
                errorQuestions.add(questions.get(i));
                correctAnswers.add(questions.get(i).getCorrectAnswer());
                userWrongAnswers.add(userAnswers[i]);
            }
        }

        // 将错题信息保存到session中
        session.setAttribute("errorQuestions", errorQuestions);
        session.setAttribute("correctAnswers", correctAnswers);
        session.setAttribute("userWrongAnswers", userWrongAnswers);

        // 初始化 currentIndex 并传递到视图
        session.setAttribute("currentIndex", 0);

        model.addAttribute("errorQuestions", errorQuestions);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("userWrongAnswers", userWrongAnswers);
        model.addAttribute("currentIndex", 0);
        model.addAttribute("totalErrorQuestions", errorQuestions.size());

        return "error-questions";
    }

    /**
     * 处理错题页面的导航，跳转到上一题或下一题。
     *
     * @param action  用户的导航操作，可能是 "next" 或 "previous"
     * @param model   用于传递数据到视图
     * @param session 当前用户会话，包含答题信息
     * @return 返回错误题目页面的视图名称
     */
    @PostMapping("/navigate-error")
    public String navigateError(@RequestParam String action, Model model, HttpSession session) {
        QuizSession quizSession = (QuizSession) session.getAttribute("quizSession");
        if (quizSession == null) {
            return "redirect:/quiz";
        }

        // 从session中获取错题和currentIndex,正确答案和用户答案
        Integer currentIndex = (Integer) session.getAttribute("currentIndex");
        List<Question> errorQuestions = (List<Question>) session.getAttribute("errorQuestions");
        List<Double> correctAnswers = (List<Double>) session.getAttribute("correctAnswers");
        List<Double> userWrongAnswers = (List<Double>) session.getAttribute("userWrongAnswers");
        // 防止currentIndex为空
        if (currentIndex == null) {
            currentIndex = 0;
        }

        // 根据用户的导航操作调整currentIndex
        if ("next".equals(action) && currentIndex < errorQuestions.size() - 1) {
            currentIndex++;
        } else if ("previous".equals(action) && currentIndex > 0) {
            currentIndex--;
        }

        // 将更新后的currentIndex存储到session
        session.setAttribute("currentIndex", currentIndex);

        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("errorQuestions", errorQuestions);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("userWrongAnswers", userWrongAnswers);
        model.addAttribute("totalErrorQuestions", errorQuestions.size());

        return "error-questions";
    }


}
