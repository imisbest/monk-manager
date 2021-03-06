package com.csw.monkmanager.entity;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * Created by HIAPAD on 2019/12/2.
 */
public class CustomStringStringConverter implements Converter<String> {
    // 转换为java数据时的数据类型
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    // 转换为 excel中的单元格数据类型
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    // 如何将excel 中的数据转换为 java数据
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        return cellData.getStringValue();
    }

    // 如何将java数据 转换为excel中的数据
    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(value);
    }
}
