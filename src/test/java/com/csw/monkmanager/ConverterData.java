package com.csw.monkmanager;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by HIAPAD on 2019/12/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(100)
@ColumnWidth(100 / 8)
public class ConverterData {
    /**
     * 我自定义 转换器，不管数据库传过来什么 。我给他加上“自定义：”
     */
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String string;
    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
    /**
     * 我想接收百分比的数字
     */
    @NumberFormat("#.##%")
    private Double doubleData;
    @ExcelProperty(converter = StringImageConverter.class)
    private String img;
}