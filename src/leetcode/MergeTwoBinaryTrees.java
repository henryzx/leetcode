package leetcode;

import java.util.Stack;

/**
 * Created by zx on 2017/6/28.
 */
public class MergeTwoBinaryTrees {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" + val + "}";
        }
    }


    public static class RecursiveSolution {

        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null)
                return t2;
            if (t2 == null)
                return t1;
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }

        public static void main(String[] args) {
            TreeNode t11 = new TreeNode(1);
            TreeNode t1 = new TreeNode(3);
            t1.left = t11;
            TreeNode t21 = new TreeNode(4);
            TreeNode t2 = new TreeNode(2);
            t2.right = t21;

            TreeNode node = new RecursiveSolution().mergeTrees(t1, t2);
            System.out.println(node);

        }
    }

    public static class IterationSolution {

        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

            Stack<TreeNode[]> stack = new Stack<>();
            stack.push(new TreeNode[]{t1, t2});

            while (!stack.isEmpty()) {
                TreeNode[] pair = stack.pop();

                if (pair[0] == null || pair[1] == null) continue;

                pair[0].val += pair[1].val;

                if (pair[0].left == null) {
                    pair[0].left = pair[1].left;
                } else {
                    stack.push(new TreeNode[]{pair[0].left, pair[1].left});
                }

                if (pair[0].right == null) {
                    pair[0].right = pair[1].right;
                } else {
                    stack.push(new TreeNode[]{pair[0].right, pair[0].right});
                }

            }

            return t1;
        }

        public static void main(String[] args) {
            TreeNode t11 = new TreeNode(1);
            TreeNode t1 = new TreeNode(3);
            t1.left = t11;
            TreeNode t21 = new TreeNode(4);
            TreeNode t2 = new TreeNode(2);
            t2.right = t21;

            TreeNode node = new IterationSolution().mergeTrees(t1, t2);
            System.out.println(node);
        }
    }


}
