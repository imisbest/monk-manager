package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.CacheAnnotation;
import com.csw.monkmanager.annotation.DeleteAnnotation;
import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.ArticleDao;
import com.csw.monkmanager.dao.ArticleRepository;
import com.csw.monkmanager.entity.Article;
import com.csw.monkmanager.service.ArticleService;
import com.csw.monkmanager.util.HttpUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * @param hotwords
     * @return
     */
    @RequestMapping("hotwords")
    public List<Article> hotwords(String hotwords) {
        System.out.println(hotwords);
        List<Article> arrayList = new ArrayList<>();
        // 1. 构建高亮器
        HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>");
        // 2. 排序
        // FieldSortBuilder fieldSortBuilder = new FieldSortBuilder("create_date").order(SortOrder.DESC);
        // 3. 查询
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(hotwords).field("title").field("content"))
                .withFilter(QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(hotwords).field("title").field("content")))
                .withHighlightFields(field)
                //.withSort(fieldSortBuilder)
                .build();
        // 4.处理查询结果
        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(nativeSearchQuery, Article.class, new SearchResultMapper() {

            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHit[] hits = searchResponse.getHits().getHits();
                ArrayList<Article> articles = new ArrayList<>();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    Article article = new Article();
                    article.setId(sourceAsMap.get("id").toString());
                    article.setTitle(sourceAsMap.get("title").toString());
                    article.setContent(sourceAsMap.get("content").toString());
                    // article.setCreate_date(new Date(Long.valueOf(sourceAsMap.get("create_date").toString())));
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields.get("title") != null) {
                        article.setTitle(highlightFields.get("title").fragments()[0].toString());
                    }
                    if (highlightFields.get("content") != null) {
                        article.setContent(highlightFields.get("content").fragments()[0].toString());
                    }
                    articles.add(article);
                   //article]" + article);
                    arrayList.add(article);
                }
                return (AggregatedPage<T>) new AggregatedPageImpl<Article>((List<Article>) articles);
            }


            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        System.out.println(articles.getContent());
        return arrayList;
    }

    /**
     *
     */
    @RequestMapping("inputclear")
    public void inputclear() {
        articleRepository.deleteAll();
    }

    /**
     *
     */
    @RequestMapping("inputreload")
    public void inputreload() {
        List<Article> articles = articleDao.selectAll();
        articleRepository.saveAll(articles);
       //poemlist;;" + articles);
    }

    /**
     * @param page
     * @param rows
     * @return
     */
    @CacheAnnotation()
    @RequestMapping("showAllArticles")
    public Map showAllArticles(Integer page, Integer rows) {
        Map map = articleService.showAllArticles(page, rows);
       //showAllArticles]" + map);
        return map;
    }

    /**
     * @param imgFile
     * @param session
     * @param request
     * @return
     */
    @DeleteAnnotation
    @RequestMapping("uploadImg")
    public Map uploadImg(MultipartFile imgFile, HttpSession session, HttpServletRequest request) {
        HashMap hashMap = new HashMap();
        String dir = "/upload/articleImg/";
        try {
            String httpUrl = HttpUtil.getHttpUrl(imgFile, request, session, dir);
            hashMap.put("error", 0);
            hashMap.put("url", httpUrl);
        } catch (Exception e) {
            hashMap.put("error", 0);
            hashMap.put("mesage", "上传错误");
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("showAllImgs")
    public Map showAllImgs(HttpSession session, HttpServletRequest request) {
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        File file = new File(realPath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir", false);
            fileMap.put("has_file", false);
            fileMap.put("filesize", file1.length());
            fileMap.put("is_photo", true);
            String extension = FilenameUtils.getExtension(file1.getName());
            fileMap.put("filetype", extension);
            fileMap.put("filename", file1.getName());
            String s = file1.getName().split("_")[0];
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sf.format(new Date(Long.valueOf(s)));
            fileMap.put("datetime", format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list", arrayList);
        hashMap.put("total_count", arrayList.size());
        hashMap.put("current_url", request.getContextPath() + "/upload/articleImg/");
        return hashMap;
    }

    @RequestMapping("insertArticle")
    @DeleteAnnotation
    public void insertArticle(MultipartFile articleImg, Article article, HttpSession session, HttpServletRequest request) {
        String httpUrl = HttpUtil.getHttpUrl(articleImg, request, session, "/upload/articleImg/");
        String uuid = UUID.randomUUID().toString();
        article.setId(uuid);
        article.setUrl(httpUrl);
        article.setCreate_date(new Date());
        article.setPublish_date(new Date());
        //状态字段保留
        System.out.println(articleImg);
        System.out.println(article);
        articleService.insertArticle(article);
        //添加文章的时候同时es里添加
        articleRepository.save(article);
    }

    /**
     * @param article
     */
    @RequestMapping("addArticle")
    @DeleteAnnotation
    public void addArticle(Article article) {
        articleService.addArticle(article);
    }

    /**
     * @param article
     */
    @RequestMapping("updateArticle")
    public void updateArticle(Article article) {
       //article]" + article);
        articleService.updateArticle(article);
    }

    /**
     * @param id
     * @param oper
     * @param article
     */
    @RequestMapping("editArticle")
    @DeleteAnnotation()
    public void editArticle(String[] id, String oper, Article article) {
        for (int i = 0; i < id.length; i++) {
           //数组" + id[i]);
        }
        //删除的时候es同时删除,先删除
        List<String> strings = Arrays.asList(id);
        for (String ss : strings) {
           //ss]" + ss);
            articleRepository.delete(articleDao.selectByPrimaryKey(new Article().setId(ss)));
        }
        //后删除
        if (oper.equals("del")) {
            articleService.deleteArticle(id);
        }
    }

    /**
     * @throws Exception
     */
    @RequestMapping("downloadArticle")
    public void downloadArticle() throws Exception {
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
            cellStyle.setDataFormat(format);
            cell3.setCellStyle(cellStyle);    //添加日期样式
            cell3.setCellValue(ac.getPublish_date()); //添加数据
        }
        //创建输出流  从内存中写入本地磁盘
        workbook.write(new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\stu.xls")));
        //关闭资源
        workbook.close();
    }

    /**
     *
     */
    @LogAnnotation(value = "上传文章信息")
    @RequestMapping("uploadArticle")
    @DeleteAnnotation
    public void uploadArticle() {
        //articleService.deleteAll();
        List<Article> articles = articleDao.selectAll();
        for (int i = 0; i < articles.size(); i++) {
            articleDao.delete(articles.get(i));
        }
        /**
         *
         */
        try {
            //获取要导入的文件
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\stu.xls")));
            //获取工作薄
            HSSFSheet sheet = workbook.getSheet("文章信息1");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Article article = new Article();

                //获取行
                HSSFRow row = sheet.getRow(i);

                article.setId(row.getCell(0).getStringCellValue());
                article.setTitle(row.getCell(1).getStringCellValue());
                article.setUrl(row.getCell(2).getStringCellValue());
                article.setContent(row.getCell(3).getStringCellValue());
                article.setCreate_date(row.getCell(4).getDateCellValue());
                article.setPublish_date(row.getCell(5).getDateCellValue());
                article.setStatus(row.getCell(6).getStringCellValue());
                article.setGuru_id(row.getCell(7).getStringCellValue());
                //调用一个插入数据库的方法
               //article]" + article);
                articleService.insertArticle(article);
            }
            //关闭资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 6. 文章详情接口
     *
     * @param article
     * @return
     */
    @RequestMapping("article")
    public Map article(Article article) {
        Map map = new HashMap();
        List<Article> articles = articleDao.selectAll();
        map.put("status", 200);
        map.put("article", articles);
        return map;
    }

    /**
     *
     */
    @RequestMapping("deleteAllArticle")
    @DeleteAnnotation
    public void deleteAllArticle() {
        articleService.deleteAll();
    }
}
