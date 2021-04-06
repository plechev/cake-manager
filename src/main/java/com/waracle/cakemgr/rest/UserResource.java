package com.waracle.cakemgr.rest;

import com.waracle.cakemgr.domain.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User userDetails() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            System.out.println(oauthToken);
            String name = oauthToken.getPrincipal().getAttribute("name");
            if (name == null) {
                name = oauthToken.getPrincipal().getName();
            }
            return new User(name);
        } else if (authentication != null) {
            return new User(authentication.getName());
        }
        throw new AuthenticationCredentialsNotFoundException("User not authenticated");
    }

}
