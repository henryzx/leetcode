package utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zx on 2017/8/5.
 */
public class TreeNodeFactory {

    public static TreeNode createBinaryTree(Integer... array){
        if (array.length == 0 || array[0] == null) return null;

        TreeNode root = new TreeNode(array[0]);

        int i = 0, l = array.length;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode parent = queue.poll();

            if (2 * i + 1 >= l) break;

            if (array[2 * i + 1] != null) {
                TreeNode left = new TreeNode(array[2 * i + 1]);
                parent.left = left;
                queue.offer(left);
            }


            if (2 * i + 2 >= l) break;

            if (array[2 * i + 2] != null) {
                TreeNode right = new TreeNode(array[2 * i + 2]);
                parent.right = right;
                queue.offer(right);
            }

            i++;
        }

        return root;
    }

    public static void visit(TreeNode node) {
        if (node == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(node.val + " ");
        visit(node.left);
        visit(node.right);
    }

    public static void main(String[] args) {
        TreeNode node = createBinaryTree(1,null,null,null,null,6,7,8);
        visit(node);
    }

}
