package com.qhl.salary_java.utils.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入excel数据监听器
 */
@Slf4j
public class ImportExcelListener<ExcelDto> extends AnalysisEventListener<ExcelDto> {
    List<ExcelDto> list = new ArrayList<>();

    @Override
    public void invoke(ExcelDto data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(list);
        System.out.println("解析完成");
    }

//    @Override
//    public void extra(CellExtra extra, AnalysisContext context) {
//        System.out.println("读取到了一条额外信息:"+JSON.toJSONString(extra));
//        switch (extra.getType()) {
//            case MERGE:
//                System.out.println("有一条合并信息");
//                break;
//        }
//    }
}
