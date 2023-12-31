package com.uppercaseband.repositories;

import com.uppercaseband.domain.Article;
import com.uppercaseband.domain.Category;
import com.uppercaseband.domain.Media;
import com.uppercaseband.domain.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void testSaveArticle() {
        assertNotNull(articleRepository);

        Article article = new Article();
        article.setTitle("Tanging Ikaw");
        article.setDescription("The brand new single from UPPERCASE released under Radio Insect Records");
        article.setDisplayOrder(100);
        article.setCategory(Category.HIGHLIGHTS);
        article.setSubContent("<a href='https://open.spotify.com/artist/6h4pjpssOa3fBNiQmSkgOB?si=lbGJiYu7R_6ouDMIs7Jv3A'>CHECK IT OUT</a>");

        Media articleMedia = new Media();
        articleMedia.setType(MediaType.IMAGE);
        articleMedia.setPath("/images/tanging_ikaw.png");

        article.setMedia(articleMedia);

        Article savedArticle = articleRepository.save(article);
        assertThat(savedArticle).isNotNull();
        assertThat(savedArticle.getId()).isNotNull();
        assertThat(savedArticle.getTitle()).isNotNull();
        assertThat(savedArticle.getDescription()).isNotNull();
        assertThat(savedArticle.getDisplayOrder()).isNotNull();
        assertThat(savedArticle.getCategory()).isNotNull();
        assertThat(savedArticle.getSubContent()).isNotNull();
        assertThat(savedArticle.toString()).isNotNull();
        assertEquals(savedArticle.hashCode(),savedArticle.hashCode());
        assertEquals(savedArticle.equals(savedArticle), true);

        assertThat(savedArticle.getMedia()).isNotNull();
        assertThat(savedArticle.getMedia().getId()).isNotNull();
        assertThat(savedArticle.getMedia().getPath()).isNotNull();
        assertThat(savedArticle.getMedia().getType()).isNotNull();
        assertThat(savedArticle.getMedia().toString()).isNotNull();

        assertEquals(savedArticle.getMedia().hashCode(),savedArticle.getMedia().hashCode());
        assertEquals(savedArticle.getMedia().equals(savedArticle.getMedia()), true);
    }
}