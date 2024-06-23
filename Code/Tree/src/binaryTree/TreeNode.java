package binaryTree;

public class TreeNode <E>{
    public E val;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(E val) {
        this(val,null,null);
    }

    @Override
    public String toString() {
        return this.val.toString();
    }
}
