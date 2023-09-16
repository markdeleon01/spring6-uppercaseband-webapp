package com.uppercaseband.mappers;

import com.uppercaseband.domain.Article;
import com.uppercaseband.domain.Category;
import com.uppercaseband.domain.Media;
import com.uppercaseband.domain.MediaType;
import com.uppercaseband.model.ArticleDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {

    static final String TITLE = "someTitle";
    static final String DESCRIPTION = "someDescription";
    static final String PATH = "images/image.png";

    @Autowired
    ArticleMapper articleMapper;

    @BeforeAll
    static void checkMapper() {
        assertNotNull(ArticleMapper.INSTANCE);
    }

    @Test
    void articleToArticleDTO() throws Exception {

        //given
        Article article = new Article();
        article.setId(1L);
        article.setTitle(TITLE);
        article.setDescription(DESCRIPTION);
        article.setDisplayOrder(100);
        article.setCategory(Category.HIGHLIGHTS);
        Media media = new Media();
        media.setId(1L);
        media.setType(MediaType.IMAGE);
        media.setPath(PATH);
        article.setMedia(media);

        //when
        ArticleDTO articleDto = articleMapper.articleToArticleDTO(article);

        //then
        assertEquals(article.getTitle(), articleDto.getTitle());
        assertEquals(article.getDescription(), articleDto.getDescription());
        assertEquals(article.getDisplayOrder().intValue(), articleDto.getDisplayOrder());
        assertEquals(article.getCategory().name(), articleDto.getCategory());
        assertEquals(article.getMedia().getType().name(), articleDto.getMedia().getType());
        assertEquals(article.getMedia().getPath(), articleDto.getMedia().getUrl());
    }

    @Test
    void articleDTOToArticle() throws Exception {

        //given
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(TITLE);

        //when
        Article article = articleMapper.articleDTOToArticle(articleDTO);

        //then
        assertEquals(articleDTO.getTitle(), article.getTitle());
    }
}