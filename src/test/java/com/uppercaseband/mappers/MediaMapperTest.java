package com.uppercaseband.mappers;

import com.uppercaseband.domain.Media;
import com.uppercaseband.domain.MediaType;
import com.uppercaseband.model.MediaDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MediaMapperTest {

    public static final String IMAGE = "IMAGE";

    @Autowired
    MediaMapper mediaMapper;


    @BeforeAll
    static void checkMapper() {
        assertNotNull(MediaMapper.INSTANCE);
    }

    @Test
    void mediaToMediaDTO() throws Exception {

        //given
        Media media = new Media();
        media.setType(MediaType.IMAGE);

        //when
        MediaDTO mediaDto = mediaMapper.mediaToMediaDTO(media);

        //then
        assertEquals(media.getType().name(), mediaDto.getType());
    }

    @Test
    void mediaDTOtoMedia() throws Exception {

        //given
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setType(IMAGE);

        //when
        Media media = mediaMapper.mediaDTOToMedia(mediaDTO);

        //then
        assertEquals(mediaDTO.getType(), media.getType().name());
    }
}