package leetcode.binaryTree;

import utils.TreeNode;
import utils.TreeNodeFactory;

/**
 * Given a binary tree, determine if it is a valid Binary Search Tree (BST).
 */
public class ValidateBinarySearchTree {

    Integer leftMostValue = null;

    private boolean isRising(TreeNode node) {
        if (node == null) return true;
        if (!isRising(node.left)) return false;
        if (leftMostValue != null && node.val < leftMostValue) return false;
        leftMostValue = node.val;
        return isRising(node.right);
    }

    /**
     * 利用性质： In-order traversal，是递增的，来保证
     *
     * @param treeNode
     * @return
     */
    public boolean isValidBST(TreeNode treeNode) {
        leftMostValue = null;
        return isRising(treeNode);
    }


    private boolean between(TreeNode node, Integer min, Integer max) {
        return node == null
                || (min == null || node.val > min)
                && (max == null || node.val < max)
                && between(node.left, min, node.val)
                && between(node.right, node.val, max);
    }

    public boolean isValidBST2(TreeNode treeNode) {
        return between(treeNode, null, null);
    }

    public static void main(String[] args) {
        /*
         *  4
         *  2 5
         *  1 3 nil 6
         */
        System.out.println(new ValidateBinarySearchTree().isValidBST(TreeNodeFactory.createBinaryTree(4, 2, 5, 1, 3, null, 6)));
        System.out.println(new ValidateBinarySearchTree().isValidBST2(TreeNodeFactory.createBinaryTree(4, 2, 5, 1, 3, null, 6)));
    }

}
