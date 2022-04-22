package com.example.network.utils.xcpparser;

import com.example.network.utils.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xcp文件解析接口实现类
 */
public class XcpParserImpl implements XcpParser {
    @Override
    public List<String> parser(String str) {
        List<String> childrenDocs = new ArrayList<>();
        // 捕获根节点中间的文本
        Pattern p = Pattern.compile("/begin(.*)/end");
        Matcher m = p.matcher(str);

        return childrenDocs;
    }

    public static void main(String[] args) {

        parseXcpFileBack("https://jfx.qdfaw.com/fs/group5/M00/3B/6C/CkXFtmJg-C-AWtcNAAAE46O_iOo095.a2l", "a2l");

        /*String xcpFileUrl =  "https://jfx.qdfaw.com/fs/group5/M00/3B/6C/CkXFtmJg-C-AWtcNAAAE46O_iOo095.a2l";
        String fileNameSuffix = "a2l";
        // 获取文件的字符流
        BufferedReader bufferedReader =  FileUtils.getBufferedReader(xcpFileUrl, fileNameSuffix);
        // 文件内容
        String fileContext = FileUtils.getFileContext(xcpFileUrl, fileNameSuffix);
        // 创建全量树对象
        XcpNode node = new XcpNode();
        parseXcpSon(fileContext, node);*/

    }

    public static void parseXcpSon(String fileContext, XcpNode node) {

    }

    public static String parseXcpFileBack(String xcpFileUrl, String fileNameSuffix) {
        // 获取文件的字符流
        BufferedReader bufferedReader =  FileUtils.getBufferedReader(xcpFileUrl, fileNameSuffix);
        // 文件内容
        String fileContext = FileUtils.getFileContext(xcpFileUrl, fileNameSuffix);
        System.out.println("解析的Xcp文件全部内容   ************   \r\n" + fileContext);

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
                        System.out.println("YES匹配节点   " + pFather.pattern() + "   范围中的内容   ************   \r\n" + fatherContext);
                    } else {
                        System.out.println("NO匹配节点   " + pFather.pattern() + "   范围中的内容" );
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //@Override
    public static String parseXcpFile(String xcpFileUrl, String fileNameSuffix) {
        // 获取文件的字符流
        BufferedReader bufferedReader =  FileUtils.getBufferedReader(xcpFileUrl, fileNameSuffix);
        // 文件内容
        String fileContext = FileUtils.getFileContext(xcpFileUrl, fileNameSuffix);
        System.out.println("解析的Xcp文件全部内容   ************   \r\n" + fileContext);

        // 创建全量树对象
        XcpNode rootNode = new XcpNode();

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

                        rootNode.setTitle(nodeTitle);
                        rootNode.setName(fatherName);
                        rootNode.setAllContext(fatherContext);

                        analysisNodes(rootNode);

                        System.out.println("YES匹配节点   " + pFather.pattern() + "   范围中的内容   ************   \r\n" + fatherContext);
                    } else {
                        System.out.println("NO匹配节点   " + pFather.pattern() + "   范围中的内容" );
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void analysisNodes(XcpNode rootNode) {

    }
}
