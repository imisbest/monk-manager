package com.csw.monkmanager;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by HIAPAD on 2019/12/2.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEasyExcel {
    String fileName = "C:\\Users\\Administrator\\Desktop\\新建文件夹1\\" + new Date().getTime() + "DemoData.xlsx";

    @Test
    public void test01() {
        // 写法1 . 链式调用
        // EasyExcel.write(fileName,DemoData.class).sheet().doWrite(data());
        // 写法2 .
        ExcelWriter build = EasyExcel.write(fileName, DemoData.class).build();
        // String : 页名称  Int : 第几页    可以同时指定
        WriteSheet sheet = EasyExcel.writerSheet("测试用").build();
        build.write(data(), sheet);
        build.finish();
    }

    @Test
    public void test02() {
        // new DemoDataListener() 每次都需要创建新的DemoDataListener 在Spring框架中使用时需要注意
        // EasyExcel.read("C:\\Users\\Administrator\\Desktop\\新建文件夹\\1575255268555DemoData.xlsx",DemoData.class,new DemoDataListener()).sheet().doRead();

        ExcelReader excelReader = EasyExcel.read("C:\\Users\\Administrator\\Desktop\\新建文件夹1\\1575269760788DemoData.xlsx", DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    @Test
    public void test03() {
        // 1. 创建一个需要忽略|包含的字段集合
        Set set = new HashSet();
        // 2. 放入需要忽略|包含的属性名
        set.add("date");
        //3. excludeColumnFiledNames 排除 includeColumnFiledNames 包含
        EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(set).sheet().doWrite(data());
    }

    @Test
    public void test04() {
        /**
         * 生成表头
         */
        // EasyExcel 通过创建空对象集合的形式 生成只有表头的文件 可以作为网络使用的模板Excle
        ComplexHeadData complexHeadData = new ComplexHeadData();
        List<ComplexHeadData> complexHeadData1 = Arrays.asList(complexHeadData);
        // 注意 null 不要写
        EasyExcel.write(fileName, ComplexHeadData.class).sheet().doWrite(complexHeadData1);

    }

    @Test
    public void test05() {

        String aasd = "C:\\Users\\Administrator\\Desktop\\img\\22.PNG";
        ConverterData rxx = new ConverterData("Rxx", new Date(), 11.0, aasd);
        EasyExcel.write(fileName, ConverterData.class).sheet().doWrite(Arrays.asList(rxx));
    }

    @Test
    public void test06() throws IOException {
        /**
         * 图片
         */
        ImageData imageData = new ImageData();
        String aasd = "C:\\Users\\Administrator\\Desktop\\img\\22.PNG";
        imageData.setFile(new File(aasd));
        imageData.setByteArray(FileUtils.readFileToByteArray(new File(aasd)));
        imageData.setInputStream(new FileInputStream(new File(aasd)));
        imageData.setString(aasd);
        //imageData.setUrl(new URL(
        //        "https://raw.githubusercontent.com/alibaba/easyexcel/master/src/test/resources/converter/img.jpg"));
        List<ImageData> imageData1 = Arrays.asList(imageData);
        EasyExcel.write(fileName, ImageData.class).sheet().doWrite(imageData1);
    }


    private List<DemoData> data() {
        DemoData demoData1 = new DemoData("Rxx", new Date(), 1.0, "Rxx");
        DemoData demoData2 = new DemoData("Rxx", new Date(), 1.0, "Rxx");
        DemoData demoData3 = new DemoData("Rxx", new Date(), 1.0, "Rxx");
        DemoData demoData4 = new DemoData("Rxx", new Date(), 1.0, "Rxx");
        List<DemoData> demoData = Arrays.asList(demoData1, demoData2, demoData3, demoData4);
        return demoData;
    }
}
