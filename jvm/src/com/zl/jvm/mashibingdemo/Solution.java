package com.zl.jvm.mashibingdemo;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;




public class Solution {


    public static void main(String[] args){
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");

        System.out.println(str1 == str2);//false
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
    }
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        Queue<TreeNode>queue = new LinkedList<TreeNode>();
        //Queue<TreeNode>queue2 = new LinkedList<TreeNode>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        queue.offer(pRoot);
        int num = 1,next = 0;
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            list.add(temp.val);
            num--;
            if(temp.left != null){
                queue.offer(temp);
                next++;
            }
            if(temp.right != null){
                queue.offer(temp.right);
                next++;
            }
            if(num == 0){
                res.add(list);
                list.clear();
                num = next;
                next = 0;
            }
        }
        return res;

    }

}