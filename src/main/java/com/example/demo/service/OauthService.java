package com.example.demo.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.demo.SocialLog.GoogleOauth;
import com.example.demo.config.SocialLoginType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {
	private final GoogleOauth googleOauth;
	private final HttpServletResponse response;
	
	 public void request(SocialLoginType socialLoginType) {
	        String redirectURL;
	        switch (socialLoginType) {
	            case GOOGLE: {
	                redirectURL = googleOauth.getOauthRedirectURL();
	            } break;
	           /*case FACEBOOK: {
	                redirectURL = facebookOauth.getOauthRedirectURL();
	            } break;
	            case KAKAO: {
	                redirectURL = kakaoOauth.getOauthRedirectURL();
	            } break;
	            case NAVER: {
	                redirectURL = naverOauth.getOauthRedirectURL();
	            } break;*/ 
	            default: {
	                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
	            }
	        }
	        try {
	            response.sendRedirect(redirectURL);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
}
