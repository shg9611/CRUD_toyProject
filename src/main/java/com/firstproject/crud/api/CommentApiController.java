package com.firstproject.crud.api;

import com.firstproject.crud.dto.CommentDto;
import com.firstproject.crud.entity.Comment;
import com.firstproject.crud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> showComment(@PathVariable Long articleId){

        List<CommentDto> commentDtoList=commentService.showComment(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(commentDtoList);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId,
                                                    @RequestBody CommentDto dto){

        CommentDto commentDto = commentService.createComment(articleId,dto);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);

    }

}
