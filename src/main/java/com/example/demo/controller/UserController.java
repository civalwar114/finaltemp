package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	
	@PostMapping("/update")
	public ResponseEntity<?> modify(@RequestBody UserEntity userEntity,UserDTO userDTO){
		UserEntity user = userService.modify(userEntity);
		
		
		return null;
	}
	
	
	/*
	@DeleteMapping("/delete")
	public void delete(@RequestBody UserDTO dto ){
		try {	
			  UserEntity entity = UserDTO.usEntity(dto);
			  System.out.println(entity);			  			  
			  userService.delete(entity);
			 		
		}catch(Exception e) {
			
		}
	}*/
	
	
	@DeleteMapping("/delete/{email}")
	public void delete2(@PathVariable String email){
		userService.delete2(email);
		//프론트에서 특정 버튼을 누르면 여기로 리다이렉트 되게 하면될듯
		
	}

	
	
}
