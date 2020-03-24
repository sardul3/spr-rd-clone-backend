package com.sagar.sprfullstack.service;

import com.sagar.sprfullstack.dto.CommentDto;
import com.sagar.sprfullstack.model.Comment;
import com.sagar.sprfullstack.model.NotificationEmail;
import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import com.sagar.sprfullstack.repository.CommentRepository;
import com.sagar.sprfullstack.repository.PostRepository;
import com.sagar.sprfullstack.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final PostRepository postRepository;
    private final MailService mailService;
    private final UserRepository userRepository;

    public Comment save(CommentDto commentDto) {
        User user = authService.getCurrentUser();
        commentDto.setUser(user);
        Optional<Post> post = postRepository.findById(commentDto.getPostId());
        log.info(String.valueOf(post.get()));
        commentDto.setPostId(post.get().getPostId());
        Comment comment =  commentRepository.save(mapCommentDto(commentDto));

        mailService.sendMail(new NotificationEmail("A new comment from " + user.getUsername(), post.get().getUser().getEmail(), "" +
                "There is a new comment on your post \n"
        ));

        return comment;
    }

    public Comment mapCommentDto(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .post(postRepository.findById(commentDto.getPostId()).get())
                .user(commentDto.getUser())
                .createdDate(Instant.now())
                .build();
    }

    public List<Comment> getCommentsByPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return commentRepository.findAllByPost(post);
    }

    public List<Comment> getCommentsByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username (" + username + ") not found"));
        return commentRepository.findAllByUser(user);
    }
}
