package com.sagar.sprfullstack.dto;

import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Long postId;
    private Instant createdDate;
    private User user;
}
