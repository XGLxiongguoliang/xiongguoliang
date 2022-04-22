package com.example.network.utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件处理工具类
 */
public class FileUtils {

    private static final String TEMP = "temp";

    public static BufferedReader getBufferedReader(String fileUrl, String fileNameSuffix) {
        InputStreamReader inputStreamReader = null;

        //解析a2l文件
        InputStream is;
        OutputStream os;

        int a = 0;
        int b;

        try {
            // 通过文件地址获取字节输入流
            is = getInputStream(fileUrl, null, null);
            // 创建一个临时文件
            File dbcFile = File.createTempFile(TEMP, fileNameSuffix);
            // 如果文件已存在，则先删除
            dbcFile.deleteOnExit();
            // 创建文件输出流
            os = new FileOutputStream(dbcFile);

            // 将文件中的内容写入文件输出流中,一次读取1k
            byte[] buffer = new byte[1024];
            while ((b = is.read(buffer)) != -1) {
                a = a + b;
                os.write(buffer, 0, b);
            }

            os.flush();
            os.close();
            is.close();

            inputStreamReader = new InputStreamReader(new FileInputStream(dbcFile), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BufferedReader(inputStreamReader);
    }

    public static String getFileContext(String fileUrl, String fileNameSuffix) {
        InputStreamReader inputStreamReader;

        //解析a2l文件
        InputStream is;
        OutputStream os;

        int a = 0;
        int b;

        StringBuffer fileContext = new StringBuffer();

        try {
            // 通过文件地址获取字节输入流
            is = getInputStream(fileUrl, null, null);
            // 创建一个临时文件
            File dbcFile = File.createTempFile(TEMP, fileNameSuffix);
            // 如果文件已存在，则先删除
            dbcFile.deleteOnExit();
            // 创建文件输出流
            os = new FileOutputStream(dbcFile);

            // 将文件中的内容写入文件输出流中,一次读取1k
            byte[] buffer = new byte[1024];
            while ((b = is.read(buffer)) != -1) {
                a = a + b;
                os.write(buffer, 0, b);
            }

            os.flush();
            os.close();
            is.close();

            inputStreamReader = new InputStreamReader(new FileInputStream(dbcFile), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContext.append(line + "" + "\r\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContext.toString();
    }

    /**
     * 通过文件url得到输入流
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static InputStream getInputStream(String url, String param, Map<String, String> headers) {
        InputStream result = null;
        URL uri;
        HttpURLConnection conn;

        try {
            uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (!StringUtils.isEmpty(param)) {
                OutputStream os = conn.getOutputStream();
                os.write(param.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

            conn.connect();

            int code = conn.getResponseCode();

            if (code == 200) {
                result = conn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
