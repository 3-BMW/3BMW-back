package com.bmw.bstagram.posts;

import com.bmw.bstagram.posts.domain.Posts;
import com.bmw.bstagram.posts.domain.PostsHashtags;
import com.bmw.bstagram.posts.domain.PostsHashtagsRepository;
import com.bmw.bstagram.posts.domain.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private PostsHashtagsRepository postsHashtagsRepository;

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
        postsHashtagsRepository.deleteAll();
    }

    @Test
    public void Posts_저장_테스트() {
        // given
        Long userId = 1L;
        String imgUrl = "http://s3.aws.com";
        String content = "content";
        int province = 0;
        int city = 1;
        List<String> hashtags = Arrays.asList("#자장면", "#맛있어");

        Set<PostsHashtags> postsHashtags = new HashSet<>();
        for (String s : hashtags) {
            postsHashtags.add(PostsHashtags.builder()
                    .hashtag(s)
                    .build());
        }

        Posts posts = Posts.builder()
                .user_id(userId)
                .imgUrl(imgUrl)
                .content(content)
                .province(province)
                .city(city)
                .postsHashtags(postsHashtags)
                .build();
        Long result = postsRepository.save(posts).getPost_id();

        // when
        List<Posts> post = postsRepository.findAll();
        List<PostsHashtags> postsHashtags1 = postsHashtagsRepository.findAll();

        // then
        Posts post1 = post.get(0);
        System.out.println(">>>>>>>>>>>>>>>>");
        assertThat(post1.getUser_id()).isEqualTo(userId);
        assertThat(post1.getImgUrl()).isEqualTo(imgUrl);
        assertThat(post1.getContent()).isEqualTo(content);
        assertThat(hashtags).contains(postsHashtags1.get(0).getHashtag());
        assertThat(hashtags).contains(postsHashtags1.get(1).getHashtag());

        assertThat(result).isEqualTo(1L);
    }
}
