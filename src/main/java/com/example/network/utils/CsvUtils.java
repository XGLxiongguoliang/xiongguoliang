package com.example.network.utils;

import com.example.network.enums.SPNMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CsvUtils {

    private static final String FIELD = "\\w+=(\\-|\\+)?\\d+(\\.\\d+)?";

    /**
     * 解析csv文件并转成bean（方法一）
     *
     * @param file
     * @return
     */
    public static List<CsvFile> getCsvDataMethod1(MultipartFile file) {
        ArrayList<CsvFile> csvFileList = new ArrayList<>();

        InputStreamReader in = null;
        String s = null;
        try {
            in = new InputStreamReader(file.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(in);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                CsvFile csvFile = new CsvFile();
                csvFile.setName(splitResult(split[0]));
                csvFile.setTitle(splitResult(split[1]));
                csvFile.setNumber(splitResult(split[2]));
                csvFile.setType(splitResult(split[3]));
                csvFile.setPersonnel(splitResult(split[4]));
                csvFile.setTime(splitResult(split[5]));
                csvFileList.add(csvFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFileList;
    }

    /**
     * 解析csv文件并转成bean（方法二）
     *
     * @param file csv文件
     * @return 数组
     */
    public static List<String[]> getCsvDataMethod2(MultipartFile file) {

        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(file.getInputStream(), "utf-8"))).build();
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                String[] next = iterator.next();
                //去除第一行的表头，从第二行开始
                //if (i >= 1) {}

                list.add(next);
                i++;
            }
            return list;
        } catch (Exception e) {
            System.out.println("CSV文件读取异常");
            return list;
        }
    }

    /**
     * 解析csv文件并转成bean（方法二） 方法的增强，解析某个表头字段（数据是多个key-value），分离成新的多个字段
     *
     * @param file csv文件
     * @return 数组
     */
    public static Map<String, Object> getCsvDataMethod2Strong(MultipartFile file) {

        Map<String, Object> resultMap = new HashMap<>();

        // 重新解析数据，将staticsdatamap字段中的数据，解析成各个不同的字段并重新命名中文名称
        // 为了避免staticsdatamap中的各个字段数量不同导致表头不统一，先分析出所有的字段，其数据中没有的字段，则补空
        List<String> header = new ArrayList<>();
        int staticsdatamapIndex = 0;
        // 新增的表头字段map
        Map<String, String> staticsdatamapAttributeMap = new HashMap<>();
        // 新增的表头字段属性值map
        Map<Integer, Map<String, String>> staticsdatamapAttributeValueMap = new HashMap<>();

        List<List<String>> list = new ArrayList<>();
        int i = 0;
        try {
            CSVReader csvReader = new CSVReaderBuilder(
                    new BufferedReader(
                            new InputStreamReader(file.getInputStream(), "utf-8"))).build();
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                List<String> next = new ArrayList(Arrays.asList(iterator.next()));

                //第一行的表头，数据从第二行开始
                if (i == 0) {
                    header = next;
                    // 分析表头字段是staticsdatamap的下标值
                    for (int j = 0; j < header.size(); j++) {
                        if ("staticsdatamap".equals(header.get(j))) {
                            staticsdatamapIndex = j;
                        }
                    }
                } else {
                    // 获取staticsdatamap字段对应的值
                    String staticsdatamapValue = next.get(staticsdatamapIndex);
                    // 解析staticsdatamap字段对应的值，分析出各个字段值
                    analysisMap(staticsdatamapAttributeMap, staticsdatamapAttributeValueMap, staticsdatamapValue, i - 1);
                    list.add(next);

                    log.info("解析完第 {} 条数据", i);
                }

                i++;
            }

            // 根据staticsdatamap解析出来的表头字段以及各个数据对应的数值，重新组装成一个新的List
            for (Map.Entry<String, String> entry : staticsdatamapAttributeMap.entrySet()) {
                header.add(entry.getValue());

                for (int j = 0; j < list.size(); j++) {
                    if (staticsdatamapAttributeValueMap.containsKey(j)) {
                        list.get(j).add(staticsdatamapAttributeValueMap.get(j).get(entry.getKey()));
                    } else {
                        list.get(j).add("");
                    }
                }
            }

            resultMap.put("header", header);
            resultMap.put("data", list);

            return resultMap;
        } catch (Exception e) {
            System.out.println("CSV文件读取异常");
            return resultMap;
        }
    }

    private static void analysisMap(Map<String, String> staticsdatamapAttributeMap,
                                    Map<Integer, Map<String, String>> staticsdatamapAttributeValueMap,
                                    String staticsdatamapValue,
                                    int index) {
        // 正则匹配出所有的字段
        Pattern pattern = Pattern.compile(FIELD);
        Matcher matcher = pattern.matcher(staticsdatamapValue);
        while (matcher.find()) {
            // 获取单个匹配项，格式为：spn=value
            String fieldValue = matcher.group();
            // 将单个匹配项拆分成一个list
            List<String> keyAndValueList = Arrays.asList(fieldValue.split("="));
            // 从SPNMap中查找中文对照关系,如果找到了，则重新创建新的表头字段名： 中文名(SPN)，如果没有，则剔除数据
            if (SPNMap.getInstance().getMap().containsKey(keyAndValueList.get(0))) {
                String newFieldName = SPNMap.getInstance().getMap().get(keyAndValueList.get(0)) + "(" + keyAndValueList.get(0) + ")";
                staticsdatamapAttributeMap.put(keyAndValueList.get(0), newFieldName);

                if (CollectionUtils.isEmpty(staticsdatamapAttributeValueMap.get(index))) {
                    Map<String, String> tempMap = new HashMap<>();
                    tempMap.put(keyAndValueList.get(0), keyAndValueList.get(1));
                    staticsdatamapAttributeValueMap.put(index, tempMap);
                } else {
                    staticsdatamapAttributeValueMap.get(index).put(keyAndValueList.get(0), keyAndValueList.get(1));
                }
            }
        }
    }

    /**
     * 解析csv文件并转成bean（方法三）
     *
     * @param file  csv文件
     * @param clazz 类
     * @param <T>   泛型
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvDataMethod3(MultipartFile file, Class<T> clazz) {
        InputStreamReader in = null;
        CsvToBean<T> csvToBean = null;
        try {
            in = new InputStreamReader(file.getInputStream(), "utf-8");
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            csvToBean = new CsvToBeanBuilder<T>(in).withMappingStrategy(strategy).build();
        } catch (Exception e) {
            log.error("数据转化失败");
            return null;
        }
        return csvToBean.parse();
    }

    private static String splitResult(String once) {
        String result = "";
        for (int i = 0; i < once.length(); i++) {
            if (once.charAt(i) != '"') {
                result += once.charAt(i);
            }
        }
        return result;
    }

    /**
     * CSV文件生成方法
     * @param head 文件头
     * @param dataList 数据列表
     * @param outPutPath 文件输出路径
     * @param filename 文件名
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GBK"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            int i = 0;
            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);

                i ++;

                log.info("成功写入CSV第 {} 条数据", i);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data == null ? "" : data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
