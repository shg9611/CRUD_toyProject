package com.firstproject.crud.entity;


import com.firstproject.crud.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto,Article article){

        if (dto.getId()!=null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        
        if(dto.getArticleId()!=article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public Comment patch(CommentDto dto){
        if (dto.getId() != this.id){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id 입력");
        }


        if (dto.getNickname()!=null){
            this.nickname= dto.getNickname();
        }
        if (dto.getBody()!=null){
            this.body= dto.getBody();
        }

        return this;
    }
}

