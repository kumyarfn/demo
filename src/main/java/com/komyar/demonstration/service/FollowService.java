package com.komyar.demonstration.service;

import com.google.common.collect.Sets;
import com.komyar.demonstration.db.entity.PageEntity;
import com.komyar.demonstration.dto.response.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.komyar.demonstration.enums.ResultMessage.PAGE_WAS_SUCCESSFULLY_FOLLOWED;
import static com.komyar.demonstration.enums.ResultMessage.PAGE_WAS_SUCCESSFULLY_UNFOLLOWED;

@Service
public class FollowService {

    @Autowired
    private PageService pageService;


    public HttpResponse followPage(Long id){
        addToTheFollowing(pageService.getSignedInPage(), id);
        return HttpResponse.create(PAGE_WAS_SUCCESSFULLY_FOLLOWED);
    }

    private void addToTheFollowing(PageEntity page, Long id){
        if (page.getFollowing() == null) page.setFollowing(Sets.newHashSet(pageService.findByID(id)));
        else page.getFollowing().add(pageService.findByID(id));
        pageService.savePage(page);
    }

    public HttpResponse unfollowPage(Long id){
        unfollow(pageService.getSignedInPage(), id);
        return HttpResponse.create(PAGE_WAS_SUCCESSFULLY_UNFOLLOWED);
    }

    private void unfollow(PageEntity page, Long id){
        if (page.getFollowing() != null){
            Set<PageEntity> following = page.getFollowing();
            following.removeIf(x -> x.getId().equals(id));
            page.setFollowing(following);
            pageService.savePage(page);
        }
    }

}
