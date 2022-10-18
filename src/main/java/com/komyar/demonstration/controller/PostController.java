package com.komyar.demonstration.controller;

import com.komyar.demonstration.dto.response.HttpResponse;
import com.komyar.demonstration.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{text}")
    @PreAuthorize("hasAuthority('user::write')")
    public ResponseEntity<HttpResponse> persistPost(@PathVariable("text") String text){
        return ResponseEntity.ok(postService.persistPost(text));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user::write')")
    public ResponseEntity<HttpResponse> persistPost(@RequestParam String uuid, @RequestParam String text){
        return ResponseEntity.ok(postService.updatePost(uuid, text));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user::write')")
    public ResponseEntity<HttpResponse> getPosts(@RequestParam Integer page, @RequestParam Integer pageSize){
        return ResponseEntity.ok(postService.getPagesPost(page, pageSize));
    }
}
