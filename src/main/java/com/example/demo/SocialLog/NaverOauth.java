package com.example.demo.SocialLog;

import org.springframework.stereotype.Component;

import com.example.demo.persistence.SocialOauth;

@Component
public class NaverOauth implements SocialOauth {
	   @Override
	    public String getOauthRedirectURL() {
	        return "";
	    }
}
