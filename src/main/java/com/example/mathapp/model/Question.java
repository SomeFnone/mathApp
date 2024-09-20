package com.example.mathapp.model;

/**
 * 数学题目实体类，包含题目文本、选项和正确答案。
 */
public class Question {

    private String questionText; // 题目文本
    private Double[] options;    // 题目选项数组
    private Double correctAnswer; // 正确答案

    /**
     * 构造函数
     *
     * @param questionText 题目文本
     * @param options      选项数组
     * @param correctAnswer 正确答案
     */
    public Question(String questionText, Double[] options, Double correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    /**
     * 获取题目文本
     *
     * @return 题目文本
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * 设置题目文本
     *
     * @param questionText 题目文本
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * 获取题目选项数组
     *
     * @return 选项数组
     */
    public Double[] getOptions() {
        return options;
    }

    /**
     * 设置题目选项数组
     *
     * @param options 选项数组
     */
    public void setOptions(Double[] options) {
        this.options = options;
    }

    /**
     * 获取正确答案
     *
     * @return 正确答案
     */
    public Double getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * 设置正确答案
     *
     * @param correctAnswer 正确答案
     */
    public void setCorrectAnswer(Double correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
