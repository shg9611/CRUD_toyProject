package com.firstproject.crud.repository;

import com.firstproject.crud.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
