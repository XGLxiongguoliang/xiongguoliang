package com.example.network.utils.xcpparser;

import java.io.Serializable;
import java.util.*;

public class Node implements Serializable {
    // 可以对Node对象持久化保存
    private static final long serialVersionUID = 1L;
    private int id;
    // 节点开始所在行
    private String start;
    // 节点结束所在行
    private String name;
    // 节点内容，去除所有子节点后的内容
    private String text;
    // 子节点集合
    private List<Node> childNodes = new LinkedList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Node> getChildNode() {
        return childNodes;
    }

    public void setChildNode(List<Node> childNode) {
        this.childNodes = childNode;
    }

    // 输出完整的节点字符串也用到了递归
    @Override
    public String toString() {
        if (childNodes.isEmpty() && text == null) {
            return start + "\n" + "</end " + name + ">\n";
        } else if (childNodes.isEmpty() && text != null && text != "") {
            return start + "\n" + text + "\n" + "</end " + name + ">\n";
        } else {
            StringBuffer buff = new StringBuffer();
            buff.append(start + "\n");
            if (!text.isEmpty()) {
                buff.append(text + "\n");
            }
            for (Node n : childNodes) {
                buff.append(n.toString());
            }
            buff.append("</end " + name + ">\n");
            return buff.toString();
        }
    }
}
