package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {

	@Autowired 
	private TodoDAO repository2;
	
		
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("°¡³ª´Ù").build();		
		repository2.save(entity);		
		TodoEntity savedEntity = repository2.findById(entity.getId()).get();		
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> findAll(){
		
		return repository2.findAll();
				
	
	}
	
	  public void insert(TodoDTO dto) {
	      TodoEntity entity = TodoEntity
	            .builder()
	            .title(dto.getTitle())
	            .done(dto.isDone())
	            .build();
	      repository2.save(entity);
	   }

	
}
