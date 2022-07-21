package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;  //���̵�
	
	@Column(nullable = false)
	private String username;  // �г���?
	
	@Column(nullable = false ,unique = true)
	private String email;  //�̸���
	
	@Column(nullable = false)
	private String password;  //��й�ȣ
	
	@Column(nullable = false)
	private String korname; //�ѱ� �̸�
	
	@Column(nullable = false)
	private String engname; //���� �̸�
	
	@Column(nullable = false)
	private String phone; //��ȭ��ȣ
	 
	
}
