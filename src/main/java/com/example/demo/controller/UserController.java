package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.SocialLoginType;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.OauthService;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
		//��û�� �ؼ� ������ ����� �����
		try {
			UserEntity user = UserEntity.builder().email(userDTO.getEmail())
					.username(userDTO.getUsername()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			//���񽺸� �̿��� �������丮�� ����
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder().email(registeredUser.getEmail())
					.username(registeredUser.getUsername()).id(registeredUser.getId()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}catch(Exception e) {
			//����� ������ �׻� �ϳ� �̹Ƿ� ����Ʈ�� ������ �ϴ� ��������DTO�� ��� ���� �ʰ� �׳� ����DTO�� ����
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
		}		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
		
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO respoUserDTO = UserDTO.builder()
					.email(user.getEmail()).id(user.getId()).token(token)
					.korname(user.getKorname()).engname(user.getEngname())
					.phone(user.getPhone()).build();
			return ResponseEntity.ok().body(respoUserDTO);		
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("�α��� ����").build();
			return ResponseEntity.badRequest().body(responseDTO);			
		}
		
	}
	
	
	
	
}
