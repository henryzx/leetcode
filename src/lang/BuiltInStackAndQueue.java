package lang;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zx on 2017/6/28.
 */
public class BuiltInStackAndQueue {

    public static void main(String[] args) {
        System.out.println("vector as stack");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println("linkedlist as queue");
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(3);
        queue.offer(2);
        queue.offer(1);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
