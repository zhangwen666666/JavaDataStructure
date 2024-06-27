package redBlackTree;

import org.junit.jupiter.api.Test;

public class RBTreeTest {
    @Test
    public void testPut(){
        RedBlackTree tree = new RedBlackTree();
        tree.put(20,20);
        tree.put(10,10);
        tree.put(5,5);
        tree.put(30,30);
        tree.put(40,40);
        tree.put(57,57);
        tree.put(3,3);
        tree.put(2,2);
        tree.put(4,4);
        tree.put(35,35);
        tree.put(25,25);
        tree.put(18,18);
        tree.put(22,22);
        tree.put(23,23);
        tree.put(24,24);
        tree.put(19,19);
    }
}
