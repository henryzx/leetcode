package toutiao;

/**
 * Created by zhengxiao on 15/03/2017.
 */
public class ReverseLinkList {

    static class Node {
        Node next;
        int value;

        Node() {
        }

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node list = new Node(1, new Node(2, new Node(3, new Node(4, null))));
        print(list);
        System.out.println("to");
        Node newList = new ReverseLinkList().reverse(list);
        print(newList);

    }

    public Node reverse(Node list) {
        Node prev = null;
        while (list != null) {
            Node old = list.next;
            list.next = prev;
            prev = list;
            list = old;
        }
        return prev;
    }

    static void print(Node thiz) {
        while (thiz != null) {
            System.out.println(thiz.value);
            thiz = thiz.next;
        }
    }
}
