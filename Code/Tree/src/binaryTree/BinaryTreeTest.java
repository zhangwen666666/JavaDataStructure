package binaryTree;

import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
    @Test
    public void test1() {
        TreeNode<Integer> tree = new TreeNode<>(1);
        TreeNode<Integer> Node1 = new TreeNode<>(2);
        TreeNode<Integer> Node2 = new TreeNode<>(3);
        TreeNode<Integer> Node3 = new TreeNode<>(4);
        TreeNode<Integer> Node4 = new TreeNode<>(5);
        TreeNode<Integer> Node5 = new TreeNode<>(6);
        tree.left = Node1;
        tree.right = Node2;
        Node1.left = Node3;
        Node2.left = Node4;
        Node2.right = Node5;

        BinaryTree.preOrder(tree);
        System.out.println();
        BinaryTree.inOrder(tree);
        System.out.println();
        BinaryTree.postOrder(tree);
    }

    @Test
    public void test2() {
        Integer[] preorder = {1, 2, 4, 3, 5, 6};
        Integer[] inorder = {4, 2, 1, 5, 3, 6};
        TreeNode<Integer> tree = BinaryTree.buildTree(preorder, inorder);
        BinaryTree.postOrder(tree);
    }

    @Test
    public void test03() {
        Integer[] inorder = {4, 2, 1, 5, 3, 6};
        Integer[] postorder = {4, 2, 5, 6, 3, 1};
        TreeNode<Integer> tree = BinaryTree.createTree(inorder,postorder);
        BinaryTree.preOrder(tree);
    }
}
