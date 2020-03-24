package com.sagar.sprfullstack.repository;

import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUser(Long id);

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findAllByUser(User user);

}
