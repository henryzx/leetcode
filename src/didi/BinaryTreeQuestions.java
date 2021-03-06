package didi;

import java.util.LinkedList;
import java.util.Queue;

/**
 * binary search tree
 * 5
 * 3 7
 * 2 4 6
 * <p>
 * Created by zx on 2017/3/22.
 */
public class BinaryTreeQuestions {

    public static void main(String[] args) {
        Node node = makeOrderTree();
        bfs(node);

        if (true) {
            System.out.println("depth " + depthIterVer2(node));
        }

        if (true) {
            System.out.println("depth " + depth(node));
        }

        if (true) {
            System.out.println("depth " + depthIterVer(node));
        }



        //dfs(node);

        if (false) {
            Node last = toLinkList(node);
            while (last != null) {
                System.out.println(last.value);
                last = last.right;
            }
        }

        if (false) {
            System.out.println(getCountOfLevel(node, 4));
        }

        if (false) {
            bfs(node);
            mirror(node);
            bfs(node);
        }

        if (false) {
            System.out.println(findCommonParent(node, 6, 4).value);
        }
    }

    public static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(int value) {
            this.value = value;
        }
    }

    static Node makeTree() {
        return
                new Node(9,
                        new Node(4,
                                new Node(1,
                                        new Node(2), null), null),
                        new Node(8,
                                new Node(5), null));
    }

    static Node makeOrderTree() {
        return
                new Node(5,
                        new Node(3,
                                new Node(2),
                                new Node(4)),
                        new Node(7,
                                new Node(6),
                                new Node(8)));
    }

    static void dfs(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.value);

        dfs(node.left);
        dfs(node.right);
    }

    /**
     * 打印二叉树
     *
     * @param node
     */
    static void bfs(Node node) {
        int count = 1;
        int target = 1;
        LinkedList<Node> list = new LinkedList<>();
        list.addLast(node);
        while (!list.isEmpty()) {
            Node thisNode = list.removeFirst();

            // visit
            String value = " ";
            if (thisNode != null) {
                value = "" + thisNode.value;
                list.addLast(thisNode.left);
                list.addLast(thisNode.right);
            }

            System.out.print(value);
            System.out.print(" ");
            if (count >= target) {
                System.out.println();
                target = target * 2;
                count = 1;
            } else {
                count++;
            }
        }
    }

    static int depth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    // 不是有序的
    static Node toLinkList(Node root) {
        Node mid = toLinkListInternal(root);
        while (mid.left != null) { // 找到最左侧节点
            mid = mid.left;
        }
        return mid;
    }

    private static Node toLinkListInternal(Node root) {
        if (root.left == null && root.right == null) {
            return root;
        }

        if (root.left != null) {
            Node temp = toLinkListInternal(root.left);
            while (temp.right != null) {
                temp = temp.right;
            }
            // place the root to the right most place
            temp.right = root;
            root.left = temp;
        }
        if (root.right != null) {
            Node temp = toLinkListInternal(root.right);

            // place the root to the left most place
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.left = root;
            root.right = temp;
        }
        return root;
    }

    static int getCountOfLevel(Node root, int level) {
        if (root == null || level <= 0) {
            return 0;
        }
        if (level == 1) {
            return 1;
        }
        int leftCount = getCountOfLevel(root.left, level - 1);
        int rightCount = getCountOfLevel(root.right, level - 1);
        return leftCount + rightCount;
    }

    static void mirror(Node root) {
        if (root == null) {
            return;
        }

        mirror(root.left);
        mirror(root.right);

        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    static Node findCommonParent(Node root, int first, int second) {

        if (findNode(root.left, first) && findNode(root.left, second)) {
            return findCommonParent(root.left, first, second);
        }

        if (findNode(root.right, first) && findNode(root.right, second)) {
            return findCommonParent(root.right, first, second);
        }

        // 还需要看到底能不能找到

        return root;
    }

    private static boolean findNode(Node root, int target) {
        return root != null && (root.value == target || findNode(root.left, target) || findNode(root.right, target));
    }

    public static class DNode {
        Node node;
        int depth;

        public DNode(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * 使用标注法
     *
     * @param root
     *
     * @return
     */
    static int depthIterVer(Node root) {
        int maxStackLength = 0;

        LinkedList<DNode> list = new LinkedList<>();
        list.addLast(new DNode(root, 1));
        while (!list.isEmpty()) {
            DNode dnode = list.removeFirst();
            maxStackLength = dnode.depth;
            Node node = dnode.node;
            if (node.left != null) {
                list.addLast(new DNode(node.left, dnode.depth + 1));
            }
            if (node.right != null) {
                list.addLast(new DNode(node.right, dnode.depth + 1));
            }
        }

        return maxStackLength;
    }

    /**
     * 非递归实现
     *
     * 借用一个思考：在bfs时，当遍历到某一层后，队列中的长度即是下一层的所有节点。
     *
     * 可以以此切分层级
     *
     * @param root
     * @return
     */
    static int depthIterVer2(Node root) {
        if (root == null) {
            return 0;
        }
        int height = 0;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        // current level is 1 (just pushed the root node)
        int levelCount = 1;

        while (!q.isEmpty()) {
            Node n = q.poll();
            levelCount--; // visited one node


            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }

            // visited the end of a level. reset level counter
            if (levelCount == 0) {
                height++;
                levelCount = q.size();
            }
        }

        return height;
    }
}
