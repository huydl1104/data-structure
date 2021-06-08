package com.yudl.sturcture.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yudongliang
 * create time 2021-06-02
 * describe : 给一个字符串实现 计算器的功能（带括号）
 *      (3 + 4) × 5 - 6 中缀表达式
 *      - × + 3 4 5 6 前缀表达式
 *      3 4 + 5 × 6 - 后缀表达式（波兰式）
 */
public class CalculatorJavaTest {

    public static void exeTestCode() {
        String expression = "(7+(14*7)-1) + 7 -9 + 12 *5";
        List<String> list = toList(expression);
        System.out.println("转换的集合:" + list);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(list);
        System.out.println("后缀表达式" + parseSuffixExpressionList);
        Double value = calculation(parseSuffixExpressionList);
        System.out.println("计算的结果:" + value);
    }

    /**
     * 去除表达式的空白符
     *
     * @param str
     * @return
     */
    public static String replaceAllBlank(String str) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[\f\n\r\t\v]
        return str.replaceAll("\\s+", "");
    }


    /**
     * 判断是否是数字的正则表达式,把点，百分号也算成数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^?[.\\d]*?[%\\d]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是运算符的的正则表达式
     */
    public static boolean isSymbol(String str) {

        return str.matches("\\+|\\-|\\*|\\/|\\(|\\)");
    }

    /**
     * 判断运算符的优先级
     */
    public static Integer operatorPriority(String str) {
        if ("*".equals(str) || "/".equals(str)) {
            return 2;
        } else if ("+".equals(str) || "-".equals(str)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 计算的表达式
     *
     * @param s1     第一个值
     * @param s2     第二个值
     * @param symbol 计算的表达式
     * @return
     */
    public static Double calculation(String s1, String s2, String symbol) {
        Double result = 0.0;
        switch (symbol) {
            case "+":
                result = Double.valueOf(s2) + Double.valueOf(s1);
                break;
            case "-":
                result = Double.valueOf(s2) - Double.valueOf(s1);
                break;
            case "*":
                result = Double.valueOf(s2) * Double.valueOf(s1);
                break;
            case "/":
                result = Double.valueOf(s2) / Double.valueOf(s1);
                break;
            default:
                throw new RuntimeException("计算出错");
        }
        return result;
    }

    /**
     * 将表达式的值放入到集合中
     */
    public static List<String> toList(String exepression) {
        exepression = replaceAllBlank(exepression);
        if (exepression == null || exepression.trim().equals("")) {
            throw new RuntimeException("数据为空");
        }
        if (!isNumber(exepression.charAt(0) + "") && !(exepression.charAt(0) + "").equals("(")) {
            throw new RuntimeException("表达式的第一个值不对，请检查");
        }
        List<String> list = new ArrayList<>();
        int index = 0;
        String data = "";
        StringBuilder temp = new StringBuilder();//用于拼接数据
        //"(7+(4*7)-1) + 7 -9 + 12 *5"
        //转换的集合:[ (, 7, +, (, 4, *, 7, ), -, 1, ), +, 7, -, 9, +, 12, *, 5 ]
        //后缀表达式[7, 4, 7, *, +, 1, -, 7, +, 9, -, 12, 5, *, +]
        //计算的结果:92.0
        do {
            if (!isNumber(data = String.valueOf(exepression.charAt(index)))) {
                list.add(data);
                index++;
            } else {
                while (index < exepression.length() && isNumber(data = String.valueOf(exepression.charAt(index)))) {
                    temp.append(data);
                    if (index >= exepression.length()) {
                        break;
                    }
                    index++;
                }
                list.add(temp.toString());
                temp = new StringBuilder();
            }
        } while (index < exepression.length());
        return list;
    }

    /**
     * 将集合中的表达式转换为一个后缀表达式
     *
     * @param expressionList 传入的集合
     */
    //转换的集合 [ (, 7, +, (, 4, *, 7, ), -, 1, ), +, 7, -, 9, +, 12, *, 5 ]
    //后缀表达式 [ 7, 4, 7, *, +, 1, -, 7, +, 9, -, 12, 5, *, + ]
    public static List<String> parseSuffixExpressionList(List<String> expressionList) {
        if (expressionList.size() <= 0) {
            throw new RuntimeException("传入的数组为空，请检查");
        }
        Stack<String> stack = new Stack<>();// 用来存放符号的栈
        List<String> list = new ArrayList<>();// 用来存放数字和符号的集合
        for (String str : expressionList) {
            if (isNumber(str)) {
                // 如果是数字，则直接加入到集合
                list.add(str);
            } else {
                if (str.equals("(")) {
                    // 如果是左括号，直接入栈
                    stack.push(str);
                } else if (str.equals(")")) {
                    // 如果是右括号,则取出左边符号栈里面的符号，加入到集合里面，直到遇到符号栈里面的左括号，这样就可以消除一对括号了
                    while (!stack.peek().equals("(")) {
                        list.add(stack.pop());
                    }
                    // 消除左括号
                    stack.pop();
                } else {
                    // 如果当前取出的操作符的优先级小于等于栈顶的优先级，将栈顶的元素弹出加入到集合里面，持续到大于栈的数据，然后把当前取出的数据加入到栈
                    while (stack.size() != 0 && operatorPriority(str) <= operatorPriority(stack.peek())) {
                        list.add(stack.pop());
                    }
                    stack.push(str);
                }
            }
        }
        // 操作完成之后，然后把栈里面的数据依次加入到集合里面，集合遍历的数据就是后缀表达式
        while (stack.size() != 0) {
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 计算后缀表达式的值
     * 计算规则:如果遇到的是数字，依次入栈，如果符号，则取出栈里面的值来进行计算，
     * 计算之后将结果入栈，再讲数入栈，依次进行计算，最后栈里面的数据就是计算的结果
     *
     * @param list
     */
    public static Double calculation(List<String> list) {
        if (list.size() < 0) {
            throw new RuntimeException("集合为空");
        }
        Stack<String>  stack = new Stack<>();
        for (String str : list) {
            if (isNumber(str)) {
                stack.push(str);
            } else {
                Double calculation = calculation(stack.pop(), stack.pop(), str);
                stack.push(String.valueOf(calculation));
            }
        }
        return Double.parseDouble(stack.pop());
    }
}
