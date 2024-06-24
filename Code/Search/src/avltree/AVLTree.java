package avltree;

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
        node.right = leftRotate(node.right);
        return leftRotate(node);
    }


    /**
     * 检查节点是否失衡，如果失衡，则进行调整
     *
     * @param node 待检查节点
     * @return 调整后的节点
     */
    private AVLNode balance(AVLNode node) {
        if (node == null)
            return null;
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
        if(root == null){
            root = new AVLNode(key,value);
            return;
        }
        AVLNode cur = root;
        AVLNode parent = null;
        while (cur != null && cur.key != key) {
            parent = cur;
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
                parent.right = new AVLNode(key,value);
            }
        } else {
            //树中已经有节点的关键字为key，则修改其对应的value值
            cur.value = value;
        }
    }
}
