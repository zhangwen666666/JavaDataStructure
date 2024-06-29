package hashtable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashTable<K, V> {
    static class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry<K, V>[] table = new Entry[4];
    int size = 0; //元素个数
    float loadFactor = 0.75f;
    int threshold = (int) (loadFactor * table.length);

    /**
     * 根据hash值，key值获取value值
     *
     * @param hash hash值
     * @param key  key值
     * @return key对应的value值
     */
    V get(int hash, K key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry<K, V> cur = table[index];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 插入键值对
     *
     * @param hash  哈希码
     * @param key   键
     * @param value 值
     */
    void put(int hash, K key, V value) {
        int index = hash & (table.length - 1);
        Entry<K, V> cur = table[index];
        if (cur == null) {
            table[index] = new Entry<>(hash, key, value);//新增
            size++;
            if (size > threshold) {
                resize();
            }
            return;
        }
        Entry<K, V> prev = null;//cur的前驱
        while (cur != null) {
            if (cur.key.equals(key)) {
                cur.value = value;//更新
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        prev.next = new Entry<>(hash, key, value);//新增
        size++;
        if (size > threshold) {
            resize();
        }
    }

    private void resize() {
        Entry<K, V>[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> cur = table[i];
            if (cur != null) {
                //拆分链表，移动到新数组中
                //拆分时，一个链表最多拆成两个
                //hash & table.length == 0 的一组
                //hash & table.length != 0 的一组
                Entry<K, V> aHead = null;
                Entry<K, V> aTail = null;
                Entry<K, V> bHead = null;
                Entry<K, V> bTail = null;
                while (cur != null) {
                    if ((cur.hash & table.length) == 0) {
                        if (aHead == null) {
                            aHead = aTail = cur;
                        } else {
                            aTail.next = cur;
                            aTail = aTail.next;
                        }
                    } else {
                        if (bHead == null) {
                            bHead = bTail = cur;
                        } else {
                            bTail.next = cur;
                            bTail = bTail.next;
                        }
                    }
                    cur = cur.next;
                }
                if (aTail != null) {
                    aTail.next = null;
                }
                if (bTail != null) {
                    bTail.next = null;
                }
                newTable[i] = aHead;
                newTable[i + table.length] = bHead;
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    /**
     * 删除键值对
     *
     * @param hash 哈希码
     * @param key  键
     * @return 被删除的键值对的值
     */
    V remove(int hash, K key) {
        int index = hash & (table.length - 1);
        Entry<K, V> cur = table[index];
        if (cur == null)
            return null;
        Entry<K, V> prev = null;//cur的前驱
        while (cur != null) {
            if (cur.key.equals(key)) {
                //删除
                if (prev == null) {
                    table[index] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                cur.next = null;//help GC;
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    V get(K key) {
        int hash = key.hashCode();
        return get(hash, key);
    }

    void put(K key, V value) {
        int hash = key.hashCode();
        put(hash, key, value);
    }

    V remove(K key) {
        int hash = key.hashCode();
        return remove(hash, key);
    }

    void print() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> cur = table[i];
            while (cur != null) {
                sums[i]++;
                cur = cur.next;
            }
        }
//        System.out.println(Arrays.toString(sums));

//        Map<Integer, List<Integer>> collect =
//                Arrays.stream(sums).boxed().collect(Collectors.groupingBy(i->i));
//        collect.forEach((k,v)-> System.out.println(k + "=" + v));

        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        collect.forEach((k,v)-> System.out.println(k + "=" + v));
        System.out.println(collect);
    }
}
