package com.uppercaseband.controllers;

import com.uppercaseband.model.ArticleListDTO;
import com.uppercaseband.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ArticleController.BASE_URL)
public class ArticleController {

    //provide a constant for the base URL
    static final String BASE_URL = "/api/v1/articles";

    private final ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        //Spring will inject the service when the controller constructor is called
        this.articleService = articleService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArticleListDTO getArticles(@RequestParam Optional<String> category) {

        if (category.isPresent()) {
            return articleService.getArticlesByCategory(category.get());
        } else {
            return articleService.getAllArticles();
        }
    }
}
