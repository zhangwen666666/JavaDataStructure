package redBlackTree;

/**
 * 红黑树
 * 1. 所有节点都有两种颜色：红色和黑色
 * 2. 所有null视为黑色
 * 3. 红色节点不能相邻
 * 4. 根节点是黑色
 * 5. 从根到任意一个叶子结点，路径中的黑色节点数一样
 */
public class RedBlackTree {
    enum Color {
        RED, BLACK;
    }

    private Node root;//根节点

    /**
     * 节点类
     */
    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;    //父节点
        Color color = Color.RED;

        /**
         * 判断当前节点是否是一个左孩子
         *
         * @return ture表示是一个左孩子
         */
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        /**
         * @return 当前节点的叔叔节点
         */
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            //如果父节点是左孩子，那么叔叔节点就是爷爷节点的右孩子
            if (parent.isLeftChild()) {
                return parent.parent.right;
            }
            //如果父节点是右孩子，那么叔叔节点就是爷爷节点的左孩子
            return parent.parent.left;
        }

        /**
         * @return 当前节点的兄弟节点
         */
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild())
                return parent.right;
            return parent.left;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * 判断某个节点是否是红色
     *
     * @param node 节点
     * @return true表示结点是红色
     */
    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * 判断某个节点是否是黑色
     *
     * @param node 节点
     * @return true表示结点是黑色
     */
    boolean isBlack(Node node) {
        return node == null || node.color == Color.BLACK;
    }

    /**
     * 右旋
     *
     * @param node 右旋的节点
     */
    private void rightRotate(Node node) {
        Node parent = node.parent;
        Node left = node.left;
        node.left = left.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        left.right = node;
        node.parent = left;
        if (parent == null) {
            root = left;
            root.parent = null;
        } else {
            left.parent = parent;
            if (node == parent.left) {
                parent.left = left;
            } else {
                parent.right = left;
            }
        }
    }

    /**
     * 左旋
     *
     * @param node 左旋的节点
     */
    private void leftRotate(Node node) {
        Node parent = node.parent;
        Node right = node.right;
        node.right = right.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        right.left = node;
        node.parent = right;
        if (parent == null) {
            root = right;
            root.parent = null;
        } else {
            right.parent = parent;
            if (node == parent.left) {
                parent.left = right;
            } else {
                parent.right = right;
            }
        }
    }


    /**
     * 新增键值对，键存在时修改值
     *
     * @param key   键
     * @param value 值
     * @return 如果key存在，则返回原来的value值，key不存在返回null
     */
    public Object put(int key, Object value) {
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            parent = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else if (key > cur.key) {
                cur = cur.right;
            } else {
                Object oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
        }
        Node newNode = new Node(key, value);
        if (parent == null) {
            root = newNode;
        } else if (parent.key < key) {
            parent.right = newNode;
            newNode.parent = parent;
        } else {
            parent.left = newNode;
            newNode.parent = parent;
        }
        fixRedRed(newNode);
        return null;
    }


    /**
     * 插入一个节点之后，导致了红红相邻，将其调整平衡
     * 1. 插入节点为根节点，将根节点变黑
     * 2. 插入节点父节点为黑色，无需调整
     * 3. 插入节点的父节点为红色，叔叔节点为红色
     * 1) 父亲变为黑色，叔叔也变为黑色
     * 2) 祖父变成红色
     * 3) 将祖父看做新插入的节点，递归调整
     * 4. 插入节点的父节点为红色，叔叔节点为黑色
     * 1) LL型：父亲变黑，爷爷变红，右旋爷爷
     * 2) LR型：新节点变黑，爷爷变红，
     *
     * @param node 待调整节点
     */
    private void fixRedRed(Node node) {
        //情况1 新节点是根节点
        if (node == root) {
            node.color = Color.BLACK;
            return;
        }
        //情况2 新节点的父节点是黑色
        if (isBlack(node.parent)) {
            return;
        }
        //情况三 叔叔是红色
        Node parent = node.parent;
        Node uncle = node.uncle();
        //grandParent一定不为空，因为如果parent是根节点，那么就不会发生红红相邻
        Node grandParent = parent.parent;
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK; //这里uncle一定不为空，因为null的颜色是黑色
            grandParent.color = Color.RED;
            fixRedRed(grandParent);
            return;
        }
        //情况四：叔叔是黑色
        //LL型
        if (parent.isLeftChild() && node.isLeftChild()) {
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            rightRotate(grandParent);
            return;
        }
        //LR型
        if (parent.isLeftChild()) {
            node.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(parent);
            rightRotate(grandParent);
            return;
        }
        //RR型
        if (!node.isLeftChild()) {
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(grandParent);
            return;
        }
        //RL型
        node.color = Color.BLACK;
        grandParent.color = Color.RED;
        rightRotate(parent);
        leftRotate(grandParent);
    }


    /**
     * 删除关键字为key的节点
     *
     * @param key 关键字
     * @return 返回key对应的value值，key不存在时返回null
     */
    public Object remove(int key) {
        Node deleted = find(key);
        if (deleted == null)
            return null;
        deleteNode(deleted);
        return deleted.value;
    }

    private void deleteNode(Node deleted) {
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        //被删除节点没有孩子
        if (replaced == null) {
            //根节点且没有孩子
            if (root == deleted) {
                root = null;
            } else { //不是根节点且没有孩子
                if (isBlack(deleted)) {
                    //被删除叶子节点是黑色
                    fixDoubleBlack(deleted);
                }
                //红色叶子节点不需要任何处理
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        //被删除节点只有一个孩子
        if (deleted.left == null || deleted.right == null) {
            //根节点且有一个孩子
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                replaced.parent = null;//清空
                root.left = root.right = null;
            } else { //不是根节点有一个孩子
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.parent = null;
                deleted.left = deleted.right = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    //删的是黑，剩下的也是黑
                    fixDoubleBlack(replaced);
                } else if (isBlack(deleted)) {
                    //删的是黑，剩下的是红
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        //被删除节点有两个孩子
        //交换被删除节点和代替节点(后继节点)，删除后继节点，从而转换为其他两种情况
        int k = deleted.key;
        deleted.key = replaced.key;
        replaced.key = k;
        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        deleteNode(replaced);
    }


    /**
     * 调整节点node，以达到平衡
     *
     * @param node 待调整节点(可能是删除的节点，也可能是剩余的节点)
     */
    private void fixDoubleBlack(Node node) {
        if (node == root)
            return;
        Node parent = node.parent;
        Node sibling = node.sibling();
        if (isRed(sibling)) {
            //case 3 被调整节点的兄弟是红色
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            //此时兄弟节点变为黑色，递归调用
            fixDoubleBlack(node);
            return;
        }
        //case 4:兄弟是黑，两侄子都是黑
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = Color.RED;
            if (isBlack(parent)) {
                fixRedRed(parent);
            } else {
                parent.color = Color.BLACK;
            }
            return;
        }
        //case 5:兄弟是黑，侄子有红色的
        //1.兄弟是左孩子，左侄子是红色
        if(sibling.isLeftChild() && isRed(sibling.left)){
            sibling.color = parent.color;
            parent.color = Color.BLACK;
            sibling.left.color = Color.BLACK;
            rightRotate(parent);
        }else if(sibling.isLeftChild() && isRed(sibling.right)){
            //LR
            sibling.right.color = parent.color;
            parent.color = Color.BLACK;
            leftRotate(sibling);
            rightRotate(parent);
        } else if (isRed(sibling.right)) {
            //RR
            sibling.color = parent.color;
            parent.color = Color.BLACK;
            sibling.right.color = Color.BLACK;
            leftRotate(parent);
        }else {
            //RL
            sibling.left.color = parent.color;
            parent.color = Color.BLACK;
            rightRotate(sibling);
            leftRotate(parent);
        }
    }

    /**
     * 根据key查找节点
     *
     * @param key 关键字
     * @return 查找到的节点，返回null表示没找到
     */
    private Node find(int key) {
        Node node = root;
        while (node != null && node.key != key) {
            if (node.key < key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node;
    }


    /**
     * 查找删除节点之后剩余的节点
     *
     * @param node 待删除节点
     * @return 删除节点之后剩余的节点
     */
    private Node findReplaced(Node node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null)
            return node.right;
        if (node.right == null)
            return node.left;
        //有两个孩子
        Node post = node.right;
        while (post.left != null) {
            post = post.left;
        }
        return post;
    }
}
