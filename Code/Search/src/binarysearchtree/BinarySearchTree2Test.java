package binarysearchtree;

import org.junit.jupiter.api.Test;

public class BinarySearchTree2Test {
    public BinarySearchTree2<Integer> createTree() {
        BinarySearchTree2.Node<Integer> n1 = new BinarySearchTree2.Node<>(1, "张无忌");
        BinarySearchTree2.Node<Integer> n3 = new BinarySearchTree2.Node<>(3, "宋青书");
        BinarySearchTree2.Node<Integer> n2 = new BinarySearchTree2.Node<>(2, "周芷若", n1, n3);

        BinarySearchTree2.Node<Integer> n5 = new BinarySearchTree2.Node<>(5, "说不得");
        BinarySearchTree2.Node<Integer> n7 = new BinarySearchTree2.Node<>(7, "殷离");
        BinarySearchTree2.Node<Integer> n6 = new BinarySearchTree2.Node<>(6, "赵敏", n5, n7);
        BinarySearchTree2.Node<Integer> root = new BinarySearchTree2.Node<>(4, "小昭", n2, n6);

        return new BinarySearchTree2<>(root);
    }

    @Test
    public void test01() {
        BinarySearchTree2<Integer> tree = createTree();
        for (int i = 1; i <= 7; i++) {
            System.out.println(i + "=" + tree.get(i));
        }
    }
}

