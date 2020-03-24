package com.sagar.sprfullstack.controller;

import com.sagar.sprfullstack.dto.SubredditDTO;
import com.sagar.sprfullstack.model.Subreddit;
import com.sagar.sprfullstack.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Subreddit> createSubreddit(@RequestBody SubredditDTO subredditDTO){
         return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDTO));
    }

    @GetMapping
    public ResponseEntity<List<Subreddit>> getAllSubreddits(){
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Subreddit>> getSubreddit(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.get(id));
    }

}

