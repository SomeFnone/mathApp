package com.example.mathapp.service;

import com.example.mathapp.model.Question;
/**
 * 高中数学题目生成器，实现了 QuestionGenerator 接口。
 */
public class HighQuestionGenerator implements QuestionGenerator {

    @Override
    public Question[] generateQuestions(int count) {
        // 生成高中数学题目的逻辑
        return new Question[count];
    }
}
