package deque;

import java.util.*;
import java.util.Deque;

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root == null)
            return lists;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        int size = 1; //当前层有几个节点
        boolean isOdd = true; //是否是奇数层
        while(!deque.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int count = 0; // 下一层的节点数
            for(int i = 0;i<size;i++){
                TreeNode node = deque.poll();
                if(node.left!=null){
                    deque.offer(node.left);
                    count++;
                }
                if(node.right!=null){
                    deque.offer(node.right);
                    count++;
                }
                if(isOdd)
                    level.offerLast(node.val);//奇数层从尾部添加
                else
                    level.offerFirst(node.val);//偶数层从头部添加
            }
            size = count;
            isOdd = !isOdd;
            lists.add(level);
        }
        return lists;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
}
