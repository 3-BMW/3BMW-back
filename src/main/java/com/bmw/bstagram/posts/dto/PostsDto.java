package com.bmw.bstagram.posts.dto;

import com.bmw.bstagram.posts.domain.Posts;
import com.bmw.bstagram.posts.domain.PostsHashtags;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostsDto {

    @Getter
    @Builder
    public static class Info {
        @JsonProperty("author")
        private final Long author;
        @JsonProperty("image")
        private final String imgUrl;
        @JsonProperty("content")
        private final String content;
        @JsonProperty("location")
        private final Location location;
        @JsonProperty("hashtags")
        private final List<String> hashtags;
        @JsonProperty("public")
        private final boolean open;

        public Posts toPostsEntity(Set<PostsHashtags> hashtags) {
            return Posts.builder()
                    .user_id(author)
                    .imgUrl(imgUrl)
                    .content(content)
                    .postsHashtags(hashtags)
                    .province(location.getProvince())
                    .city(location.getCity())
                    .build();
        }
        public Set<PostsHashtags> toPostsHastagsEntity() {
            Set<PostsHashtags> postsHashtags = new HashSet<>();
            for (String hash : hashtags) {
                postsHashtags.add(PostsHashtags.builder()
                        .hashtag(hash)
                        .build());
            }
            return postsHashtags;
        }
    }

    @Getter
    @Builder
    public static class Location {
        @JsonProperty("province")
        private final int province;
        @JsonProperty("city")
        private final int city;

    }


}
