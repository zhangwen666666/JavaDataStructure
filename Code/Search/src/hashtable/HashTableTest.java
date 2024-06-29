package hashtable;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HashTableTest {
    @Test
    public void test01() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(1, 1, "张三");
        table.put(17, 2, "李四");
        table.put(2, 3, "王五");

        assertEquals(3, table.size);
        assertEquals("张三", table.get(1, 1));
        assertEquals("李四", table.get(17, 2));
        assertEquals("王五", table.get(2, 3));

        table.put(1, 1, "zhangsan");
        table.put(17, 2, "lisi");
        assertEquals("zhangsan", table.get(1, 1));
        assertEquals("lisi", table.get(17, 2));
    }

    @Test
    public void test02() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(1, 1, "张三");
        table.put(17, 2, "李四");
        table.put(2, 3, "王五");

        table.remove(17, 2);
        assertEquals(2, table.size);
        assertEquals("张三", table.table[1].value);
        assertNull(table.table[1].next);
    }

    @Test
    public void test03() {
        HashTable<Object, Object> table = new HashTable<>();
        for (int i = 0; i < 20000; i++) {
            table.put(new Object(), new Object());
        }
        table.print();
    }

    @Test
    public void test04() {
        System.out.println(Integer.toBinaryString(524290));
        System.out.println(Integer.toBinaryString(524290 >>> 16));
        HashMap<Integer,Integer> map = new HashMap<>();
        Object[] array = map.keySet().toArray();

    }

    @Test
    public void test05(){}
}
