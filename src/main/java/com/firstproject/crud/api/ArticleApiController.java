package com.firstproject.crud.api;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;
import com.firstproject.crud.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){

        Article created = articleService.create(dto);

        return (created != null) ? ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //PUT

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article saved = articleService.update(id, dto);
        return (saved != null) ? ResponseEntity.status(HttpStatus.CREATED).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    //DELETE

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);

        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @PostMapping("/api/transaction-test")
//    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
//        List<Article> articleList = articleService.transactionTest(dtos);
//
//        return (articleList !=null ) ? ResponseEntity.status(HttpStatus.CREATED).body(articleList) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}
