package com.example.mathapp.service;

import com.example.mathapp.model.Question;
/**
 * 初中数学题目生成器，实现了 QuestionGenerator 接口。
 */
public class MiddleQuestionGenerator implements QuestionGenerator {

    @Override
    public Question[] generateQuestions(int count) {
        // 生成初中数学题目的逻辑
        return new Question[count];
    }
}
