package com.example.network.utils.xcpparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XcpNode创建着
 */
public class XcpNodeBuilder {
    private XcpNode root = new XcpNode();
    // xcp文件过滤器
    private XcpFilter xcpFilter;
    // xcp文件解析器
    private XcpParser xcpParser;

    public XcpNodeBuilder(XcpParser xcpParser, XcpFilter xcpFilter) {
        this.xcpFilter = xcpFilter;
        this.xcpParser = xcpParser;
    }

    // 获取父节点
    public XcpNode getRoot(String... regex) {
        String str;
        if (regex.length == 0) {
            str = xcpFilter.filter();
        } else {
            str = xcpFilter.filter(regex);
        }

        buildNodeTree(str, root);

        return root;
    }

    // 设置节点标题
    private void buildNodeTitle(String str, XcpNode n) {
        Pattern p = Pattern.compile("/begin\\s+\\S+(.*)/end\\s+\\S+", Pattern.DOTALL);
        Matcher m = p.matcher(str);
        List<String> childrenDocs = xcpParser.parser(str);
        if (m.find()) {
            String temp = m.group(1);
            for (String s : childrenDocs) {
                temp = temp.replaceAll(s, "");
            }
            n.setAllContext(temp.trim());
        }
    }

    // 设置节点名称
    private void buildNodeName(String str, XcpNode n) {
        Pattern p = Pattern.compile("/begin.*?/end\\s+\\S+", Pattern.DOTALL);
        Matcher m = p.matcher(str);
        if (m.find()) {
            String temp = m.group();
            String s = temp.substring(1, temp.length() - 1).split(" ")[0];
            if (s.endsWith("/end\\s+\\S+")) {
                n.setTitle(s.substring(0, s.length() - 1));
            } else {
                n.setTitle(s.split(" ")[0]);
            }
        }
    }

    // 设置节点内容,节点的内容是删除了所有子节点以后剩下的部分
    private void buildNodeContext(String str, XcpNode n) {

        Pattern p = Pattern.compile("/begin(.*)/end\\s+\\S+", Pattern.DOTALL);
        Matcher m = p.matcher(str);
        List<String> childrenDocs = xcpParser.parser(str);
        if (m.find()) {
            String temp = m.group(1);
            for (String s : childrenDocs) {
                temp = temp.replaceAll(s, "");
            }
            n.setAllContext(str);
        }
    }

    // 通过递归生成完整节点树
    private void buildNodeTree(String str, XcpNode n) {
        buildNodeTitle(str, n);
        buildNodeContext(str, n);
        // 如果存在子节点则继续下面的操作
        if (!xcpParser.parser(str).isEmpty()) {
            // 对每一个子节点都应该继续调用直到递归结束
            for (String temp : xcpParser.parser(str)) {
                XcpNode child = new XcpNode();
                buildNodeTitle(temp, child);
                buildNodeContext(temp, child);
                n.getChildNodes().add(child);
                buildNodeTree(temp, child);
            }
        }
    }
}
