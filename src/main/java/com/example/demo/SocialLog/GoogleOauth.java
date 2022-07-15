package com.example.demo.SocialLog;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.persistence.SocialOauth;

@Component
public class GoogleOauth implements SocialOauth {
	
	@Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;
    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
	
	@Override
    public String getOauthRedirectURL() {
		 Map<String, Object> params = new HashMap<>();
		  params.put("scope", "profile");
	      params.put("response_type", "code");
	      params.put("client_id", GOOGLE_SNS_CLIENT_ID);
	      params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
	        String parameterString = params.entrySet().stream()
	                .map(x -> x.getKey() + "=" + x.getValue())
	                .collect(Collectors.joining("&"));

	        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }
}
