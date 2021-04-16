package com.csw.monkmanager.service;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.ArticleDao;
import com.csw.monkmanager.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> showAllArticles(Integer page, Integer rows) {

        //jqGrid 需要page当前页数  total总页数  rows数据  records总条数
        HashMap<String,Object> hashMap = new HashMap<>();
        Integer records = articleDao.selectCount(new Article());
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds((page - 1) * rows, rows));
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        hashMap.put("page", page);
        hashMap.put("total", total);
        hashMap.put("rows", articles);
        hashMap.put("records", records);
        return hashMap;
    }

    @Override
    @LogAnnotation(value = "添加文章信息")
    public void addArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreate_date(new Date());
        article.setPublish_date(new Date());
        articleDao.insertSelective(article);
    }

    @Override
    @LogAnnotation(value = "更新文章信息")
    public void updateArticle(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }


    @Override
    @LogAnnotation(value = "删除文章信息")
    public void deleteArticle(String[] ids) {
        Example example = new Example(Article.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        articleDao.deleteByExample(example);
    }

    @Override
    public void insertArticle(Article article) {
        articleDao.insert(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> queryAllArticles() {
        return articleDao.selectAll();
    }

    @Override
    public void deleteAll() {
        List<Article> articles = articleDao.selectAll();
        for (Article article : articles) {
            articleDao.delete(article);
        }

    }
}
