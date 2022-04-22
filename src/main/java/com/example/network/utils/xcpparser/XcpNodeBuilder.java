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
    // xcp文件的全量内容
    private String fileContext;
    // 文件字节流
    private BufferedReader bufferedReader;

    public XcpNodeBuilder(XcpFilter xcpFilter, XcpParser xcpParser, BufferedReader bufferedReader, String fileContext) {
        this.xcpFilter = xcpFilter;
        this.xcpParser = xcpParser;
        this.bufferedReader = bufferedReader;
        this.fileContext = fileContext;
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
        Pattern p = Pattern.compile("/begin\\s+\\s+(.*)/end\\s+\\S+");
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
    private void buildNodeNode(String str, XcpNode n) {
    }

    // 设置节点内容,节点的内容是删除了所有子节点以后剩下的部分
    private void buildNodeContext(String str, XcpNode n) {
        n.setAllContext(str);
    }

    // 通过递归生成完整节点树
    private void buildNodeTree(String str, XcpNode n) {
        // 读取并解析每行数据
        String line;
        try {
            // 读取并解析每行数据
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("读取行的数据   ************   " + line);

                // 正则匹配"/begin name"标签
                Pattern p = Pattern.compile("/begin\\s+\\S+.*");
                Matcher m = p.matcher(line);

                // 如果匹配到"/begin name"标签，则重新匹配"/begin name"到"/end name"(相同name)之间的完整内容
                String fatherName;

                if (m.find()) {

                    String nodeTitle = line;

                    // 去除标签行的前后空格，方便后续做正则匹配
                    line = line.trim();

                    Pattern p2 = Pattern.compile("\\s+");
                    fatherName = p2.split(line)[1];
                    System.out.println("标签节点的名称   ************   " + line);

                    // 构造父节点的基本信息并递归解析文本内容中的子节点,并将子节点存入到父节点下
                    Pattern pFather = Pattern.compile("/begin\\s+" + fatherName + "\\s+.*" + "/end" + "\\s+" + fatherName, Pattern.DOTALL);
                    Matcher mFather = pFather.matcher(fileContext);

                    System.out.println("匹配节点   " + pFather.pattern() + "   范围中的内容   ************   " );

                    // 匹配父节点及节点内的所有内容
                    if (mFather.find()) {
                        String fatherContext = mFather.group(0);

                        n.setTitle(nodeTitle);
                        n.setName(fatherName);
                        n.setAllContext(fatherContext);

                        System.out.println("YES匹配节点   " + pFather.pattern() + "   范围中的内容   ************   \r\n" + fatherContext);
                    } else {
                        System.out.println("NO匹配节点   " + pFather.pattern() + "   范围中的内容" );
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
