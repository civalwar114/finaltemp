package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SocialLoginType;
import com.example.demo.service.OauthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class OauthController {

	private final OauthService oauthService;
	
	 @GetMapping(value = "/{socialLoginType}")
	    public void socialLoginType(
	            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
			log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
	        //OauthService.request(socialLoginType);
	    }

	  @GetMapping(value = "/{socialLoginType}/callback")
	    public String callback(
	            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
	            @RequestParam(name = "code") String code) {
	        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
	        return "";
	    }
	
	
	
}
