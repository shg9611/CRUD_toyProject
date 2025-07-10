package com.firstproject.crud.service;

import com.firstproject.crud.dto.CommentDto;
import com.firstproject.crud.entity.Article;
import com.firstproject.crud.entity.Comment;
import com.firstproject.crud.repository.ArticleRepository;
import com.firstproject.crud.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;
    
    //댓글 조회
    public List<CommentDto> showComment(Long articleId){

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional
    public CommentDto createComment(Long articleId, CommentDto dto) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패 ! " + "대상 게시글이 없습니다."));

        Comment comment = Comment.createComment(dto,article);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }


    //댓글 생성
    
    //댓글 수정
    
    //댓글 삭제
}

