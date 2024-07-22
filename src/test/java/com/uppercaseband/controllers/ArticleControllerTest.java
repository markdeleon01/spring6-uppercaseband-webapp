package com.uppercaseband.controllers;

import com.uppercaseband.model.ArticleDTO;
import com.uppercaseband.model.ArticleListDTO;
import com.uppercaseband.model.MediaDTO;

import com.uppercaseband.services.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ArticleController.class)    //brings up a small set of the Spring context for the web front-end
class ArticleControllerTest {   //this is essentially an integration test

    @Autowired
    MockMvc mockMvc;    //provided by Spring Context because of @WebMvcTest annotation

    @MockBean   //provided by Spring Context - Spring creates the Mockito mock and injects it into this class
    ArticleServiceImpl articleServiceImpl;  //instead of MockitoAnnotations.initMocks(this);


    static ArticleDTO article1;
    static ArticleDTO article2;
    static ArticleDTO article3;


    @BeforeAll
    static void setUp() {

        article1 = new ArticleDTO();
        article1.setTitle("Tanging Ikaw");
        article1.setDescription("The brand new single from UPPERCASE released under Radio Insect Records");
        article1.setSubContent("<a href='https://open.spotify.com/artist/6h4pjpssOa3fBNiQmSkgOB?si=lbGJiYu7R_6ouDMIs7Jv3A'>CHECK IT OUT</a>");

        MediaDTO article1Media = new MediaDTO();
        article1Media.setType("IMAGE");
        article1Media.setUrl("/images/tanging_ikaw.png");

        article1.setMedia(article1Media);

        article2 = new ArticleDTO();
        article2.setTitle("'Time Space Warp' Album Launch");
        article2.setDescription("May 17, 2013 – Hard Rock Café Toronto");
        article2.setSubContent("<p><a href='https://www.facebook.com/pg/cyberpinoyradio/photos/?tab=album&album_id=657041557656169'>SEE EVENT PICS</a></p><p><a href='https://youtu.be/yNt0JV8or3k?list=PL0AgfLYM2K_sKTvDMqLY4sDr8Pi1zadB0'>WATCH EVENT VIDEO</a></p>");

        MediaDTO article2Media = new MediaDTO();
        article2Media.setType("IMAGE");
        article2Media.setUrl("/images/tsw_album.png");

        article2.setMedia(article2Media);

        article3 = new ArticleDTO();
        article3.setTitle("'Time Space Warp' Music Video Launch");
        article3.setDescription("July 12, 2013 – Prestige Bar, North York");

        MediaDTO article3Media = new MediaDTO();
        article3Media.setType("VIDEO");
        article3Media.setUrl("<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/ZfNUPtLtH5w\" frameborder=\"0\" allowfullscreen></iframe>");

        article3.setMedia(article3Media);
    }


    @Test
    void getArticles() throws Exception {

        ArticleListDTO articleListDTO = new ArticleListDTO(Arrays.asList(article1, article2, article3));

        //given that the service call will return the mocked data
        given(articleServiceImpl.getAllArticles()).willReturn(articleListDTO);

        //perform the REST call to the controller to get all articles
        mockMvc.perform( get(ArticleController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articles.length()", is(3)));

        verify(articleServiceImpl, times(1)).getAllArticles();
    }


    @Test
    void getArticlesByCategory() throws Exception {

        ArticleListDTO articleListDTO = new ArticleListDTO(Arrays.asList(article1, article2));

        //given that the service call will return the mocked data
        given(articleServiceImpl.getArticlesByCategory(anyString())).willReturn(articleListDTO);

        //perform the REST call to the controller to get articles by category
        mockMvc.perform( get(ArticleController.BASE_URL+"/category/HIGHLIGHTS")
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articles.length()", is(2)));

        verify(articleServiceImpl, times(1)).getArticlesByCategory(anyString());
    }

    @Test
    void getArticlesByInvalidCategory() throws Exception {

        //given that the service call will return IllegalArgumentException
        given(articleServiceImpl.getArticlesByCategory(anyString())).willThrow(new IllegalArgumentException());

        //perform the REST call to the controller to get articles by invalid category
        mockMvc.perform( get(ArticleController.BASE_URL+"/category/blah")
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isNotFound());

        verify(articleServiceImpl, times(1)).getArticlesByCategory(anyString());
    }


    @Test
    void getArticlesByNoCategory() throws Exception {

        //given that the service call will return IllegalArgumentException
        given(articleServiceImpl.getArticlesByCategory(anyString())).willThrow(new IllegalArgumentException());

        //perform the REST call to the controller to get articles by invalid category
        mockMvc.perform( get(ArticleController.BASE_URL+"/category/ ")
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isNotFound());

        verify(articleServiceImpl, times(1)).getArticlesByCategory(anyString());
    }
}