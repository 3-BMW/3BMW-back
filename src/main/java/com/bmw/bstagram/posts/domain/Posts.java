package com.bmw.bstagram.posts.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    private Long user_id;

    @Column(name = "img_url", length = 500, nullable = false)
    private String imgUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.ALL})
    private Set<PostsHashtags> hashtags = new HashSet<>();

    private int province;
    private int city;

    @Builder
    public Posts(Long user_id, String imgUrl, String content, Set<PostsHashtags> postsHashtags, int province, int city) {
        this.user_id = user_id;
        this.imgUrl = imgUrl;
        this.content = content;
        this.hashtags = postsHashtags;
        this.province = province;
        this.city = city;
    }


}
