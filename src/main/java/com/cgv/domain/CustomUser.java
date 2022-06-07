package com.cgv.domain;

import com.cgv.domain.entity.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collections;

@Getter
public class CustomUser extends org.springframework.security.core.userdetails.User {
    private String name;

    public CustomUser(User user) {
        super(user.getUsername(), user.getPwd(),
                Collections.unmodifiableList(AuthorityUtils.createAuthorityList(user.getRole().name())));

        this.name = user.getName();
    }
}
