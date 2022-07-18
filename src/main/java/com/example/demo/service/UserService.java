package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Slf4j
@Service

public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity ==null || userEntity.getEmail()==null) {
			
			throw new RuntimeException("Invalid arguments");
		} 
		final String email = userEntity.getEmail();
		if(userRepository.existsByEmail(email)) {
			log.warn("email alredy exist {}", email);
			throw new RuntimeException("Email aleady exsits");
		}
		
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(final String email, final String password) {
		return userRepository.findByEmailAndPassword(email, password);
		
	}
	
	public UserEntity modify(final UserEntity userEntity) {
		// È¸¿ø¼öÁ¤
		
		
		return null;
	}
	
	
	
	private void validate(final UserEntity entity) {
		if(entity ==null) {
			 log.warn("Entity cannot be null.");
		     throw new RuntimeException("Entity cannot be null.");
		}
		
		if(entity.getEmail()==null) {
			 log.warn("Unknown user.");
		     throw new RuntimeException("Unknown user.");
		}	
	}
	
	
	public List<UserEntity> retrieve(final String email) {
		return (List<UserEntity>) userRepository.findByEmail(email);
	}
	
	
	/*
	@Transactional
	public void delete(final UserEntity userEntity) {
		//Å»Åð
		try {
			//UserEntity entity = userRepository.findByEmail(userEntity);
			userRepository.delete(userEntity);
		}catch(Exception e) {
			log.error("Å»Åð¿¡·¯");
			throw new RuntimeException("test 1234");
		}
		
		//return retrieve(userEntity.getEmail());
	}*/
	

	@Transactional
	public void delete2(String email) {
		UserEntity entity = userRepository.findByEmail(email);
		userRepository.delete(entity);
		
	}
	
	
	
	
}
