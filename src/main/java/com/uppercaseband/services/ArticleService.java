package com.uppercaseband.services;

import com.uppercaseband.model.ArticleListDTO;

public interface ArticleService {

    ArticleListDTO getAllArticles();

    ArticleListDTO getArticlesByCategory(String category);
}
