package com.example.mathapp.service;

/**
 * 问题生成器工厂类，根据年级创建相应的题目生成器。
 */
public class QuestionGeneratorFactory {

    /**
     * 创建指定年级的题目生成器。
     *
     * @param grade 年级（小学、初中、高中）
     * @return 对应的题目生成器实例
     */
    public static QuestionGenerator createGenerator(String grade) {
        switch (grade) {
            case "小学":
                return new ElementaryQuestionGenerator();
            case "初中":
                return new MiddleQuestionGenerator();
            case "高中":
                return new HighQuestionGenerator();
            default:
                throw new IllegalArgumentException("未知的年级: " + grade);
        }
    }
}
