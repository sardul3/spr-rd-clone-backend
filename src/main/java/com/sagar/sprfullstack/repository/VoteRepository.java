package com.sagar.sprfullstack.repository;

import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import com.sagar.sprfullstack.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}
