package com.sagar.sprfullstack.service;

import com.sagar.sprfullstack.dto.PostRequest;
import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.model.User;
import com.sagar.sprfullstack.repository.PostRepository;
import com.sagar.sprfullstack.repository.SubredditRepository;
import com.sagar.sprfullstack.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private PostRepository postRepository;
    private final AuthService authService;
    private final SubredditRepository subredditRepository;
    private SubredditService subredditService;
    private UserRepository userRepository;

    @Transactional
    public Post save(PostRequest postRequest){
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                           .orElseThrow(() -> new RuntimeException("Sunreddit not found"));
        log.info(String.valueOf(subreddit));
        User user = authService.getCurrentUser();
        postRequest.setSubredditName(subreddit.getName());
        postRequest.setUser(user);
        Post post = postRepository.save(mapPostDto(postRequest));
        return post;
    }

    public Post mapPostDto(PostRequest postRequest) {
        return Post.builder().postName(postRequest.getPostName())
                            .description(postRequest.getDescription())
                            .user(postRequest.getUser())
                            .voteCount(0)
                            .createdDate(Instant.now())
                            .subreddit(subredditRepository.findByName(postRequest.getSubredditName()).get())
                            .build();
    }

    public Optional<Post> findPostsByUser(Long id) {
        return postRepository.findByUser(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Post> getPost(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditService.get(subredditId).orElseThrow(() -> new RuntimeException("no subreddit found"));
        log.info(String.valueOf(subreddit.getPosts()));
        return postRepository.findAllBySubreddit(subreddit);
    }


    public List<Post> findPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        log.info(String.valueOf(user));
        return postRepository.findAllByUser(user);
    }
}
