package com.example.network.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class ExcelUtils<T> {
    /**
     *  2007 版本以上 最大支持1048576行
     */
    public final static String EXCEl_FILE_2007 = "2007";

    /**
     *  2003 版本 最大支持65536 行
     */
    public final static String EXCEL_FILE_2003 = "2003";

    private static final Pattern NUMBER = Pattern.compile("^//d+(//.//d+)?$");

    /**
     * 上传路径
     */
    private String uploadUrl;

    public ExcelUtils(String url) {
        this.uploadUrl = url;
    }

    /**
     * <p>
     * 导出无头部标题行Excel <br>
     * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
     * </p>
     *
     * @param title    表格标题
     * @param dataset  数据集合
     * @param fileName 文件名(不含后缀)
     * @param version  2003 或者 2007，不传时默认生成2003版本
     */
    public Map exportExcel(String title, Collection<T> dataset, String fileName, String version, boolean flag) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = title;
        }
        File file;
        if (StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())) {
            fileName = fileName + ".xls";
            file = new File(fileName);
            try {
                exportExcel2003(title, new String[0], dataset, new FileOutputStream(file), "yyyy-MM-dd HH:mm:ss",flag);
            } catch (FileNotFoundException e) {
                log.error("excel file generate fail!", e);
            }
        } else {
            fileName = fileName + ".xlsx";
            file = new File(fileName);
            try {
                exportExcel2007(title, new String[0], dataset, new FileOutputStream(file), "yyyy-MM-Rdd HH:mm:ss",flag);
            } catch (FileNotFoundException e) {
                log.error("excel file generate fail!", e);
            }
        }
        Map result = this.uploadExcel(file);
        file.delete();
        return result;
    }

    /**
     * <p>
     * 导出带有头部标题行的Excel <br>
     * 时间格式默认：yyyy-MM-dd hh:mm:ss <br>
     * </p>
     *
     * @param title    表格标题
     * @param headers  头部标题集合
     * @param dataset  数据集合
     * @param fileName 输出流
     * @param version  2003 或者 2007，不传时默认生成2003版本
     */
    public Map exportExcel(String title, String[] headers, Collection<T> dataset, String fileName, String version, boolean flag) {
        if (StringUtils.isEmpty(fileName)) {
            fileName = title;
        }
        File file;
        if (StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())) {
            fileName = fileName + ".xls";
            file = new File(fileName);
            try {
                exportExcel2003(title, headers, dataset, new FileOutputStream(file), "yyyy-MM-dd HH:mm:ss",flag);
            } catch (FileNotFoundException e) {
                log.error("excel file generate fail!", e);
            }
        } else {
            fileName = fileName + ".xlsx";
            file = new File(fileName);
            try {
                exportExcel2007(title, headers, dataset, new FileOutputStream(file), "yyyy-MM-dd HH:mm:ss",flag);
            } catch (FileNotFoundException e) {
                log.error("excel file generate fail!", e);
            }
        }
        Map result = this.uploadExcel(file);
        file.delete();
        return result;
    }

    /**
     * <p>
     * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
     * 此版本生成2007以上版本的文件 (文件后缀：xlsx)
     * </p>
     *
     * @param title   表格标题名
     * @param headers 表格头部标题集合
     * @param dataset 需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
     *                JavaBean属性的数据类型有基本数据类型及String,Date
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void exportExcel2007(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern,boolean flag) {
        // 声明一个工作薄
//        XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(new Color(java.awt.Color.gray));
//        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
//        font.setColor(new Color(java.awt.Color.BLACK));
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(new Color(java.awt.Color.WHITE));
//        style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        Row row = null;
        //产生标题头
        if(flag) {
            this.createNormalHead2007(sheet, style, title, headers.length);
            row = sheet.createRow(1);
        }else{
            row = sheet.createRow(0);
        }


        Cell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
        }
        sheet.autoSizeColumn((short)0);
        // 遍历集合数据，产生数据行
        this.buildSheetData(sheet, dataset, style2, pattern,flag);
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>
     * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
     * 此方法生成2003版本的excel,文件名后缀：xls <br>
     * </p>
     *
     * @param title   表格标题名
     * @param headers 表格头部标题集合
     * @param dataset 需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
     *                JavaBean属性的数据类型有基本数据类型及String,Date
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void exportExcel2003(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern,boolean flag) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //产生标题头
        this.createNormalHead2003(sheet, style, title, headers.length);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(1);
        HSSFCell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(new HSSFRichTextString(headers[i]));
        }

        // 遍历集合数据，产生数据行
        this.buildSheetData(sheet, dataset, style2, pattern,flag);
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建通用EXCEL头部
     *
     * @param headString 头部显示的字符
     * @param colSum     该报表的列数
     */
    public void createNormalHead2007(Sheet sheet, CellStyle style, String headString, int colSum) {
        Row row = sheet.createRow(0);

        // 设置第一行
        Cell cell = row.createCell(0);
        row.setHeight((short) 500);

        // 定义单元格为字符串类型
        cell.setCellStyle(style);
        cell.setCellValue(new XSSFRichTextString(headString));

        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) colSum - 1));
    }

    /**
     * 创建通用EXCEL头部
     *
     * @param headString 头部显示的字符
     * @param colSum     该报表的列数
     */
    public void createNormalHead2003(HSSFSheet sheet, HSSFCellStyle style, String headString, int colSum) {
        HSSFRow row = sheet.createRow(0);

        // 设置第一行
        HSSFCell cell = row.createCell(0);
        row.setHeight((short) 500);

        // 定义单元格为字符串类型
        cell.setCellStyle(style);
        cell.setCellValue(new HSSFRichTextString(headString));

        // 指定合并区域
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) colSum - 1));
    }

    public Map uploadExcel(File file) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("name", file.getName());
        param.add("file", new FileSystemResource(file));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uploadUrl, param, Map.class);
    }

    /**
     * 装载数据
     *
     * @param sheet   表格对象
     * @param dataset 数据对象
     * @param style   数据样式
     * @param pattern 时间格式
     */
    private void buildSheetData(Sheet sheet, Collection<T> dataset, CellStyle style, String pattern,boolean flag) {
        Row row;
        T t;
        Field[] fields;
        Field field;
        String fieldName;
        String getMethodName;
        Cell cell;
        Class tCls;
        Method getMethod;
        Object value;
        int index;
        if(flag) {
            index = 1;
        }else{
            index = 0;
        }
        Iterator<T> it = dataset.iterator();
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            t = (T) it.next();
            // 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
            fields = t.getClass().getDeclaredFields();
            if (fields.length > 0){
                for (int i = 0; i < fields.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellStyle(style);
                    field = fields[i];
                    fieldName = field.getName();
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        tCls = t.getClass();
                        getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        value = getMethod.invoke(t, new Object[]{});
                        // 判断值的类型后进行强制类型转换
                        this.setCellValue(cell, value, pattern);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }
            }else if (t.getClass().isArray() && !t.getClass().isEnum()){
                String[] data = (String[]) t;
                for (int i = 0; i < data.length; i++) {
                    try {
                        cell = row.createCell(i);
                        cell.setCellStyle(style);
                        // 判断值的类型后进行强制类型转换
                        if (data[i] != null){
                            this.setCellValue(cell, data[i], pattern);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }
            }
        }
    }

    /**
     * 单元格数据处理
     *
     * @param cell    单元格对象
     * @param value   数据
     * @param pattern 时间格式
     */
    private void setCellValue(Cell cell, Object value, String pattern) {
        RichTextString richString;
        String textValue = null;
        Matcher matcher;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // 判断值的类型后进行强制类型转换
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Float) {
            textValue = String.valueOf((Float) value);
            cell.setCellValue(textValue);
        } else if (value instanceof Double) {
            textValue = String.valueOf((Double) value);
            cell.setCellValue(textValue);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        if (value instanceof Boolean) {
            textValue = "是";
            if (!(Boolean) value) {
                textValue = "否";
            }
        } else if (value instanceof Date) {
            textValue = sdf.format((Date) value);
        } else {
            // 其它数据类型都当作字符串简单处理
            if (value != null) {
                textValue = value.toString();
            }
        }
        if (textValue != null) {
            matcher = NUMBER.matcher(textValue);
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                if (cell instanceof HSSFCell) {
                    richString = new HSSFRichTextString(textValue);
                } else {
                    richString = new XSSFRichTextString(textValue);
                }
                cell.setCellValue(richString);
            }
        }
    }
}
