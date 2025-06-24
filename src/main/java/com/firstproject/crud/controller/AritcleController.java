package com.firstproject.crud.controller;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AritcleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return"articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        log.info(form.toString());
        
        //dto를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());


        // repository로 엔티티를 db에 저장
        Article saved=articleRepository.save(article);
        log.info(saved.toString());


        return "";
    }
}
