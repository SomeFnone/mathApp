package com.example.mathapp.service;

import com.example.mathapp.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 小学数学题目生成器类，实现了 QuestionGenerator 接口，生成小学难度的数学题目。
 */
public class ElementaryQuestionGenerator implements QuestionGenerator {
    private Random random = new Random();

    /**
     * 生成指定数量的小学数学题目。
     *
     * @param count 题目数量
     * @return 生成的题目数组
     */
    @Override
    public Question[] generateQuestions(int count) {
        Question[] questiones = new Question[count];
        List<String> questions = new ArrayList<>();
        int i = 0;
        while (questions.size() < count) {
            String question = generateSimpleMathProblem();
            // 查重，避免重复题目
            if (!questions.contains(question)) {
                questions.add(question);
                String questionText = ExpressionEvaluator.toMarkdown(question);
                Double correctAnswer = ExpressionEvaluator.evaluateExpression(question);
                Double[] options = ExpressionEvaluator.generateOptions(correctAnswer);
                questiones[i] = new Question(questionText, options, correctAnswer);
                i++;
            }
        }
        return questiones;
    }

    /**
     * 生成简单的小学数学题目，包括基本的加减乘除运算。
     *
     * @return 生成的题目字符串
     */
    public String generateSimpleMathProblem() {
        int operatorsCount = random.nextInt(2) + 1;
        StringBuilder question = new StringBuilder();
        for (int i = 0; i < operatorsCount; i++) {
            char operator = "+-*/".charAt(random.nextInt(4));
            if (random.nextBoolean()) {
                question.append(random.nextInt(100) + 1).append(" ").append(operator).append(" ");
            } else {
                question.append("(").append(random.nextInt(100) + 1)
                        .append(" ").append(operator).append(" ")
                        .append(random.nextInt(100) + 1).append(")").append(" ").append(operator).append(" ");
            }
        }
        question.append(random.nextInt(100) + 1);
        return question.toString();
    }
}
