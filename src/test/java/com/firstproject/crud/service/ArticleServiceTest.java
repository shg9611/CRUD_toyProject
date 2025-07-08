package com.firstproject.crud.service;

import com.firstproject.crud.dto.ArticleForm;
import com.firstproject.crud.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println(expectedList.toString());
        System.out.println(articleList.toString());
        assertEquals(expectedList.toString(), articleList.toString());
    }

    @Test
    void show_success_id_exist() {
        //예상 데이터
        Long id =1L;
        Article expected = new Article(id,"가가가가","1111");

        //실제 데이터
        Article article = articleService.show(id);


        //비교 및 검증
        assertEquals(expected.toString(),article.toString());

    }
    @Test
    void show_fail_id_not_exist() {
        //예상 데이터
        Long id =-1L;
        Article expected = null;

        //실제 데이터
        Article article = articleService.show(id);

        //비교 및 검증
        assertEquals(expected,article);
    }


    @Test
    @Transactional
    void create_success_id_is_null() {
        //예상 데이터
        ArticleForm dto = new ArticleForm(null,"라라라라","4444");
        Article expected = new Article(4L, "라라라라","4444");
        //실제 데이터
        Article article = articleService.create(dto);

        //비교 및 검증
        assertEquals(expected.toString(),article.toString());

    }
    @Test
    @Transactional
    void create_fail_id_is_not_null() {

        //예상 데이터
        ArticleForm dto = new ArticleForm(4L,"라라라라","4444");

        Article expected = null;
        //실제 데이터
        Article article = articleService.create(dto);

        //비교 및 검증
        assertEquals(expected,article);

    }

    @Test
    @Transactional
    void update_success_exist_id_exist_title_content() {
        //예상 데이터
        Long id = 1L;
        String title = "테스트 1";
        String content = "테스트 1의 내용";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,content);

        //실제 데이터
        Article article = articleService.update(id, dto);

        //비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_success_exist_id_exist_only_title() {
        //예상 데이터
        Long id = 1L;
        String title = "테스트 1";
        String content = null;

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,title,"1111");

        //실제 데이터
        Article article = articleService.update(id, dto);

        //비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_success_exist_id_exist_only_content() {
        //예상 데이터
        Long id = 1L;
        String title = null;
        String content = "테스트 1의 내용";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = new Article(id,"가가가가",content);

        //실제 데이터
        Article article = articleService.update(id, dto);

        //비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_fail_not_exist_id() {
        //예상 데이터
        Long id = -1L;
        String title = "테스트 1";
        String content = "테스트 1의 내용";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        //실제 데이터
        Article article = articleService.update(id, dto);

        //비교 및 검증
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_fail_id_is_different_with_path() {
        //예상 데이터
        Long id = -1L;
        String title = "테스트 1";
        String content = "테스트 1의 내용";

        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;

        //실제 데이터
        Article article = articleService.update((id*(-1)), dto);

        //비교 및 검증
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void delete_success_exist_id() {
        //예상 데이터
        Long id = 1L;
        String title = "가가가가";
        String content = "1111";

        Article expected = new Article(id,title,content);

        //실제 데이터
        Article article = articleService.delete(id);

        //비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void delete_fail_not_exist_id() {
        //예상 데이터
        Long id = -1L;
        String title = "가가가가";
        String content = "1111";

        Article expected = null;

        //실제 데이터
        Article article = articleService.delete(id);

        //비교 및 검증
        assertEquals(expected,article);
    }
}