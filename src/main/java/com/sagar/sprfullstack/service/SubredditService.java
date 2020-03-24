package com.sagar.sprfullstack.service;

import com.sagar.sprfullstack.dto.SubredditDTO;
import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public Subreddit save(SubredditDTO subredditDTO){
        Subreddit subreddit = subredditRepository.save(mapSubredditDto(subredditDTO));
        subredditDTO.setId(subreddit.getId());
        return subreddit;

    }

    public Subreddit mapSubredditDto(SubredditDTO subredditDTO) {
        return Subreddit.builder().name(subredditDTO.getName())
                                    .description(subredditDTO.getDescription())
                                    .build();
    }

    @Transactional(readOnly = true)
    public List<Subreddit> getAll() {
        List<Subreddit> subreddits = subredditRepository.findAll();
        return subreddits;
    }

    public Optional<Subreddit> get(Long id) {
        return subredditRepository.findById(id);
    }
}
