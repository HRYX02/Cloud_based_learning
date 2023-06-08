package com.sxx.codegenerator.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHIXINXI
 * @description easy-excel写操作
 * @create 2023-06-08-17:06
 */

public class TestEasyExcel {
    public static void main(String[] args) {
//        write();
        read();
    }
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lisi"+i);
            list.add(data);
        }
        return list;
    }

    /**
     * @description 实现Excel写的操作
     */
    private static void write() {
        //
        // 1设置写入文件夹的地址和excel文件的名称
        String filename = "E:/project/atguigu/Cloud_based_learning/write.xlsx";
        // 2调用easyexcel里面的方法实现写操作
        // write方法中两个参数，第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    /**
     * @description 实现Excel读的操作
     */
    private static void read() {
        String filename = "E:/project/atguigu/Cloud_based_learning/write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
