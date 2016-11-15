package leetcode;

import java.util.LinkedList;

/**
 * Created by zhengxiao on 15/11/2016.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        boolean result = new ValidParentheses().isValid("[()]");
        System.out.println(result);
        if (!result) {
            throw new IllegalStateException("wrong");
        }
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ("{[(".contains("" + c)) {
                // push
                stack.push(c);
            } else {
                // pop, 判断是否 match
                if (!stack.isEmpty()) {
                    char old = stack.pop();
                    if (old == '[' && c == ']' || old == '{' && c == '}' || old == '(' && c == ')') {
                        // ok
                    } else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
