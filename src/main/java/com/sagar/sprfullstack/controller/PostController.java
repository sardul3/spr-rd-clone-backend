package com.sagar.sprfullstack.controller;

import com.sagar.sprfullstack.dto.PostRequest;
import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.repository.SubredditRepository;
import com.sagar.sprfullstack.service.PostService;
import com.sagar.sprfullstack.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;
    private SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequest));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Post>> getPostsByUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostsByUser(userId));
    }

    @GetMapping("/by-subreddit/{subredditId}")
    public ResponseEntity<List<Post>>  getPostsBySubreddit(@PathVariable Long subredditId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostsBySubreddit(subredditId));
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<Post>>  getPostsByUsername(@PathVariable String username) {
        log.info(username);
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPostsByUsername(username));
    }
}
