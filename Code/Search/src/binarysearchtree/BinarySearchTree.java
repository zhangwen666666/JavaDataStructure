package binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree 二叉搜索树
 */
public class BinarySearchTree {
    static class Node {
        int key;
        Object value;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Object value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;//根节点

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    /**
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key) {
        //递归方法
        //return doGet(root,key);

        //非递归方法
        Node node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else
                return node.value;
        }
        return null;
    }

    //查找关键字对应的值的递归方法
    private Object doGet(Node node, int key) {
        if (node == null)
            return null;
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (node.key < key) {
            return doGet(node.right, key);
        } else
            return node.value;
    }

    /**
     * 查找最小关键字对应的value值
     *
     * @return 最小关键字对应的value值
     */
    public Object min() {
        return min(root);
    }

    /**
     * 查找最大关键字对应的value值
     *
     * @return 最大关键字对应的value值
     */
    public Object max() {
        return max(root);
    }

    /**
     * 存储关键字和对应的值
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        Node prev = null;
        Node cur = root;
        while (cur != null) {
            prev = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                cur.value = value; //当前key存在，修改其对应的value值
                return;
            }
        }
        //当前key不存在
        if (prev == null)
            root = new Node(key, value);
        else if (key < prev.key)
            prev.left = new Node(key, value);
        else
            prev.right = new Node(key, value);
    }

    /**
     * 查找关键字的前驱值
     *
     * @param key 关键字
     * @return 前驱值
     */
    public Object predecessor(int key) {
        //找到关键字为key的节点
        Node cur = root;
        Node ancestorFromLeft = null;//存放cur的自左而来的祖先结点
        while (cur != null) {
            if (key < cur.key)
                cur = cur.left;
            else if (key > cur.key) {
                ancestorFromLeft = cur;
                cur = cur.right;
            } else
                break;
        }
        //如果没有找到当前节点
        if (cur == null)
            return null;
        //如果找到了当前节点
        //情况一：当前节点有左子树，左子树的最大值就是当前节点的前驱节点
        //情况二：如果当前节点没有左子树，那么离当前节点最近的自左而来的祖先结点就是其前驱节点
        if (cur.left != null)
            return max(cur.left);
        return ancestorFromLeft == null ? null : ancestorFromLeft.value;
    }

    private Object max(Node node) {
        if (node == null)
            return null;
        Node cur = node;
        while (cur.right != null)
            cur = cur.right;
        return cur.value;
    }

    /**
     * 查找关键字的后继值
     *
     * @param key 关键字
     * @return 后继值
     */
    public Object successor(int key) {
        //找要查找的关键字的节点
        Node cur = root;
        Node ancestorFromRight = null;//记录cur的自右而来的最近祖先
        while (cur != null) {
            if (key < cur.key) {
                ancestorFromRight = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else
                break;
        }
        //没找到当前节点
        if (cur == null)
            return null;
        //找到了当前节点
        //情况1：如果当前节点有右子树，右子树的最小值就是当前节点的后继节点
        //情况2：如果没有右子树，那么当前节点的自右而来的最近的祖先就是当前节点的后继节点
        if (cur.right != null)
            return min(cur.right);
        return ancestorFromRight == null ? null : ancestorFromRight.value;
    }

    private Object min(Node node) {
        if (node == null)
            return null;
        Node cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.value;
    }

    /**
     * 根据关键字删除值
     *
     * @param key 关键字
     * @return 被删除的值
     */
    public Object delete(int key) {
        //先寻找要删除的节点
        Node cur = root;
        Node parent = null;//记录cur的父节点
        while (cur != null) {
            if (key < cur.key) {
                parent = cur;
                cur = cur.left;
            } else if (key > cur.key) {
                parent = cur;
                cur = cur.right;
            } else
                break;
        }
        //没找到待删除的节点
        if (cur == null)
            return null;
        //如果找到了待删除的节点
        if (cur.left == null && cur.right != null) {
            //情况一：待删除的节点没有左孩子，只有右孩子
            //将待删除的节点的右孩子做为其父节点的孩子
            shift(parent, cur, cur.right);
        } else if (cur.left != null && cur.right == null) {
            //情况二：待删除的节点没有右孩子，只有左孩子
            //将待删除的节点的左孩子做为其父节点的孩子
            shift(parent, cur, cur.left);
        } else if (cur.left == null && cur.right == null) {
            //情况三：待删除的节点既没有左孩子也没有右孩子
            //直接将null做为父节点的孩子
            //可以合并到情况一或二中
            shift(parent, cur, null);
        } else {
            //情况四：被删除节点既有左孩子又有右孩子
            //将被删除节点的后继节点顶上去
            //细分为两种：
            //后继节点与被删除节点不相邻，先将后继节点的子节点做为后继节点的父节点的孩子，再将后继节点做为被删除节点父节点的孩子
            //后继节点与被删除节点相邻，直接将后继节点做为被删除节点的父节点的孩子

            //找后继节点(被删除节点有左子树和右子树，在右子树中找最小的)
            Node postNode = cur.right;
            Node postNodeParent = cur;//后继节点的父亲
            while (postNode.left != null) {
                postNodeParent = postNode;
                postNode = postNode.left;
            }
            //找到了后继节点
            if (postNode != cur.right) {
                //后继节点与被删除节点不相邻
                shift(postNodeParent, postNode, postNode.right);//处理后继节点的后事(后继节点不可能有左孩子)
                postNode.right = cur.right;
            }
            shift(parent, cur, postNode);
            postNode.left = cur.left;
        }
        return cur.value;
    }

    /**
     * 删除parent节点的子节点deleted，并用child代替deleted
     *
     * @param parent  待删除节点的父节点
     * @param deleted 待删除的节点
     * @param child   顶替待删除的节点的节点
     */
    private void shift(Node parent, Node deleted, Node child) {
        if (parent == null)
            root = child;
        else if (parent.left == deleted) {
            //如果被删除节点是父节点的左孩子，
            //那么顶替的节点要托付给父节点的左孩子
            parent.left = child;
        } else
            //如果被删除节点是父节点的右孩子
            //那么顶替的节点要要托付给父节点的右孩子
            parent.right = child;
    }


    /**
     * @param key 关键字
     * @return 返回值是一个集合，集合中存储了二叉搜索树中所有小于key的value
     */
    public List<Object> less(int key) {
        List<Object> result = new ArrayList<>();
        Node cur = root;
        LinkedList<Node> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node pop = stack.pop();
                if (pop.key < key)
                    result.add(pop.value);
                else
                    break;
                cur = pop.right;
            }
        }
        return result;
    }

    //利用反向的中序遍历
    public List<Object> greater(int key) {
        List<Object> result = new ArrayList<>();
        Node cur = root;
        LinkedList<Node> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                Node pop = stack.pop();
                if (pop.key > key)
                    result.add(pop.value);
                else
                    break;
                cur = pop.left;
            }
        }
        return result;
    }

    /**
     * 找出所有key值小于等于key1且大于等于key2的value值
     *
     * @param key1 关键字1
     * @param key2 关键字2
     * @return 返回一个List集合
     */
    public List<Object> between(int key1, int key2) {
        List<Object> result = new ArrayList<>();
        Node cur = root;
        LinkedList<Node> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node pop = stack.pop();
                if (pop.key <= key2 && pop.key >= key1)
                    result.add(pop.value);
                else if (pop.key > key2)
                    break;
                cur = pop.right;
            }
        }
        return result;
    }
}
