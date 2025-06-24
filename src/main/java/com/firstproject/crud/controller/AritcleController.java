package com.firstproject.crud.controller;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class AritcleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){

        //모든 데이터 가져오기
         List<Article> articleList = articleRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("articleList",articleList);
        //뷰 페이지에 출력하기
        return "articles/index";
    }

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

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);

        //id 조회해 db에서 데이터 가져오기

        Article articleData=articleRepository.findById(id).orElse(null);

        //모델에 가져온 데이터 등록하기
        model.addAttribute("article",articleData);

        //데이터 보이는 뷰 페이지 반환하기
        return "articles/show";
    }
}
