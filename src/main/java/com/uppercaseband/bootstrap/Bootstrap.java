package com.uppercaseband.bootstrap;

import com.uppercaseband.domain.Article;
import com.uppercaseband.domain.Category;
import com.uppercaseband.domain.Media;
import com.uppercaseband.domain.MediaType;
import com.uppercaseband.repositories.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// the CommandLineRunner is a Spring Boot-specific class that will run on application start-up
@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {
    private final ArticleRepository articleRepository;

    public Bootstrap(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadArticles();
    }

    private void loadArticles() {
        Article article1 = new Article();
        article1.setTitle("Tanging Ikaw");
        article1.setDescription("The brand new single from UPPERCASE released under Radio Insect Records");
        article1.setDisplayOrder(100);
        article1.setCategory(Category.HIGHLIGHTS);
        article1.setSubContent("<a href='https://open.spotify.com/artist/6h4pjpssOa3fBNiQmSkgOB?si=lbGJiYu7R_6ouDMIs7Jv3A'>CHECK IT OUT</a>");

        Media article1Media = new Media();
        article1Media.setType(MediaType.IMAGE);
        article1Media.setPath("/images/tanging_ikaw.png");

        article1.setMedia(article1Media);
        articleRepository.save(article1);


        Article article2 = new Article();
        article2.setTitle("'Time Space Warp' Album Launch");
        article2.setDescription("May 17, 2013 – Hard Rock Café Toronto");
        article2.setDisplayOrder(200);
        article2.setCategory(Category.HIGHLIGHTS);
        article2.setSubContent("<p><a href='https://www.facebook.com/pg/cyberpinoyradio/photos/?tab=album&album_id=657041557656169'>SEE EVENT PICS</a></p><p><a href='https://youtu.be/yNt0JV8or3k?list=PL0AgfLYM2K_sKTvDMqLY4sDr8Pi1zadB0'>WATCH EVENT VIDEO</a></p>");

        Media article2Media = new Media();

        article2Media.setType(MediaType.IMAGE);
        article2Media.setPath("/images/tsw_album.png");

        article2.setMedia(article2Media);
        articleRepository.save(article2);


        Article article3 = new Article();
        article3.setTitle("'Time Space Warp' Music Video Launch");
        article3.setDescription("July 12, 2013 – Prestige Bar, North York");
        article3.setDisplayOrder(200);
        article3.setCategory(Category.EVENTS);

        Media article3Media = new Media();
        article3Media.setType(MediaType.VIDEO);
        article3Media.setPath("http://www.youtube.com/embed/ZfNUPtLtH5w");

        article3.setMedia(article3Media);
        articleRepository.save(article3);

        log.debug("Articles loaded: "+ articleRepository.count());
    }
}
