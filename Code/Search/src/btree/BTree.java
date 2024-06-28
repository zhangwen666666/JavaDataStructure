package btree;

import java.util.Arrays;

public class BTree {
    static class Node {
        int[] keys;
        Node[] children;
        int keyNumber;//关键字数量
        boolean isTerminal = true;//标记节点是否是终端节点
        int t;//最小度数

        public Node(int t) {
            this.t = t;
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append("[");
            for (int i = 0; i < keyNumber; i++) {
                s.append(keys[i]).append(" ");
            }
            if (s.charAt(s.length() - 1) == ' ') {
                s.deleteCharAt(s.length() - 1);
            }
            s.append("]");
            return s.toString();
        }

        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            if (isTerminal) {
                return null;
            }
            return children[i].get(key);
        }

        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }
    }

    Node root;

    int t;//树中节点的最小度数
    final int MAX_KEY_NUMBER;
    final int MIN_KEY_NUMBER;

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    /**
     * 查找关键字key是否存在
     *
     * @param key 关键字
     * @return 是否找到
     */
    public boolean contains(int key) {
        return root.get(key) != null;
    }


    /**
     * 插入key
     *
     * @param key 键
     */
    public void put(int key) {
        add(root, key, null, 0);
    }

    /**
     * @param node   被插入键的节点，即需要将key插入到node中
     * @param key    待插入的键
     * @param parent node的父节点
     * @param index  node是parent的第index个孩子
     */
    private void add(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return;
            } else if (node.keys[i] < key) {
                i++;
            } else {
                break;
            }
        }
        //走到这里就找到了插入位置
        if (node.isTerminal) {
            node.insertKey(key, i);
        } else {
            add(node.children[i], key, node, i);
        }
        //判断当前节点是否需要分裂
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    /**
     * 分裂节点的方法
     *
     * @param left   待分裂节点
     * @param parent 待分裂节点的父节点
     * @param index  待分裂节点是其父节点的第几个孩子
     */
    private void split(Node left, Node parent, int index) {
        //如果分裂的新节点，需要创建新的根节点
        if (parent == null) {
            Node newRoot = new Node(t);
            newRoot.isTerminal = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        //创建新节点，将待分裂节点的后半部分拷贝进来
        Node right = new Node(t);
        right.isTerminal = left.isTerminal;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        right.keyNumber = t - 1;
        if (!left.isTerminal) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        //将待分裂节点的中间值插入到父节点中
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        //新节点插入到父节点的children数组中
        parent.insertChild(right, index + 1);
        //待分裂节点的keyNumber需要调整
        left.keyNumber = t - 1;
    }
}
