package com.uppercaseband.bootstrap;

import com.uppercaseband.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootstrapTest {

    @Autowired
    ArticleRepository articleRepository;

    Bootstrap bootstrapData;


    @BeforeEach
    void setUp() {
        bootstrapData = new Bootstrap(articleRepository);
    }


    @Test
    void testRun() throws Exception {
        bootstrapData.run(null);

        assertThat(articleRepository.count()).isEqualTo(3);
    }
}
