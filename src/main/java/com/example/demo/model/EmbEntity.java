package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="embtest")
public class EmbEntity {

	@Id
	private int tempno;
	private String country_iso_alp2;  //iso �ڵ�
	private String country;   // ���� �̸�
	
	private String embassy_kor_nm; //���� �̸�

	private String emblgbd_addr;  //�ּ�

	private String tel_no;  //��ȭ
	private String urgency_tel_no;  //��ȭ2
	
}
