package binaryTree;

import java.util.LinkedList;

public class BinaryTree<E> {
    /*
    //前序遍历
    static void preOrder(TreeNode<?> root) {
        if (root == null) {
            //System.out.println("null");
            return;
        }
        System.out.print(root + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    static void inOrder(TreeNode<?> root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root + " ");
        inOrder(root.right);
    }

    //后序遍历
    static void postOrder(TreeNode<?> root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root + " ");
    }

     */


    //前序遍历
    static void preOrder(TreeNode<?> root) {
        TreeNode<?> cur = root;
        LinkedList<TreeNode<?>> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                System.out.print(cur + " ");
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode<?> node = stack.pop();
                cur = node.right;
            }
        }
    }

    //中序遍历
    static void inOrder(TreeNode<?> root) {
        TreeNode<?> cur = root;
        LinkedList<TreeNode<?>> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode<?> node = stack.pop();
                System.out.print(node + " ");
                cur = node.right;
            }
        }
    }

    //后序遍历
    static void postOrder(TreeNode<?> root) {
        TreeNode<?> cur = root; //当前处理的节点
        TreeNode<?> pop = null;//上一次弹栈的节点
        LinkedList<TreeNode<?>> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                //处理左子树
                stack.push(cur);
                cur = cur.left;
            } else {
                //处理右子树
                TreeNode<?> peek = stack.peek();
                //判断右子树是否处理完成
                if (peek.right == null || peek.right == pop) {
                    //右子树处理完成，即可出栈并打印
                    pop = stack.pop();
                    System.out.print(pop + " ");
                } else {
                    //处理右子树
                    cur = peek.right;
                }
            }
        }
    }

    //根据前序遍历和中序遍历来构造树
    public static <T> TreeNode<T> buildTree(T[] preorder, T[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1);
    }

    private static <T> TreeNode<T> build(T[] preorder, int l1, int e1, T[] inorder, int l2, int e2) {
        if (l1 > e1)
            return null;
        TreeNode<T> parent = new TreeNode<>(preorder[l1]);
        for (int i = l2; i <= e2; i++) {
            if (inorder[i].equals(parent.val)) {
                //中序遍历中当前父节点的索引为i，
                //那么中序遍历中下标从l2到i-1的元素就是左子树的中序遍历结果，共i-l2个元素
                //中序遍历中下标从i+1到e2的元素就是右子树的中序遍历结果，共e2-i个元素
                //前序遍历中下标从l1+1到i+l1-l2的i-l2个元素就是左子树的前序遍历结果
                //前序遍历中下标从i+l1-l2+1到e1的e2-i个元素就是右子树的前序遍历结果
                parent.left = build(preorder, l1 + 1, i + l1 - l2, inorder, l2, i - 1);
                parent.right = build(preorder, i + l1 - l2 + 1, e1, inorder, i + 1, e2);
                break;
            }
        }
        return parent;
    }


    //根据中序和后序遍历来构造树
    public static <T> TreeNode<T> createTree(T[] inOrder, T[] postOrder) {
        return create(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
    }

    private static <T> TreeNode<T> create(T[] inOrder, int l1, int e1, T[] postOrder, int l2, int e2) {
        if (l1 > e1)
            return null;
        TreeNode<T> parent = new TreeNode<>(postOrder[e2]);
        for (int i = l1; i <= e1; i++) {
            if(parent.val.equals(inOrder[i])){
                //中序遍历中父节点的下标为i
                //那么中序遍历中从l1到i-1的i-l1个元素即为父节点的左子树
                //中序遍历中从i+1到e1的e1-i个元素即为父节点的右子树
                //后序遍历中从l2到i-l1+l2-1的i-l1个元素即为左子树的后序遍历
                //后序遍历中从i-l1+l2到e2-1的e1-i个元素即为右子树的后序遍历
                parent.left = create(inOrder,l1,i-1,postOrder,l2,i-l1+l2-1);
                parent.right = create(inOrder,i+1,e1,postOrder,i-l1+l2,e2-1);
                break;
            }
        }
        return parent;
    }
}

