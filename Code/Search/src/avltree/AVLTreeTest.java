package avltree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AVLTreeTest {
    @Test
    public void test01(){
        AVLTree tree = new AVLTree();
        tree.put(4,4);
        tree.put(5,5);
        tree.put(7,7);
        tree.put(2,2);
        tree.put(1,1);
        tree.put(3,3);
        tree.put(6,6);
        tree.put(8,8);
        String[] strings = tree.preOrder();
        System.out.println(Arrays.toString(strings));
        tree.remove(4);
        strings = tree.preOrder();
        System.out.println(Arrays.toString(strings));
    }
}
