package btree;

import org.junit.jupiter.api.Test;

public class BTreeTest {
    @Test
    public void test(){
        BTree tree = new BTree();
        tree.put(1);
        tree.put(2);
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.put(7);
        tree.put(8);
        tree.put(9);
        tree.put(10);
        tree.put(11);
        System.out.println(tree);
    }
}
