package com.example.mathapp.model;

/**
 * 试卷生成请求类，封装用户生成试卷时的请求数据。
 */
public class QuizRequest {

    private String grade; // 用户选择的年级
    private int questionCount; // 用户要求生成的题目数量

    /**
     * 获取用户选择的年级
     *
     * @return 年级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置用户选择的年级
     *
     * @param grade 年级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取用户要求生成的题目数量
     *
     * @return 题目数量
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /**
     * 设置用户要求生成的题目数量
     *
     * @param questionCount 题目数量
     */
    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
