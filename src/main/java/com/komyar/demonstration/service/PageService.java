package com.komyar.demonstration.service;

import com.komyar.demonstration.db.entity.PageEntity;
import com.komyar.demonstration.dto.request.PageRegistrationDto;
import com.komyar.demonstration.dto.request.PageUpdateDto;
import com.komyar.demonstration.dto.response.HttpResponse;
import com.komyar.demonstration.dto.response.PageInfoDto;
import com.komyar.demonstration.enums.Role;
import com.komyar.demonstration.exception.PageException;
import com.komyar.demonstration.db.repository.PageRepository;
import com.komyar.demonstration.security.principal.UserPrincipal;
import com.komyar.demonstration.util.DateUtil;
import com.komyar.demonstration.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.komyar.demonstration.enums.ResultMessage.*;

@Service
@Qualifier("userDetailsService")
public class PageService implements UserDetailsService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserPrincipal.convertFromPageEntity(pageRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User by the name of: " + username + " was not found.")));
    }

    public HttpResponse registerUser(PageRegistrationDto dto) {
        register(dto);
        return HttpResponse.create(SUCCESS_RESULT.getCode(), PAGE_REGISTERED_SUCCESSFULLY.getMessage());
    }

    private PageEntity register(PageRegistrationDto dto) {
        validateNewPage(dto.getUsername(), dto.getPhoneNumber());
        return pageRepository.save(getPageForRegistering(dto));
    }

    public ResponseEntity<HttpResponse> authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        HttpHeaders jwtHeader = jwtTokenProvider.getJwtHeader(UserPrincipal
                .convertFromPageEntity(findByUsername(username)));
        return new ResponseEntity<>(HttpResponse.create(SUCCESS_RESULT.getCode(), PAGE_LOGIN_WAS_SUCCESSFUL.getMessage()), jwtHeader, HttpStatus.OK);
    }

    private PageEntity findByUsername(String username) {
        return pageRepository.findByUsername(username).orElseThrow(() -> new PageException(PAGE_WAS_NOT_FOUND));
    }

    public String getSignedInPageUUID(){
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUuid();
    }
    public PageEntity getSignedInPage(){
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }


    private void validateNewPage(String username, String phoneNumber) {
        pageRepository.findByUsername(username).ifPresent(x -> {
            throw new PageException(USERNAME_ALREADY_EXISTS);
        });
        pageRepository.findByPhoneNumber(phoneNumber).ifPresent(x -> {
            throw new PageException(PHONE_NUMBER_ALREADY_EXISTS);
        });
    }

    private PageEntity getPageForRegistering(PageRegistrationDto dto) {
        return PageEntity.builder()
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .isActive(true)
                .isNonLocked(true)
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .role(Role.USER)
                .build();
    }


    public HttpResponse updatePage(PageUpdateDto dto) {
        PageEntity page = findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if ((dto.getUsername() != null && !dto.getUsername().isEmpty())
            || (dto.getFullName() != null && !dto.getFullName().isEmpty())
            || (dto.getPassword() != null && !dto.getPassword().isEmpty()))
            page.setUpdateDate(DateUtil.localDateTimeOfNow());
        if (dto.getUsername() != null && !dto.getUsername().isEmpty())
            page.setUsername(dto.getUsername());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty())
            page.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getFullName() != null && !dto.getFullName().isEmpty())
            page.setFullName(dto.getFullName());
        pageRepository.save(page);
        return HttpResponse.create(PAGE_UPDATE_WAS_SUCCESSFUL);
    }

    private PageEntity findByUUID(String uuid) {
        return pageRepository.findByUuid(uuid).orElseThrow(() -> new PageException(PAGE_WAS_NOT_FOUND));
    }

    public PageEntity findByID(Long id) {
        return pageRepository.findById(id).orElseThrow(() -> new PageException(PAGE_WAS_NOT_FOUND));
    }
    public HttpResponse deletePage(String uuid) {
        pageRepository.delete(findByUUID(uuid));
        return HttpResponse.create(PAGE_DELETION_WAS_SUCCESSFUL);
    }

    public PageEntity savePage(PageEntity page){
        return pageRepository.save(page);
    }

    public HttpResponse getPageInfo() {
        return HttpResponse.create(SUCCESS_RESULT.getCode(),
                getInfoFromDto(findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
    }

    private PageInfoDto getInfoFromDto(PageEntity page) {
        List<String> following = page.getFollowing().parallelStream().map(x -> x.getFullName()).collect(Collectors.toList());
        List<String> followedBy = page.getFollowedBy().parallelStream().map(x -> x.getFullName()).collect(Collectors.toList());
        return PageInfoDto.builder().following(following).followedBy(followedBy).fullName(page.getFullName())
                .username(page.getUsername()).phoneNumber(page.getPhoneNumber()).updateDate(page.getUpdateDate()).build();
    }

}
