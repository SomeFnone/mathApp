package com.example.mathapp.service;

import com.example.mathapp.model.Question;
/**
 * 问题生成器接口，定义了生成数学题目的方法。
 */
public interface QuestionGenerator {

    /**
     * 生成指定数量的数学题目。
     *
     * @param count 题目数量
     * @return 生成的题目数组
     */
    Question[] generateQuestions(int count);
}
