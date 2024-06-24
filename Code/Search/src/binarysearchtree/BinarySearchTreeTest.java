package binarysearchtree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {
    public BinarySearchTree createTree() {
        BinarySearchTree.Node n1 = new BinarySearchTree.Node(1, "张无忌");
        BinarySearchTree.Node n3 = new BinarySearchTree.Node(3, "宋青书");
        BinarySearchTree.Node n2 = new BinarySearchTree.Node(2, "周芷若", n1, n3);

        BinarySearchTree.Node n5 = new BinarySearchTree.Node(6, "说不得");
        BinarySearchTree.Node n7 = new BinarySearchTree.Node(8, "殷离");
        BinarySearchTree.Node n6 = new BinarySearchTree.Node(7, "赵敏", n5, n7);
        BinarySearchTree.Node root = new BinarySearchTree.Node(4, "小昭", n2, n6);

        return new BinarySearchTree(root);
    }

    @Test
    public void test01() {
        BinarySearchTree tree = createTree();
        for (int i = 1; i <= 8; i++) {
            System.out.println(i + "=" + tree.get(i));
        }

        System.out.println(tree.min());
        System.out.println(tree.max());

        tree.put(1, "灭绝师太");
        System.out.println(tree.get(1));

        tree.put(5, "金毛狮王");
        System.out.println(tree.get(5));
    }

    @Test
    public void test02() {
        BinarySearchTree tree = createTree();
        for (int i = 1; i <= 8; i++) {
            System.out.println(i + "的前驱节点是：" + tree.predecessor(i));
            System.out.println(i + "的后继节点是：" + tree.successor(i));
        }
    }

    @Test
    public void test03() {
        BinarySearchTree.Node n1 = new BinarySearchTree.Node(1, 1);
        BinarySearchTree.Node n3 = new BinarySearchTree.Node(3, 3);
        BinarySearchTree.Node n2 = new BinarySearchTree.Node(2, 2, n1, n3);

        BinarySearchTree.Node n5 = new BinarySearchTree.Node(5, 5);
        BinarySearchTree.Node n6 = new BinarySearchTree.Node(6, 6, n5, null);
        BinarySearchTree.Node n7 = new BinarySearchTree.Node(7, 7, n6, null);
        BinarySearchTree.Node n4 = new BinarySearchTree.Node(4, 4, n2, n7);

        BinarySearchTree tree = new BinarySearchTree(n4);

        for (int i = 1; i <= 7; i++) {
            System.out.println(i + "=" + tree.get(i));
        }

        tree.delete(4);
        System.out.println(tree.get(7));
    }

    @Test
    public void test04() {
        BinarySearchTree.Node n1 = new BinarySearchTree.Node(1, 1);
        BinarySearchTree.Node n3 = new BinarySearchTree.Node(3, 3);
        BinarySearchTree.Node n2 = new BinarySearchTree.Node(2, 2, n1, n3);

        BinarySearchTree.Node n5 = new BinarySearchTree.Node(5, 5);
        BinarySearchTree.Node n6 = new BinarySearchTree.Node(6, 6, n5, null);
        BinarySearchTree.Node n7 = new BinarySearchTree.Node(7, 7, n6, null);
        BinarySearchTree.Node n4 = new BinarySearchTree.Node(4, 4, n2, n7);

        BinarySearchTree tree = new BinarySearchTree(n4);

        List<Object> list = tree.less(5);
        for (Object o : list) {
            Integer i = (Integer) o;
            System.out.println(i);
        }

        System.out.println("====================");
        List<Object> greater = tree.greater(3);
        for (Object o : greater) {
            Integer i = (Integer) o;
            System.out.println(i);
        }

        System.out.println("====================");
        List<Object> between = tree.between(3, 8);
        for (Object o : between) {
            Integer i = (Integer) o;
            System.out.println(i);
        }
    }
}

