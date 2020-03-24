package com.sagar.sprfullstack.service;

import com.sagar.sprfullstack.dto.VoteDto;
import com.sagar.sprfullstack.model.Post;
import com.sagar.sprfullstack.model.User;
import com.sagar.sprfullstack.model.Vote;
import com.sagar.sprfullstack.repository.PostRepository;
import com.sagar.sprfullstack.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sagar.sprfullstack.model.VoteType.UPVOTE;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final AuthService authService;
    private final PostRepository postRepository;

    public Vote vote(VoteDto voteDto){
        User user = authService.getCurrentUser();
        voteDto.setUser(user);
        Post post = postRepository.findById(voteDto.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        voteDto.setPostId(post.getPostId());
        log.info(String.valueOf(post));

        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, user);
        if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new RuntimeException("Cannot vote in the same direction twice");
        }

        if(UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount() + 1);
        }
        else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        Vote vote  = voteRepository.save(mapToVoteDto(voteDto));
        return vote;
    }

    public Vote mapToVoteDto(VoteDto voteDto){
        return Vote.builder().voteType(voteDto.getVoteType())
                .post(postRepository.findById(voteDto.getPostId()).get())
                .user(voteDto.getUser())
                .build();
    }

}
