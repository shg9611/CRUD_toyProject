package com.firstproject.crud.repository;

import com.firstproject.crud.entity.Article;
import com.firstproject.crud.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        //case 1: 4번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 4L;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByArticleId(articleId);

            //예상 데이터
            Article article = new Article(articleId,"당신의 인생 영화는?","댓글 고");
            Comment one = new Comment(1L,article,"Park","굿 윌 헌팅");
            Comment two = new Comment(2L,article,"Kim","아이 엠 샘");
            Comment three = new Comment(3L,article,"Choi","쇼생크 탈출");
            List<Comment> expected = Arrays.asList(one,two,three);

            //비교 및 검증
            assertEquals(expected.toString(),commentList.toString(),"4번 글의 댓글 모두 출력");
        }
        //case 2: 1번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 1L;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByArticleId(articleId);

            //예상 데이터
            Article article = new Article(1L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected,commentList,"1번 글의 댓글 모두 출력");
        }

        //case 3: 9번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 9L;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByArticleId(articleId);

            //예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected,commentList,"9번 글의 댓글 모두 출력");
        }

        //case 4: 999번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 999L;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByArticleId(articleId);

            //예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected,commentList,"999번 글의 댓글 모두 출력");
        }

        //case 5: -1번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = -1L;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByArticleId(articleId);

            //예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected,commentList,"-1번 글의 댓글 모두 출력");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        //case 1: Park의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = "Park";

            //실제 데이터
            List<Comment> commentList = commentRepository.findByNickname(nickname);

            //예상 데이터
            Article articleOne = new Article(4L,"당신의 인생 영화는?","댓글 고");
            Article articleTwo = new Article(5L,"당신의 소울 푸드는?","댓글 고고");
            Article articleThree = new Article(6L,"당신의 취미는?","댓글 고고고");
            Comment one = new Comment(1L,articleOne,nickname,"굿 윌 헌팅");
            Comment two = new Comment(4L,articleTwo,nickname,"짬뽕");
            Comment three = new Comment(7L,articleThree,nickname,"영화감상");

            List<Comment> expected = Arrays.asList(one,two,three);

            //비교 및 검증
            assertEquals(expected.toString(),commentList.toString(), "Park의 모든 댓글 출력");
        }

        //case 2: Kim의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = "Kim";

            //실제 데이터
            List<Comment> commentList = commentRepository.findByNickname(nickname);

            //예상 데이터
            Article articleOne = new Article(4L,"당신의 인생 영화는?","댓글 고");
            Article articleTwo = new Article(5L,"당신의 소울 푸드는?","댓글 고고");
            Article articleThree = new Article(6L,"당신의 취미는?","댓글 고고고");
            Comment one = new Comment(2L,articleOne,nickname,"아이 엠 샘");
            Comment two = new Comment(5L,articleTwo,nickname,"짜장");
            Comment three = new Comment(8L,articleThree,nickname,"웹툰");

            List<Comment> expected = Arrays.asList(one,two,three);

            //비교 및 검증
            assertEquals(expected.toString(),commentList.toString(), "Kim의 모든 댓글 출력");
        }

        //case 3: null의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = null;

            //실제 데이터
            List<Comment> commentList = commentRepository.findByNickname(nickname);

            //예상 데이터
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected.toString(),commentList.toString(), "null의 모든 댓글 출력");
        }

        //case 4: ""의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = "";

            //실제 데이터
            List<Comment> commentList = commentRepository.findByNickname(nickname);

            //예상 데이터
            List<Comment> expected = Arrays.asList();

            //비교 및 검증
            assertEquals(expected.toString(),commentList.toString(), "\"\"의 모든 댓글 출력");
        }


    }
}