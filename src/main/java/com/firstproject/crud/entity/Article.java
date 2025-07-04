package com.firstproject.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article){
        if (article.getTitle() != null){
            this.title=article.getTitle();
        }
        if (article.getContent() != null){
            this.content=article.getContent();
        }
    }


}
