package com.bmw.bstagram.posts.service;

import com.bmw.bstagram.posts.domain.Posts;
import com.bmw.bstagram.posts.domain.PostsRepository;
import com.bmw.bstagram.posts.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long savePosts(PostsDto.Info info) {
        Long result = 0L;
        try {
            Posts posts = info.toPostsEntity(info.toPostsHastagsEntity());
            result = postsRepository.save(posts).getPost_id();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
