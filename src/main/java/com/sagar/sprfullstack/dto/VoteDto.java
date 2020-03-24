package com.sagar.sprfullstack.dto;

import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import com.sagar.sprfullstack.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
    private User user;
}
