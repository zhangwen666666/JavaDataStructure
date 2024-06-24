package binarysearchtree;

/**
 * Binary Search Tree2 二叉搜索树
 */
public class BinarySearchTree2<T extends Comparable<T>> {
    static class Node<T> {
        T key;
        Object value;
        Node<T> left;
        Node<T> right;

        public Node(T key) {
            this.key = key;
        }

        public Node(T key, Object value, Node<T> left, Node<T> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(T key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<T> root;//根节点

    public BinarySearchTree2() {
    }

    public BinarySearchTree2(Node<T> root) {
        this.root = root;
    }

    /**
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(T key) {
        //递归方法
        //return doGet(root,key);

        //非递归方法
        Node<T> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else
                return node.value;
        }
        return null;
    }

    //查找关键字对应的值的递归方法
    private Object doGet(Node<T> node, T key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            return doGet(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return doGet(node.right, key);
        } else
            return node.value;
    }
}
