package com.sxx.codegenerator.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author SHIXINXI
 * @description easy-excel读取监听器
 * @create 2023-06-08-17:20
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    /**
     * @description 一行一行读取excel内容
     */
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }

    /**
     * @description 读取表头内容
     */
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    /**
     * @description 完成后做的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
