package stack;

import java.util.Stack;

public class InfixToSuffix {
    /**
     * @param c 操作符
     * @return 操作符的优先级
     */
    private static int priority(char c) {
        int priority = 0;
        switch (c) {
            case '+', '-' -> priority = 1;
            case '*', '/' -> priority = 2;
            default -> throw new IllegalArgumentException("操作符无效");
        }
        return priority;
    }

    public static String infixToSuffix(String exp) {
        Stack<Character> stack = new Stack<>(); //存放操作符
        StringBuilder s = new StringBuilder();  //拼接后缀表达式
        char[] chars = exp.toCharArray();
        for (char c : chars) {
            switch (c) {
                //如果遇到了左括号，直接入栈
                //如果遇到了右括号，依次将栈中的操作符弹栈，并拼接到表达式，直到遇到左括号为止
                //注意左括号也要出栈，同时右括号不入栈
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while(stack.peek()!='('){
                        s.append(stack.pop());
                    }
                    stack.pop();//左括号出栈
                }
                //如果遇到了操作符
                //栈为空，操作符直接入栈
                //如果栈顶元素是左括号，直接入栈
                //比较当前操作符的优先级和栈顶操作符的优先级
                //依次判断栈顶操作符的优先级，此过程中如果遇到了左括号，不用在继续了，直接将当前操作符入栈
                //只要栈顶操作符优先级大于等于当前操作符的优先级，都出栈，拼到表达式中
                //当前操作符入栈
                case '+', '-', '*', '/' -> {
                    while (!stack.isEmpty() && stack.peek() != '(' && priority(c) <= priority(stack.peek())) {
                        s.append(stack.pop());
                    }
                    stack.push(c);
                }
                default -> {
                    s.append(c);
                }
            }
        }
        //栈不为空，则将所有操作符弹栈，拼到后缀表达式中
        while(!stack.isEmpty()){
            s.append(stack.pop());
        }
        return s.toString();
    }

    public static void main(String[] args) {
//        System.out.println(infixToSuffix("a+b"));
//        System.out.println(infixToSuffix("a+b-c"));
//        System.out.println(infixToSuffix("a*b+c"));
//        System.out.println(infixToSuffix("a+b*c"));
//        System.out.println(infixToSuffix("a+b*c-d"));
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));

    }
}
