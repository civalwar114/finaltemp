package com.example.demo.persistence;

import com.example.demo.SocialLog.GoogleOauth;
import com.example.demo.config.SocialLoginType;

public interface SocialOauth {

	
	  /**
     * �� Social Login �������� Redirect ó���� URL Build
     * ����ڷκ��� �α��� ��û�� �޾� Social Login Server ������ code ��û
     */
    String getOauthRedirectURL();
    
 
    
}
