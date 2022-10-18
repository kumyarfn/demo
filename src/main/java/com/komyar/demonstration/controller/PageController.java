package com.komyar.demonstration.controller;

import com.komyar.demonstration.dto.request.LoginDto;
import com.komyar.demonstration.dto.request.PageRegistrationDto;
import com.komyar.demonstration.dto.request.PageUpdateDto;
import com.komyar.demonstration.dto.response.HttpResponse;
import com.komyar.demonstration.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> pageLogin(@RequestBody @Valid LoginDto dto) {
        return pageService.authenticate(dto.getUsername(),dto.getPassword());
    }

    @PostMapping
    public ResponseEntity<HttpResponse> registerPage(@RequestBody @Valid PageRegistrationDto dto) {
        return ResponseEntity.ok(pageService.registerUser(dto));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> updatePageInformation(@RequestBody PageUpdateDto dto){
        return ResponseEntity.ok(pageService.updatePage(dto));
    }

    @DeleteMapping("/{userUUID}")
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> deletePage(@PathVariable String userUUID){
        return ResponseEntity.ok(pageService.deletePage(userUUID));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user::read')")
    public ResponseEntity<HttpResponse> getPageInfo(){
        return ResponseEntity.ok(pageService.getPageInfo());
    }

}
