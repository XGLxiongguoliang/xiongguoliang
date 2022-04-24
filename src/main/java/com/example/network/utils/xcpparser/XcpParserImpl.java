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
        Pattern p = Pattern.compile("/begin\\s+\\S+(.*)/end\\s+\\S+");
        Matcher m = p.matcher(str);

        if (m.find()) {
            String inner = m.group(1);
            // 匹配节点字符串
            p = Pattern.compile("/begin(.*?)/end\\s+\\S+");
            m = p.matcher(inner);
            while (m.find()) {
                String s1 = m.group(1);
                // 计算起始字符数
                int start = m.end() - m.group().length();
                // 如果捕获到未闭合节点则index++，如果捕获到闭合节点则index--
                int index = 1;
                while (m.find()) {
                    String s2 = m.group(1);
                    if (!s2.startsWith("/begin") && !s2.endsWith("/end\\s+\\S+")) {
                        index++;
                    } else if (s2.startsWith("/begin")) {
                        index--;
                    }
                    // 找到符合条件的闭合节点则循环终止
                    if (index == 0) {
                        break;
                    }
                }
                // 计算结束字符数
                int end = m.end();
                // 截取对应字符串
                childrenDocs.add(inner.substring(start, end));
            }
        }

        return childrenDocs;
    }

    public static void main(String[] args) {

        String aaa = "/*********************************************************/\n" +
                "  /* Definitions for XCP                                   */\n" +
                "  /*********************************************************/\n" +
                "\n" +
                "  struct Protocol_Layer {\n" +
                "\tuint;     /* XCP protocol layer version, current 0x100 */\n" +
                "\tuint;     /* T1 [ms] */\n" +
                "\tuint;     /* T2 [ms] */\n" +
                "\tuint;     /* T3 [ms] */\n" +
                "\tuint;     /* T4 [ms] */\n" +
                "\tuint;     /* T5 [ms] */\n" +
                "\tuint;     /* T6 [ms] */\n" +
                "\tuint;     /* T7 [ms] */\n" +
                "\tuchar;    /* MAX_CTO */\n" +
                "\tuint;     /* MAX_DTO */\n" +
                "\tenum {\n" +
                "\t  \"BYTE_ORDER_MSB_LAST\" = 0,\n" +
                "\t  \"BYTE_ORDER_MSB_FIRST\" = 1\n" +
                "\t};\n" +
                "\tenum {\n" +
                "\t  \"ADDRESS_GRANULARITY_BYTE\" = 1,\n" +
                "\t  \"ADDRESS_GRANULARITY_WORD\" = 2,\n" +
                "\t  \"ADDRESS_GRANULARITY_DWORD\" = 4\n" +
                "\t};\n" +
                "\t\n" +
                "\t/begin RECORD_LAYOUT SSV__FLOAT64_IEEE_S \n" +
                "\t  AXIS_PTS_X 1 FLOAT64_IEEE INDEX_INCR DIRECT\n" +
                "\t  \n" +
                "\t  /begin ASAAA\n" +
                "\t\tAXIS_PTS_X 1 FLOAT32_IEEE INDEX_INCR DIRECT\n" +
                "\t  /end ASAAA\n" +
                "\t/end RECORD_LAYOUT";

        // 构造父节点的基本信息并递归解析文本内容中的子节点,并将子节点存入到父节点下
        Pattern pFather = Pattern.compile("[`/begin\\s+\\S+(.*)/end\\s+\\S+]", Pattern.DOTALL);
        Matcher mFather = pFather.matcher(aaa);

        while (mFather.find()) {
            System.out.println("num---" + mFather.groupCount());
            System.out.println(mFather.group(0));
        }

        //parseXcpFileBack("https://jfx.qdfaw.com/fs/group5/M00/3B/6C/CkXFtmJg-C-AWtcNAAAE46O_iOo095.a2l", "a2l");

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
                        int start = m.start();
                        int end = m.end();
                        System.out.println("start --" + start + "  end--" + end);

                        String fatherContext = mFather.group(0);
                        System.out.println("YES匹配节点   " + pFather.pattern() + "   范围中的内容   ************   \r\n" + fatherContext);

                        mFather.find();

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
}
