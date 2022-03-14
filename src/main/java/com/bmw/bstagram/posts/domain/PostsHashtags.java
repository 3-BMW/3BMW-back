package com.bmw.bstagram.posts.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts_hashtags")
public class PostsHashtags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(targetEntity = Posts.class)
    @JoinColumn(name="post_id")
    private Posts posts;

    private String hashtag;

    @Builder
    public PostsHashtags(String hashtag) {
        this.hashtag = hashtag;
    }
}
