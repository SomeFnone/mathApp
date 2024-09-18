package com.example.mathapp.model;

/**
 * 答题请求封装类。
 */
public class AnswerRequest {

    private String[] answers;

    // 获取答案数组
    public String[] getAnswers() {
        return answers;
    }

    // 设置答案数组
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
