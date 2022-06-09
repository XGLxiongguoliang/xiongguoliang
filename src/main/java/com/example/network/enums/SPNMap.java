package com.example.network.enums;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class SPNMap {

    private static final Map<String, String> map = new HashMap<>();

    private static SPNMap spnMap = new SPNMap();

    private SPNMap() {

    }

    public static SPNMap getInstance(){
        return spnMap;
    }

    public static Map<String, String> getMap() {
        return map;
    }

    static {
        map.put("1624", "仪表车速(必填)");
        map.put("190", "转速信号");
        map.put("91", "油门踏板开度信号(必填)");
        map.put("183", "燃油消耗率");
        map.put("184", "瞬时燃油经济性");
        map.put("102", "发动机进气歧管压力");
        map.put("105", "进气歧管温度");
        map.put("100", "发动机机油压力(必填)");
        map.put("110", "发动机冷却液温度");
        map.put("171", "环境温度");
        map.put("108", "大气压力");
        map.put("174", "燃油温度");
        map.put("523", "当前档位(必填)");
        map.put("180", "整车质量（轴荷信号是否重复）");
        map.put("582", "轴荷信号");
        map.put("521", "制动踏板位置（百分比）(必填)");
        map.put("1761", "尿素液位");
        map.put("3031", "尿素罐温度");
        map.put("513", "发动机实际扭矩(必填)");
        map.put("512", "驾驶员需求扭矩");
        map.put("514", "摩擦百分比扭矩");
        map.put("522", "离合器滑移率");
        map.put("904", "前轴速度");
        map.put("905", "左前轮速");
        map.put("906", "右前轮速");
        map.put("907", "后轴 1 左轮速");
        map.put("908", "后轴 1 右轮速");
        map.put("909", "后轴 2 左轮速");
        map.put("910", "后轴 2 右轮速");
        map.put("1808", "横摆角速度");
        map.put("1809", "侧向加速度 （ABS）");
        map.put("1810", "纵向加速度 （ABS）");
        map.put("185", "平均燃油经济性");
        map.put("161", "输入轴转速");
        map.put("191", "输出轴转速");
        map.put("899", "发动机转矩模式(必填)");
        map.put("3236", "排气流量");
        map.put("132", "进气流量");
        map.put("3216", "SCR 上游 NOx 传感器输出");
        map.put("3226", "SCR 下游 NOx 传感器输出");
        map.put("4360", "SCR 入口温度");
        map.put("4363", "SCR 出口温度");
        map.put("3251", "DPF 压差");
        map.put("167", "发动机输入电压");
        map.put("247", "发动机累计运行时间");
        map.put("249", "发动机累计转数(必填)");
        map.put("102", "进气歧管压力");
        map.put("106", "进气压力");
        map.put("173", "排气温度");
        map.put("172", "进气温度");
        map.put("1081", "冷启动灯");
        map.put("97", "油中含水指示");
        map.put("526", "实际速比");
        map.put("96", "仪表燃油液位");
        map.put("544", "参考扭矩");
        map.put("1721", "左前轴相对水平位置");
        map.put("1722", "右前轴相对水平位置");
        map.put("1723", "左后轴相对水平位置");
        map.put("1724", "右后轴相对水平位置");
        map.put("1725", "左前轴波纹管压力");
        map.put("1726", "右前轴波纹管压力");
        map.put("1727", "左后轴波纹管压力");
        map.put("1728", "右后轴波纹管压力");
        map.put("516097", "开关信号");
        map.put("516098", "Tbox 加速度信号");
        map.put("516100", "TBOX 计算坡度");
        map.put("516101", "预见性巡航坡度");
        map.put("516102", "储气筒 1 压力");
        map.put("516103", "储气筒 2 压力");
        map.put("516104", "T-BOX 供电电压，精度 0.1V（A/D 采集电压）");
        map.put("516105", "无线通信网络信号强度");
        map.put("516106", "定位卫星数");
        map.put("516107", "轮胎数据");
        map.put("516108", "蓄电池 1 温度");
        map.put("516109", "蓄电池 1 电压");
        map.put("516110", "蓄电池 1SOC");
        map.put("516111", "蓄电池 1SOH");
        map.put("516112", "蓄电池 2 温度");
        map.put("516113", "蓄电池 2 电压");
        map.put("516114", "蓄电池 2SOC");
        map.put("516115", "蓄电池 2SOH");
        map.put("516116", "点火开关电压");
        map.put("516117", "空气悬架控制器 1 状态 1");
        map.put("516118", "空气悬架控制器 1 状态 2");
        map.put("516119", "缓速器档位信号");
        map.put("516120", "发动机/缓速器扭矩模式");
        map.put("516121", "TSC1_IS 状态信号");
        map.put("516122", "TSC1_IS 求速度限制");
        map.put("516123", "TSC1_IS 请求扭矩限制");
        map.put("516124", "TSC1_IS 高分辨率请求扭矩");
        map.put("516125", "VCU 坡度");
        map.put("516126", "预见性巡航坡度");
        map.put("516127", "背光亮度状态信号");
        map.put("516128", "AMT 换挡模式");
        map.put("516129", "时间");
        map.put("516130", "维度");
        map.put("516131", "经度");
        map.put("516132", "Gps 速度");
        map.put("516133", "定位状态");
        map.put("516134", "高度");
        map.put("516135", "空气悬架控制器 3 信号");
        map.put("516136", "空气悬架控制器 4 信号");
        map.put("516137", "实际尿素喷射量");
        map.put("516138", "实际尿素喷射量");
        map.put("516139", "转向开关状态");
        map.put("516140", "轮胎位置 1");
        map.put("516141", "轮胎压力");
        map.put("516142", "轮胎温度");
        map.put("516143", "轮胎状态");
        map.put("516144", "扩展胎压位");
        map.put("516145", "轮胎漏气率");
        map.put("516146", "轮胎过压/欠压报警信号");
        map.put("516147", "轮胎位置 2");
        map.put("516148", "轮胎扩展胎压");
        map.put("516149", "要求胎压");
        map.put("516150", "原车空调开关状态");
        map.put("516151", "原车空调模式状态");
        map.put("516152", "原车空调自动状态");
        map.put("516153", "原车空调风量状态");
        map.put("516154", "原车空调压缩机状态");
        map.put("516155", "原车空调循环状态");
        map.put("516156", "独立暖风开关状态");
        map.put("516157", "独立暖风温度状态");
        map.put("516158", "驻车空调开关状态");
        map.put("516159", "驻车空调风量状态");
        map.put("516160", "原车空调温度状态");
        map.put("516161", "驻车空调温度状态");
        map.put("516162", "当前温度");
        map.put("516163", "原车空调高精度温度状态");
        map.put("516164", "驻车空调高精度温度状态");
        map.put("516165", "原车空调一键通风状态");
        map.put("516166", "驻车空调自动模式");
        map.put("516167", "碳载量指示灯状态");
        map.put("516168", "再生状态指示灯");
        map.put("516169", "再生状态请求开关");
        map.put("516170", "禁止再生开关状态");
        map.put("516171", "驾驶员警报灯开关");
        map.put("516172", "MIL 指示灯状态");
        map.put("516173", "按下禁止再生开关");
        map.put("516174", "搅拌罐运转状态");
        map.put("516175", "搅拌罐旋转速度");
    }
}
