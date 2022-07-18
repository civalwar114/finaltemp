package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
		TodoEntity entity = TodoEntity.builder().title("������").build();		
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

	
	  public List<TodoEntity> create(final TodoEntity entity) {
		    // Validations
		    validate(entity);

		    repository2.save(entity);

		    log.info("Entity Id : {} is saved.", entity.getId());

		    return repository2.findByuserId(entity.getUserId());

		  }
	  
	  
	  private void validate(final TodoEntity entity) {
		    if(entity == null) {
		      log.warn("Entity cannot be null.");
		      throw new RuntimeException("Entity cannot be null.");
		    }

		    if(entity.getUserId() == null) {
		      log.warn("Unknown user.");
		      throw new RuntimeException("Unknown user.");
		    }
		  }

		  public List<TodoEntity> retrieve(final String userId) {
		    return repository2.findByuserId(userId);
		  }

		  public List<TodoEntity> update(final TodoEntity entity) {
		    // (1) ���� �� ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�. �� �޼���� 2.3.1 Create Todo���� �����ߴ�.
		    validate(entity);

		    // (2) �Ѱܹ��� ��ƼƼ id�� �̿��� TodoEntity�� �����´�. �������� �ʴ� ��ƼƼ�� ������Ʈ �� �� ���� �����̴�.
		    final Optional<TodoEntity> original = repository2.findById(entity.getId());

		    original.ifPresent(todo -> {
		      // (3) ��ȯ�� TodoEntity�� �����ϸ� ���� �� entity�� ������ ���� �����.
		      todo.setTitle(entity.getTitle());
		      todo.setDone(entity.isDone());

		      // (4) �����ͺ��̽��� �� ���� �����Ѵ�.
		      repository2.save(todo);
		    });

		    // 2.3.2 Retrieve Todo���� ���� �޼��带 �̿��� ������ ��� Todo ����Ʈ�� �����Ѵ�.
		    return retrieve(entity.getUserId());
		  }

		  public List<TodoEntity> delete(final TodoEntity entity) {
		    // (1) ���� �� ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�. �� �޼���� 2.3.1 Create Todo���� �����ߴ�.
		    validate(entity);

		    try {
		      // (2) ��ƼƼ�� �����Ѵ�.
		      repository2.delete(entity);
		    } catch(Exception e) {
		      // (3) exception �߻��� id�� exception�� �α��Ѵ�.
		      log.error("error deleting entity ", entity.getId(), e);

		      // (4) ��Ʈ�ѷ��� exception�� ������. �����ͺ��̽� ���� ������ ĸ��ȭ �ϱ� ���� e�� �������� �ʰ� �� exception ������Ʈ�� �����Ѵ�.
		      throw new RuntimeException("error deleting entity " + entity.getId());
		    }
		    // (5) �� Todo����Ʈ�� ������ �����Ѵ�.
		    return retrieve(entity.getUserId());
		  }

	  
	  
	  
}
