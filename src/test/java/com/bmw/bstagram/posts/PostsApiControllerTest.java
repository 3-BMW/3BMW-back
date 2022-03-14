package com.bmw.bstagram.posts;

import com.bmw.bstagram.posts.domain.PostsRepository;
import com.bmw.bstagram.posts.dto.PostsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        Long userId = 1L;
        String imgUrl = "https://s3.com";
        String content = "content";
        int province = 1;
        int city = 0;
        List<String> hashtags = Arrays.asList("#자장면", "#중국집", "맛있어");
        boolean open = true;

        PostsDto.Location postsLocation = PostsDto.Location.builder()
                .province(province)
                .city(city)
                .build();
        PostsDto.Info postsInfo = PostsDto.Info.builder()
                .author(userId)
                .imgUrl(imgUrl)
                .content(content)
                .location(postsLocation)
                .hashtags(hashtags)
                .open(open)
                .build();


        String url = "http://localhost:" + port + "/api/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postsInfo, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
