package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
		//요청을 해서 저장할 사용자 만들기
		try {
			UserEntity user = UserEntity.builder().email(userDTO.getEmail())
					.username(userDTO.getUsername()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			//서비스를 이용해 레포짓토리에 저장
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder().email(registeredUser.getEmail())
					.username(registeredUser.getUsername()).id(registeredUser.getId()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}catch(Exception e) {
			//사용자 정보는 항상 하나 이므로 리스트로 만들어야 하는 리스폰스DTO를 사용 하지 않고 그냥 유저DTO를 리턴
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
					.error("로그인 실패").build();
			return ResponseEntity.badRequest().body(responseDTO);			
		}
		
	}
	
	

	
	
	
	
	
	
}
