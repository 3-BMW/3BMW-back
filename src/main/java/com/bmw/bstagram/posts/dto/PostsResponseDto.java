package com.bmw.bstagram.posts.dto;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class PostsResponseDto {

    private final int post_id;
//    private final String writer;
//    private final String img_url;
//    private final int likes;
//    private final Location location;
//    private final Optional<List> hashtags;

    public PostsResponseDto(final int post_id) {
        this.post_id = post_id;
    }
}
