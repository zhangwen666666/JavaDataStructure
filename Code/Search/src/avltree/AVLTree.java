package avltree;

import java.util.LinkedList;

/**
 * 自平衡二叉树：AVL树
 */
public class AVLTree {
    /**
     * AVL树的节点类
     */
    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;//高度(刚创建出来的节点高度是1)

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    AVLNode root = null;

    public AVLTree() {
    }

    public AVLTree(AVLNode root) {
        this.root = root;
    }

    /**
     * 求节点的高度
     *
     * @param node 节点
     * @return 高度
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }


    /**
     * 更新节点的高度(新增，删除，旋转时)
     *
     * @param node 节点
     */
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }


    /**
     * @param node 节点
     * @return 平衡因子(左子树高度 - 右子树高度)
     * 返回值为-1,0,1表示结点是平衡的
     */
    private int balanceFactor(AVLNode node) {
        return height(node.left) - height(node.right);
    }


    /**
     * 将子树的根节点左旋 解决RR失衡问题
     *
     * @param node 需要旋转的子树的根节点
     * @return 旋转之后的树
     */
    private AVLNode leftRotate(AVLNode node) {
        /*
          1. 将根节点的右孩子rightChild记录下来
          2. 根节点的右孩子的左孩子leftChild.right做为根节点右孩子
          3. 原来的根节点node做为原来根节点的右孩子rightChild的左孩子
          4. 原来根节点的右孩子做为新的根节点并返回
          5. 在返回之前需要更新原来根节点的高度和新的根节点的高度
             (这里需要注意，要先更新原来根节点的高度，因为原来的根节点是新的根节点的子树，
             在更新新的根节点的高度时会用到其子树原根节点的高度)
         */
        AVLNode rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }


    /**
     * 将子树的根节点右旋 解决LL失衡问题
     *
     * @param node 需要旋转的子树的根节点
     * @return 旋转之后的树
     */
    private AVLNode rightRotate(AVLNode node) {
        /*
          1. 将根节点的左孩子leftChild记录下来
          2. 根节点的左孩子的右孩子leftChild.right做为根节点左孩子
          3. 原来的根节点node做为原来根节点的左孩子leftChild的右孩子
          4. 原来根节点的左孩子做为新的根节点并返回
          5. 在返回之前需要更新原来根节点的高度和新的根节点的高度
             (这里需要注意，要先更新原来根节点的高度，因为原来的根节点是新的根节点的子树，
             在更新新的根节点的高度时会用到其子树原根节点的高度)
         */
        AVLNode leftChild = node.left; //根节点的左孩子
        node.left = leftChild.right;
        leftChild.right = node;
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }


    /**
     * 将左子树左旋，在将根节点右旋 解决LR失衡问题
     *
     * @param node 失衡的节点
     * @return 旋转之后的根节点
     */
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }


    /**
     * 将右子树右旋，在将根节点左旋 解决RL失衡问题
     *
     * @param node 失衡的节点
     * @return 旋转之后的根节点
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }


    /**
     * 检查节点是否失衡，如果失衡，则进行调整
     *
     * @param node 待检查节点
     * @return 调整后的节点
     */
    private AVLNode balance(AVLNode node) {
        if (node == null) return null;
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1 && balanceFactor(node.left) >= 0) {
            //node节点的左子树更高，且左子树中也是左子树更高或相等
            //对应LL失衡情况
            return rightRotate(node);
        } else if (balanceFactor > 1 && balanceFactor(node.left) < 0) {
            //node节点的左子树更高，但左子树中是右子树更高
            //对应LR失衡情况
            return leftRightRotate(node);
        } else if (balanceFactor < -1 && balanceFactor(node.right) > 0) {
            //node节点的右子树更高，但右子树中是左子树更高
            //对应RL失衡情况
            return rightLeftRotate(node);
        } else if (balanceFactor < -1 && balanceFactor(node.right) <= 0) {
            //node节点的右子树更高，且右子树中也是右子树更高或相等
            //对应RR失衡情况
            return leftRotate(node);
        }
        return node;
    }


    /**
     * 向AVL树中新增元素
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, Object value) {
        if (root == null) {
            root = new AVLNode(key, value);
            return;
        }
        AVLNode cur = root;
        AVLNode parent = null;
        LinkedList<AVLNode> stack = new LinkedList<>();//记录来时的路
        while (cur != null && cur.key != key) {
            parent = cur;
            stack.push(parent);
            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur == null) {
            //树中没有节点的关键字为key，则创建新节点
            if (key < parent.key) {
                parent.left = new AVLNode(key, value);
            } else {
                parent.right = new AVLNode(key, value);
            }
        } else {
            //树中已经有节点的关键字为key，则修改其对应的value值
            cur.value = value;
        }
        while (!stack.isEmpty()) {
            AVLNode pop = stack.pop();
            updateHeight(pop);//更新高度
            if (stack.isEmpty()) {
                root = balance(pop);//平衡根节点时，根节点可能会修改
            } else {
                AVLNode peek = stack.peek();
                //平衡pop节点时，pop节点可能会被修改，需要更新父子关系
                pop = balance(pop);
                if (pop.key > peek.key) peek.right = pop;
                else peek.left = pop;
            }
        }
    }

    /**
     * 前序遍历
     *
     * @return 前序遍历的结果，以String数组的形式返回，每个元素表示key=value
     */
    public String[] preOrder() {
        if (root == null) return null;
        StringBuilder s = new StringBuilder();
        LinkedList<AVLNode> stack = new LinkedList<>();
        AVLNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                s.append(cur.key).append("=").append(cur.value).append(",");
                stack.push(cur);
                cur = cur.left;
            } else {
                AVLNode pop = stack.pop();
                cur = pop.right;
            }
        }
        String string = s.toString();
        return string.split(",");
    }


    /**
     * 根据给定的键删除键值对
     *
     * @param key 键
     */
    public void remove(int key) {
        root = removeNode(root,key);
    }

    private AVLNode removeNode(AVLNode node, int key) {
        if (node == null) return null;
        if (node.key < key) {
            node.right = removeNode(node.right, key);
        } else if (node.key > key) {
            node.left = removeNode(node.left, key);
        } else {
            //当前的node就是要删除的节点
            //1.最多只有一个孩子
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                //2.两个孩子都有
                // 找node的后继节点
                AVLNode post = node.right;
                while (post.left != null) {
                    post = post.left;
                }
                post.right = removeNode(node.right, post.key);
                post.left = node.left;
                node = post;
            }
        }
        if(node == null)
            return null;
        updateHeight(node);
        return balance(node);
    }
}
