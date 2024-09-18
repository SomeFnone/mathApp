package com.example.mathapp.model;

/**
 * 试卷生成请求封装类。
 */
public class QuizRequest {

    private String grade;
    private int questionCount;

    // 获取年级
    public String getGrade() {
        return grade;
    }

    // 设置年级
    public void setGrade(String grade) {
        this.grade = grade;
    }

    // 获取题目数量
    public int getQuestionCount() {
        return questionCount;
    }

    // 设置题目数量
    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
