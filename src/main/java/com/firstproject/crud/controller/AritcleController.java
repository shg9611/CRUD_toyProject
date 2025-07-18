package com.firstproject.crud.controller;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.dto.CommentDto;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.repository.ArticleRepository;
import com.firstproject.crud.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class AritcleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

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


       return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);

        //id 조회해 db에서 데이터 가져오기

        Article articleData=articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.showComment(id);

        //모델에 가져온 데이터 등록하기
        model.addAttribute("article",articleData);
        model.addAttribute("commentDtos",commentDtos);

        //데이터 보이는 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article editArticle = articleRepository.findById(id).orElse(null);
        model.addAttribute("editArticle",editArticle);

        return "articles/edit";
    }


    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        Article formEntity=form.toEntity();

        log.info(formEntity.toString());

        Article target=articleRepository.findById(formEntity.getId()).orElse(null);

        if(target!=null){
            articleRepository.save(formEntity);
        }

        return "redirect:/articles/"+formEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        rttr.addFlashAttribute("msg", "정상적으로 삭제 되었습니다");

        log.info("삭제 요청 들어옴");

        Article target=articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        if (target!=null){
            articleRepository.delete(target);
        }

        return "redirect:/articles";
    }
}

