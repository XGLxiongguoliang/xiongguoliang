package com.example.network.utils.xcpparser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeBuilder {
    private Node root = new Node();
    private XcpParser parser;
    private XcpFilter filter;

    // 提供合适的过滤器和解析器
    public NodeBuilder(XcpParser parser, XcpFilter filter) {
        this.parser = parser;
        this.filter = filter;
    }

    public Node getRoot(String... regex) {
        String str;
        if (regex.length == 0) {
            str = filter.filter();
        } else {
            str = filter.filter(regex);
        }
        buildNodeTree(str, root);
        return root;
    }

    // 设置节点类型
    private void buildNodeName(String str, Node n) {
        Pattern p = Pattern.compile("</begin(.*?)>");
        Matcher m = p.matcher(str);
        if (m.find()) {
            String temp = m.group(1);
            String name = temp.split(" ")[1];
            n.setName(name);
        }
    }

    // 设置节点类型
    private void buildNodeStart(String str, Node n) {
        Pattern p = Pattern.compile("</begin.*?>");
        Matcher m = p.matcher(str);
        if (m.find()) {
            String temp = m.group();
            if (temp.startsWith("</begin")) {
                n.setStart(temp);
            }
        }
    }

    // 设置节点内容,节点的内容是删除了所有子节点字符串以后剩下的部分
    private void buildNodeText(String str, Node n) {
        Pattern p = Pattern.compile("<.*?>(.*)<.*?>");
        Matcher m = p.matcher(str);
        List<String> childrenDocs = parser.parser(str);
        if (m.find()) {
            String temp = m.group(1);
            for (String s : childrenDocs) {
                temp = temp.replaceAll(s, "");
            }
            n.setText(temp);
        }
    }

    // 通过递归生成完整节点树
    private void buildNodeTree(String str, Node n) {
        buildNodeStart(str, n);
        buildNodeName(str, n);
        buildNodeText(str, n);
        // 如果存在子节点则继续下面的操作
        if (!parser.parser(str).isEmpty()) {
            // 对每一个子节点都应该继续调用直到递归结束
            for (String temp : parser.parser(str)) {
                Node child = new Node();
                buildNodeStart(temp, child);
                buildNodeName(str, n);
                buildNodeText(temp, child);
                n.getChildNode().add(child);
                buildNodeTree(temp, child);
            }
        }
    }
}
