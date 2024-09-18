package com.example.mathapp.controller;

import com.example.mathapp.model.QuizRequest;
import com.example.mathapp.model.AnswerRequest;
import com.example.mathapp.model.ScoreResponse;
import com.example.mathapp.service.QuestionGenerator;
import com.example.mathapp.service.QuestionGeneratorFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 试卷控制器，处理试卷生成、答题和分数计算请求。
 */
@Controller
public class QuizController {

    /**
     * 显示试卷生成页面。
     *
     * @return 试卷生成页面的视图名称
     */
    @GetMapping("/quiz")
    public String quiz(Model model) {
        model.addAttribute("grades", new String[]{"小学", "初中", "高中"});
        return "quiz";
    }

    /**
     * 处理试卷生成请求。
     *
     * @param quizRequest 试卷生成请求参数
     * @param model       模型对象，用于传递数据到视图
     * @return 试卷页面的视图名称
     */
    @PostMapping("/generate-quiz")
    public String generateQuiz(@ModelAttribute QuizRequest quizRequest, Model model) {
        QuestionGenerator generator = QuestionGeneratorFactory.createGenerator(quizRequest.getGrade());
        model.addAttribute("questions", generator.generateQuestions(quizRequest.getQuestionCount()));
        return "quiz-questions";
    }

    /**
     * 处理答题请求。
     *
     * @param answerRequest 答题请求参数
     * @param model         模型对象，用于传递数据到视图
     * @return 分数页面的视图名称
     */
    @PostMapping("/submit-answers")
    public String submitAnswers(@ModelAttribute AnswerRequest answerRequest, Model model) {
        // 这里应该包含评分的逻辑
        int score = calculateScore(answerRequest);
        ScoreResponse scoreResponse = new ScoreResponse();
        scoreResponse.setScore(score);
        model.addAttribute("scoreResponse", scoreResponse);
        return "score";
    }

    /**
     * 计算分数的简单示例方法。
     *
     * @param answerRequest 答题请求参数
     * @return 计算出的分数
     */
    private int calculateScore(AnswerRequest answerRequest) {
        // 假设每题10分，实际实现中应根据正确答案计算得分
        return answerRequest.getAnswers().length * 10;
    }
}
