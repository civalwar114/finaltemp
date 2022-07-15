package com.example.demo.SocialLog;

import org.springframework.stereotype.Component;

import com.example.demo.persistence.SocialOauth;

@Component
public class KakaoOauth implements SocialOauth {
	 @Override
	    public String getOauthRedirectURL() {
	        return "";
	    }
}
