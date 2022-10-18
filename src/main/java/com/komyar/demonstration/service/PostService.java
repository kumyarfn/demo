package com.komyar.demonstration.service;

import com.komyar.demonstration.db.entity.PostEntity;
import com.komyar.demonstration.db.repository.PostRepository;
import com.komyar.demonstration.dto.response.HttpResponse;
import com.komyar.demonstration.dto.response.PostOutDto;
import com.komyar.demonstration.exception.PostException;
import com.komyar.demonstration.util.PostConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.komyar.demonstration.enums.ResultMessage.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PageService pageService;


    public HttpResponse persistPost(String text){
        postRepository.save(new PostEntity(pageService.getSignedInPageUUID(), text, 0L, 0L));
        return HttpResponse.create(POST_WAS_SUCCESSFULLY_SAVES);
    }

    public HttpResponse updatePost(String uuid, String text){
        PostEntity post = findByUUID(uuid);
        checkPost(post);
        post.setText(text);
        postRepository.save(post);
        return HttpResponse.create(POST_WAS_SUCCESSFULLY_UPDATED);
    }

    private PostEntity findByUUID(String uuid){
        return postRepository.findByUuid(uuid).orElseThrow(() -> new PostException(POST_WAS_NOT_FOUND));
    }

    private void checkPost(PostEntity post){
        if (!pageService.getSignedInPageUUID().equals(post.getPageUUID()))
            throw new PostException(NOT_ALLOWED_TO_UPDATE_POST);
    }

    public HttpResponse getPagesPost(Integer page, Integer pageSize){
        return HttpResponse.create(SUCCESS_RESULT.getCode(), getPostsOfUser(page, pageSize));
    }

    private List<PostOutDto> getPostsOfUser(Integer page, Integer pageSize){
        return postRepository.findByPageUUID(pageService.getSignedInPageUUID(),
                        PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationDate"))
                .get().parallel().map(PostConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
