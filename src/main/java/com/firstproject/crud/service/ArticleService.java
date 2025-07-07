package com.firstproject.crud.service;


import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {

        log.info(dto.toString());
        Article article = dto.toEntity();
        log.info(article.toString());

        if (article.getId()!=null){
            log.info("id is not null");
            return null;
        }

        return articleRepository.save(article);

    }

    public Article update(Long id , ArticleForm dto){
        log.info (id.toString());
        log.info (dto.toString());

        Article article = dto.toEntity();
        Article target= articleRepository.findById(id).orElse(null);

        if (target== null || article.getId() != id){
            log.info("badRequest");
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);

    }

    public Article delete(Long id){

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null){
            return null;
        }

        log.info(target.toString());

        articleRepository.delete(target);
        return target;
    }

//    @Transactional
//    public List<Article> transactionTest(List<ArticleForm> dtos) {
//
//
//        //dto 묶음을 엔티티 묶음으로 변환
////      List<Article> articleList = new ArrayList<Article>();
////      dtos.stream().forEach((dto)-> articleList.add(dto.toEntity()));
//
//        List<Article> articleList = dtos.stream()
//                .map(dto -> dto.toEntity()).collect(Collectors.toList());
//
//        //엔티티 묶음을 db에 저장
//
//        articleList.stream().forEach(entity -> articleRepository.save(entity));
//
//        //강제 예외 발생
//
//        articleRepository.findById(-1L).orElseThrow(()->new IllegalArgumentException("결제 실패!"));
//
//        //결과 값 반환
//
//        return articleList;
//    }
}
