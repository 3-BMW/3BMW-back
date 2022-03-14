package com.bmw.bstagram.posts.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsHashtagsRepository extends JpaRepository<PostsHashtags, Long> {
}
