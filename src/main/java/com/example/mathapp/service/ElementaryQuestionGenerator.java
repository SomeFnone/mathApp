package com.example.mathapp.service;

import com.example.mathapp.model.Question;
/**
 * 小学数学题目生成器，实现了 QuestionGenerator 接口。
 */
public class ElementaryQuestionGenerator implements QuestionGenerator {

    @Override
    public Question[] generateQuestions(int count) {
        // 生成小学数学题目的逻辑
        return new Question[count];
    }
}
