package com.guli.edu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-03 11:39
 * @Description:
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //write();
        read();
    }

    private static void read() {
        /**
         * 实现easyexcel的读操作
         */
        String filename="D:\\gali.xlsx";
        EasyExcel.read(filename,ExcelData.class,new ExcelListener()).sheet().doRead();
    }

    private static void write() {
        /**
         * 实现excel写的操作
         */
        String filename="D:\\gali.xlsx";

        EasyExcel.write(filename,ExcelData.class).sheet("学生列表").doWrite(getList());
    }

    private static List<ExcelData> getList(){
        List<ExcelData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelData excelData = new ExcelData();
            excelData.setSno(i);
            excelData.setSname("gali"+i);
            list.add(excelData);
        }
        return list;
    }
}
