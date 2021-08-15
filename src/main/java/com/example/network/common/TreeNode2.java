package com.example.network.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.network.common
 * @description s
 * @auther Mr.Xiong
 * @create 2021-05-17 10:38:49
 */
public class TreeNode2 {
    private String id;
    private List<TreeNode2> children;

    public TreeNode2(String id, List<TreeNode2> children) {
        this.id = id;
        this.children = children;
    }

    public void addOne(TreeNode2 TreeNode2) {
        children.add(TreeNode2);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode2> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode2> children) {
            this.children = children;
        }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", childrenSize=" + children.size() +
                '}';
    }

    public static void main(String[] args) {
        TreeNode2 node1a = new TreeNode2("1a", new ArrayList<>());
        TreeNode2 node1b = new TreeNode2("1b", new ArrayList<>());
        TreeNode2 node1b2a = new TreeNode2("1b2a", new ArrayList<>());
        TreeNode2 node1b2b = new TreeNode2("1b2b", new ArrayList<>());
        node1b.addOne(node1b2a);
        node1b.addOne(node1b2b);
        TreeNode2 node1c = new TreeNode2("1c", new ArrayList<>());
        TreeNode2 node2c = new TreeNode2("2c", new ArrayList<>());
        TreeNode2 node3c = new TreeNode2("3c", new ArrayList<>());
        TreeNode2 node4c = new TreeNode2("4c", new ArrayList<>());
        TreeNode2 node5c = new TreeNode2("5c", new ArrayList<>());
        node1c.addOne(node2c);
        node2c.addOne(node3c);
        node3c.addOne(node4c);
        node4c.addOne(node5c);

        //模拟原始数据
        List<TreeNode2> treeNodeList = new ArrayList<>();
        treeNodeList.add(node1a);
        treeNodeList.add(node1b);
        treeNodeList.add(node1c);

        for (int i = 0; i < treeNodeList.size(); i++) {
            treeNodeList.addAll(treeNodeList.get(i).getChildren());
        }

        System.out.println(treeNodeList);
    }
}
