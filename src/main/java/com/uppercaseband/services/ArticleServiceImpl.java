package com.uppercaseband.services;

import com.uppercaseband.domain.Category;
import com.uppercaseband.mappers.ArticleMapper;
import com.uppercaseband.model.ArticleListDTO;
import com.uppercaseband.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;


    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleRepository articleRepository) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }


    @Override
    public ArticleListDTO getAllArticles() {

        //use Java8 streams to convert the domain objects into data transfer objects
        return new ArticleListDTO (
                articleRepository.findAll()
                    .stream()
                    .map( articleMapper::articleToArticleDTO)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public ArticleListDTO getArticlesByCategory(String category) {

        return new ArticleListDTO(
                articleRepository.findByCategory(Enum.valueOf(Category.class, category) )
                    .stream()									//create a stream for the collection
                    .map(articleMapper::articleToArticleDTO)	//method to call for each item in the stream (method reference notation)
                    //.map( (Article a) -> { return articleMapper.articleToArticleDTO( a ); } )		//lambda expression equivalent to above
                    .collect(Collectors.toList())				//terminal method operation that creates a reduced list
        );
    }
}
