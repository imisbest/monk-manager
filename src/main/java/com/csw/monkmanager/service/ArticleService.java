package com.csw.monkmanager.service;

import com.csw.monkmanager.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map<String,Object> showAllArticles(Integer page, Integer rows);

    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(String[] ids);

    void insertArticle(Article article);

    List<Article> queryAllArticles();

    void deleteAll();
}
