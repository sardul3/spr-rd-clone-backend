package com.sagar.sprfullstack.controller;

import com.sagar.sprfullstack.dto.VoteDto;
import com.sagar.sprfullstack.model.Vote;
import com.sagar.sprfullstack.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Vote> vote(@RequestBody VoteDto voteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.vote(voteDto));
    }

}
