package com.uppercaseband.repositories;

import com.uppercaseband.domain.Article;
import com.uppercaseband.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data will provide us with functionalities out of the box from JpaRepository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategory(Category c);
}
