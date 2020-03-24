package com.sagar.sprfullstack.repository;

import com.sagar.sprfullstack.model.Comment;
import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Optional<Post> post);

    List<Comment> findAllByUser(User user);

}
