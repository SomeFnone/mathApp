package com.example.mathapp.service;

import com.example.mathapp.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 初中数学题目生成器，实现了 QuestionGenerator 接口。
 */
public class MiddleQuestionGenerator implements QuestionGenerator {
    private Random random = new Random();

    @Override
    public Question[] generateQuestions(int count) {
        Question[] questiones = new Question[count];
        List<String> questions = new ArrayList<>();
        int i = 0;
        while (questions.size() < count) {
            String question = generateMiddleSchoolMathProblem();
            // 查重：如果题目不在已有题目中，添加到题目列表
            if (!questions.contains(question)) {
                questions.add(question); // 避免后续重复
                String questionText = ExpressionEvaluator.toMarkdown(question);
                Double correctAnswer = ExpressionEvaluator.evaluateExpression(question);
                Double[] options = ExpressionEvaluator.generateOptions(correctAnswer);
                questiones[i] = new Question(questionText, options, correctAnswer);
                i++;
            }
        }
        return questiones;
    }

    // 生成初中数学问题
    public String generateMiddleSchoolMathProblem() {
        int operatorsCount = random.nextInt(2)+1;
        StringBuilder question = new StringBuilder();
        for (int i = 0; i < operatorsCount; i++) {
            char operator = "+-*/".charAt(random.nextInt(4));
            if(random.nextBoolean())question.append(random.nextBoolean() ?"√" + (random.nextInt(100) + 1):(random.nextInt(100) + 1 )+ "^2").append(" ").append(operator).append(" ");
            else question.append("(").append(random.nextBoolean() ?"√" + (random.nextInt(100) + 1):(random.nextInt(100) + 1 )+ "^2").append(" ").append(operator).append(" ").append(random.nextInt(100) + 1).append(")").append(" ").append(operator).append(" ");
        }
        question.append(random.nextInt(100) + 1);
        return question.toString();
    }
}
