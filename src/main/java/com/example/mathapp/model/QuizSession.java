package com.example.mathapp.model;

import java.util.List;

/**
 * 试卷会话类，用于管理当前用户的答题状态，包括题目列表、当前题目索引和用户的答案。
 */
public class QuizSession {

    private List<Question> questions; // 题目列表
    private int currentQuestionIndex; // 当前题目的索引
    private Double[] userAnswers;     // 用户答案数组

    /**
     * 构造函数，初始化题目列表并设置当前题目索引为0。
     *
     * @param questions 题目列表
     */
    public QuizSession(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.userAnswers = new Double[questions.size()];
    }

    /**
     * 获取当前题目
     *
     * @return 当前题目
     */
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    /**
     * 获取当前题目索引
     *
     * @return 当前题目索引
     */
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    /**
     * 设置用户的答案
     *
     * @param answer 用户答案
     */
    public void setUserAnswer(Double answer) {
        userAnswers[currentQuestionIndex] = answer;
    }

    /**
     * 获取用户的答案数组
     *
     * @return 用户答案数组
     */
    public Double[] getUserAnswers() {
        return userAnswers;
    }

    /**
     * 跳转到下一题
     */
    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    /**
     * 跳转到上一题
     */
    public void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
        }
    }

    /**
     * 判断是否是最后一题
     *
     * @return 如果是最后一题返回 true，否则返回 false
     */
    public boolean isLastQuestion() {
        return currentQuestionIndex == questions.size() - 1;
    }

    /**
     * 获取题目总数
     *
     * @return 题目总数
     */
    public int getTotalQuestions() {
        return questions.size();
    }

    /**
     * 获取所有题目
     *
     * @return 题目列表
     */
    public List<Question> getQuestions() {
        return questions;
    }
}
