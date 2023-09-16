package com.uppercaseband.controllers;

import com.uppercaseband.model.ArticleDTO;
import com.uppercaseband.model.ArticleListDTO;
import com.uppercaseband.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class ArticleController {

    //provide a constant for the base URL
    static final String BASE_URL = "/api/v1/articles";

    private final ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        //Spring will inject the service when the controller constructor is called
        this.articleService = articleService;
    }


    @GetMapping(ArticleController.BASE_URL)
    public ArticleListDTO getAllArticles() {

        return articleService.getAllArticles();
    }

    @GetMapping(path = ArticleController.BASE_URL+"/category/{category}")
    public ArticleListDTO getArticles(@PathVariable Optional<String> category) {

        try {
            return articleService.getArticlesByCategory(category.get().toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, illegalArgumentException.getMessage(), illegalArgumentException.getCause());
        }
    }
}
