package com.group36.vertxspring.repository;

import com.group36.vertxspring.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
