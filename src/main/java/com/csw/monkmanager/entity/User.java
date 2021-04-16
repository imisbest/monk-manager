package com.csw.monkmanager.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by HIAPAD on 2019/11/29.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ContentRowHeight(100)
@ColumnWidth(100 / 8)
public class User implements Serializable {
    @Id
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "ID")
    private String id;//   `id` varchar(255) NOT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "手机号")
    private String phone;//  `phone` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "密码")
    private String password; //  `password` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "盐")
    private String salt;//  `salt` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "状态")
    private String status;//  `status` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = StringImageConverter.class, value = "图片")
    private String photo;//  `photo` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "名字")
    private String name;//  `name` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "昵称")
    private String nick_name;//  `nick_name` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "性别")
    private String sex;//  `sex` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "信号")
    private String sign;//  `sign` varchar(255) DEFAULT NULL,
    @ExcelProperty(converter = CustomStringStringConverter.class, value = "省份")
    private String location;//  `location` varchar(255) DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("注册日期")
    private Date rigest_date;//  `rigest_date` date DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty("最后登陆日期")
    private Date last_login;//            `last_login` date DEFAULT NULL,
}
