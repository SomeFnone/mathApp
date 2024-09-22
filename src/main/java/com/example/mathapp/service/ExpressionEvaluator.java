package com.example.mathapp.service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Random;

public class ExpressionEvaluator {

    /**
     * 计算表达式的结果。
     *
     * @param question 数学表达式字符串
     * @return 表达式的结果（保留两位小数）
     */
    public static double evaluateExpression(String question) {
        // 替换开方符号为 sqrt，并正确添加括号
        String expression = question.replaceAll("√(\\d+)", "sqrt($1)");

        // 创建表达式
        Expression exp = new ExpressionBuilder(expression)
                .build();

        // 计算表达式的值
        double result = exp.evaluate();

        // 将结果保留两位小数并四舍五入
        return Math.round(result * 100.0) / 100.0;
    }

    /**
     * 生成错误选项。
     *
     * @param correctAnswer 正确答案
     * @return 4个选项
     */
    public static Double[] generateOptions(double correctAnswer) {
        Random random = new Random();
        Double[] options = new Double[4];

        // 保证正确答案是第一个选项
        options[0] = correctAnswer;

        // 处理正确答案的整数和小数部分
        boolean isInteger = correctAnswer % 1 == 0;
        int decimalPlaces = isInteger ? 0 : String.valueOf(correctAnswer).split("\\.")[1].length();

        // 生成三个错误选项
        for (int i = 1; i < 4; i++) {
            double wrongAnswer;
            if (isInteger) {
                // 对于整数答案，生成整数误差
                int error = random.nextBoolean() ? random.nextInt(5) + 1 : -(random.nextInt(5) + 1);
                wrongAnswer = correctAnswer + error;
            } else {
                // 对于小数答案，生成小数误差
                double error = random.nextDouble() * 2 - 1; // 生成-1到1之间的误差
                wrongAnswer = correctAnswer + error;

                // 限制小数位数
                wrongAnswer = Math.round(wrongAnswer * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
            }

            // 确保错误答案与正确答案不同
            while (wrongAnswer == correctAnswer) {
                if (isInteger) {
                    int error = random.nextBoolean() ? random.nextInt(5) + 1 : -(random.nextInt(5) + 1);
                    wrongAnswer = correctAnswer + error;
                } else {
                    double error = random.nextDouble() * 2 - 1;
                    wrongAnswer = correctAnswer + error;
                    wrongAnswer = Math.round(wrongAnswer * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
                }
            }

            options[i] = wrongAnswer;
        }

        return options;
    }

    /**
     * 将表达式转换为 Markdown 格式。
     *
     * @param question 数学表达式字符串
     * @return Markdown 格式的表达式字符串
     */
    public static String toMarkdown(String question) {
        // 替换乘法符号为 \cdot
        String expression = question.replaceAll("\\*", "\\\\cdot");
        // 替换除法符号为 \div
        expression = expression.replaceAll("/", "\\\\div");
        // 处理平方的情况
        //expression = expression.replaceAll("^2", "{$1^2}");
        // 替换开方符号为 sqrt，并正确添加括号
        expression = expression.replaceAll("√(\\d+)", "\\\\sqrt{$1}");
        // 替换三角函数符号为 LaTeX 格式
        expression = expression.replaceAll("sin", "\\\\sin");
        expression = expression.replaceAll("cos", "\\\\cos");
        expression = expression.replaceAll("tan", "\\\\tan");
        // 添加适当的括号
        expression = expression.replaceAll("\\(([^()]+)\\)", "\\\\left($1\\\\right)");
        // 创建 Markdown 格式的表达式
        return "$$" + expression + "$$";
    }
}
