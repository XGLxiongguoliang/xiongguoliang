package com.example.network.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program com.example.network.temp
 * @description 导入mysql数据
 * @auther Mr.Xiong
 * @create 2020-05-15 09:
 */
public class MysqlLearn {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://127.0.0.1:3306/navinfo_tsp?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
        final String name = "com.mysql.cj.jdbc.Driver";
        final String user = "root";
        final String password = "123456";
        Connection conn = null;
        Class.forName(name);
        //指定连接类型
        conn = DriverManager.getConnection(url, user, password);
        //获取连接
        if (conn != null) {
            System.out.println("获取连接成功");
            insertFaultRecord(conn);
        } else {
            System.out.println("获取连接失败");
        }

    }

    public static void insert(Connection conn) {
        long total = 0;
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO fawde_t_fault_group_setting_info(ecu_id,fault_group,engine_model) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(prefix);
            //准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 1000; j++) {
                    total = i * j;
                    // 构建SQL后缀
                    suffix.append("('" + i * j + "','" + i * j + "'" + ",'XX发动机号'" + "),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println(total / 10000 + "万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }

    public static void insertFaultRecord(Connection conn) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long total = 0;
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO fawde_t_fault_record_info(" +
                "id" +
                ",engine_number\n" +
                ",terminal_id\n" +
                ",engine_type\n" +
                ",engine_model\n" +
                ",fault_name\n" +
                ",fault_group\n" +
                ",spn\n" +
                ",obd_code\n" +
                ",fmi\n" +
                ",uds_code\n" +
                ",fault_level\n" +
                ",chinese_desc\n" +
                ",fault_class\n" +
                ",fault_affirm_loop\n" +
                ",fault_repair_loop\n" +
                ",fault_component\n" +
                ",lead_problem\n" +
                ",error_reason\n" +
                ",solution\n" +
                ",fault_happen_time\n" +
                ",fault_end_time\n" +
                ",start_position\n" +
                ",end_position\n" +
                ",stop_type\n" +
                ",create_time\n" +
                ",update_time) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(prefix);
            //准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 1000; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 4000; j++) {
                    total = i * j;
                    // 构建SQL后缀
                    suffix.append("(NULL, '60525364', '1111111', '非道路市场机型', '60525364', '故障名称1', '测试kafka', '11', NULL, '22', '123956711', 1, NULL, '故障类', '故障确认循环', '故障修复循环', '故障部件', '导致问题', '出错原因', '解决办法'," + "'" + sdf.format(new Date()) + "'," + "'2021-05-19 09:25:35', '河北省保定市涿州市义和庄镇常家庄村', '河北省保定市涿州市义和庄镇常家庄村',NULL," + "'" + sdf.format(new Date()) + "','" + sdf.format(new Date()) + "'),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println(total / 10000 + "万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}
