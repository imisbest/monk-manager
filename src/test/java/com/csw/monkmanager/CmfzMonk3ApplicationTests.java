package com.csw.monkmanager;

import com.csw.monkmanager.dao.AdminDao;
import com.csw.monkmanager.dao.BannerDao;
import com.csw.monkmanager.entity.Admin;
import com.csw.monkmanager.entity.Article;
import com.csw.monkmanager.entity.Banner;
import com.csw.monkmanager.entity.Stu;
import com.csw.monkmanager.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzMonk3ApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ArticleService articleService;

    @Test
    public void contextLoads() {
        List<Banner> banners = bannerDao.selectAll();
//        List<Admin> admins = adminDao.selectAll();
//        admins.forEach(admin -> System.out.println(admin));
    }

    @Test
    public void testMapper() {
//	    adminDao.insert(new Admin());
        adminDao.insertSelective(new Admin());
    }

    /*
        通用Mapper查询测试
     */
    @Test
    public void testSelect() {
        // 查询所有
        // adminDao.selectAll();
        // 根据条件进行查询
        Admin admin = new Admin();
        admin.setUsername("admin");
        //List<Admin> select = adminDao.select(admin);
        //System.out.println(select);
        // 根据主键查询
//        Admin admin1 = adminDao.selectByPrimaryKey("1");
//        System.out.println(admin1);
        // 根据条件查询单个对象
        //System.out.println(adminDao.selectOne(admin));
        //System.out.println(adminDao.selectCount(new Admin()));
        // 分页查询方法
        System.out.println(adminDao.selectByRowBounds(new Admin(), new RowBounds(1, 4)));
    }

    @Test
    public void testInsert() {
        Admin rx = new Admin(null, "Rx", "123");
        adminDao.insert(rx);
        System.out.println(rx);
        // insertSelective 会判断当前字段是否为null,为null不处理
        //adminDao.insertSelective()
    }

    @Test
    public void testExample() {
        Example example = new Example(Admin.class);
        example.createCriteria().andIn("username", Arrays.asList("admin", "Rx"));
        List<Admin> admins = adminDao.selectByExample(example);
        System.out.println(admins);
    }

    @Test
    public void testPoi() {
        // 1. 创建表格对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2. 创建页对象
        HSSFSheet sheet = workbook.createSheet("学生信息");
        // 3. 创建行对象
        HSSFRow row = sheet.createRow(0);
        // 4. 创建单元格对象
        HSSFCell cell = row.createCell(0);

        // 5. 将表格对象写入磁盘
        try {
            workbook.write(new File("D:\\桌面杂项\\stu.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPoiDemo() {
        // 1. 创建表格对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2. 创建页对象
        HSSFSheet sheet = workbook.createSheet("学生信息");
        sheet.setColumnWidth(3, 50 * 256);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 10);
        //施工
        sheet.addMergedRegion(region);
        // 3. 创建行对象
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 500);
        // 4. 创建表头
        HSSFFont font = workbook.createFont();
        // 加粗
        font.setBold(true);
        // 颜色
        font.setColor(Font.COLOR_RED);
        // 字体
        font.setFontName("宋体");
        // 斜体
        font.setItalic(true);

        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);

        HSSFCell cell1 = row.createCell(5);
        cell1.setCellStyle(cellStyle1);
        cell1.setCellValue("脑壳疼");


        String[] strs = {"ID", "姓名", "年龄", "生日"};
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            row.createCell(i).setCellValue(str);
        }
        Stu rxx1 = new Stu("1", "Rxx", 18, new Date());
        Stu rxx2 = new Stu("1", "Rxx", 18, new Date());
        Stu rxx3 = new Stu("1", "Rxx", 18, new Date());
        Stu rxx4 = new Stu("1", "Rxx", 18, new Date());
        // 将集合中的数据写入至xls文件中
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        List<Stu> stus = Arrays.asList(rxx1, rxx2, rxx3, rxx4);
        for (int i = 0; i < strs.length; i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(stus.get(i).getId());
            row1.createCell(1).setCellValue(stus.get(i).getName());
            row1.createCell(2).setCellValue(stus.get(i).getAge());
            HSSFCell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(stus.get(i).getBir());
        }
        // 5. 将表格对象写入磁盘
        try {
            workbook.write(new File("C:\\Users\\Administrator\\Desktop\\stu.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void ArticleDownload() throws IOException {
        List<Article> articles = articleService.queryAllArticles();

        //创建 Excel 工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建工作表   参数：表名（相当于Excel的sheet1,sheet2...）
        HSSFSheet sheet = workbook.createSheet("文章信息1");

        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title = {"ID", "标题", "链接", "内容", "创建时间", "发布时间", "状态", "上师"};

        //处理单元格对象
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);    //单元格下标
            cell.setCellValue(title[i]);   //单元格内容

        }

        //处理数据行
        for (int i = 0; i < articles.size(); i++) {
            //遍历一次创建一行
            HSSFRow row2 = sheet.createRow(i + 1);
            Article ac = articles.get(i);
            //Article article=new Article(ac.getId(),ac.getTitle(),ac.getUrl(),ac.getContent(),ac.getCreate_date(),ac.getPublish_date(),ac.getStatus(),ac.getGuru_id());
            //每行对应放的数据
            row2.createCell(0).setCellValue(ac.getId());
            row2.createCell(1).setCellValue(ac.getTitle());
            row2.createCell(2).setCellValue(ac.getUrl());
            row2.createCell(3).setCellValue(ac.getContent());
            row2.createCell(4).setCellValue(ac.getCreate_date());
            row2.createCell(5).setCellValue(ac.getPublish_date());
            row2.createCell(6).setCellValue(ac.getStatus());
            row2.createCell(7).setCellValue(ac.getGuru_id());
/**
 *
 */
            //设置单元格日期格式
            Cell cell2 = row2.createCell(4);
            // 将集合中的数据写入至xls文件中
            HSSFDataFormat dataFormat = workbook.createDataFormat();
            short format = dataFormat.getFormat("yyyy-MM-dd");
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(format);
            cell2.setCellStyle(cellStyle);    //添加日期样式
            cell2.setCellValue(ac.getCreate_date()); //添加数据
            /**
             *
             */

            //设置单元格日期格式
            Cell cell3 = row2.createCell(5);
            // 将集合中的数据写入至xls文件中
//            HSSFDataFormat dataFormat = workbook.createDataFormat();
//            short format = dataFormat.getFormat("yyyy-MM-dd");
//            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(format);
            cell3.setCellStyle(cellStyle);    //添加日期样式
            cell3.setCellValue(ac.getPublish_date()); //添加数据
        }

        //创建输出流  从内存中写入本地磁盘
        workbook.write(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\stu.xls")));
        //关闭资源
        workbook.close();
    }
    @Test
    public void sss() {
       //1");
    }
}
