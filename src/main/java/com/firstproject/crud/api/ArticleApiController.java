package com.firstproject.crud.api;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){

        log.info(dto.toString());
        Article entity = dto.toEntity();
        log.info(entity.toString());
        return articleRepository.save(entity);
    }

    //PUT

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        log.info(dto.toString());

        Article entity = dto.toEntity();
        log.info(entity.toString());

        Article target=articleRepository.findById(id).orElse(null);

        if(target ==null | id !=entity.getId()){
            log.info("잘못된 요청. id={} article={}",id,entity.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
        target.patch(entity);
        Article saved = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(saved);

    }

    //DELETE

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
