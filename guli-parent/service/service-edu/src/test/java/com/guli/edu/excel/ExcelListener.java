package com.guli.edu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Auther: gali
 * @Date: 2022-09-03 11:49
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener<ExcelData> {
    /**
     * 读取表头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    /**
     * 一行一行读取内容
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelData data, AnalysisContext analysisContext) {
        System.out.println("*****"+data);
    }

    /**
     *  读取完成
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成");
    }
}
