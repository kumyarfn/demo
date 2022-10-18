package com.komyar.demonstration.controller;

import com.komyar.demonstration.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;


    @PostMapping("/follow/{id}")
    @PreAuthorize("hasAuthority('user::write')")
    public ResponseEntity<Object> followPage(@PathVariable Long id){
        return ResponseEntity.ok(followService.followPage(id));
    }

    @PostMapping("/unfollow/{id}")
    @PreAuthorize("hasAuthority('user::write')")
    public ResponseEntity<Object> unFollowPage(@PathVariable Long id){
        return ResponseEntity.ok(followService.unfollowPage(id));
    }
}
