package com.firstproject.crud.service;

import com.firstproject.crud.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        
        //예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

        List<Article> expectedList = new ArrayList<Article>(Arrays.asList(a,b,c));

        
        //실제 데이터
        List<Article> articleList = articleService.index();
        
        //비교 및 검증

        assertEquals(expectedList.toString(), articleList.toString());
    }
}