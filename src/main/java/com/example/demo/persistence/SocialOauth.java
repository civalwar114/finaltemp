package com.example.demo.persistence;

public interface SocialOauth {

	
	  /**
     * �� Social Login �������� Redirect ó���� URL Build
     * ����ڷκ��� �α��� ��û�� �޾� Social Login Server ������ code ��û
     */
    String getOauthRedirectURL();
}
