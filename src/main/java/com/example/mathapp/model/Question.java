package com.example.mathapp.model;

/**
 * 数学题目实体类。
 */
public class Question {

    private String questionText;
    private String[] options;
    private String correctAnswer;

    // 构造函数
    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // 获取题目文本
    public String getQuestionText() {
        return questionText;
    }

    // 设置题目文本
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // 获取选项
    public String[] getOptions() {
        return options;
    }

    // 设置选项
    public void setOptions(String[] options) {
        this.options = options;
    }

    // 获取正确答案
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // 设置正确答案
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
