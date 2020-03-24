package com.sagar.sprfullstack.dto;

import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private Long id;
    private String postName;
    private String description;
    private String url;
    private String subredditName;
    private User user;

}
