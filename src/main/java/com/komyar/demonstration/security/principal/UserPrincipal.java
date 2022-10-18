package com.komyar.demonstration.security.principal;


import com.komyar.demonstration.db.entity.PageEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


public class UserPrincipal implements UserDetails {


    public static UserPrincipal convertFromPageEntity(PageEntity page){
        return new UserPrincipal(page);
    }

    private UserPrincipal(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    private PageEntity pageEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return pageEntity.getRole().getAuthorities().parallelStream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return pageEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return pageEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return pageEntity.getIsNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return pageEntity.getIsActive();
    }
}
