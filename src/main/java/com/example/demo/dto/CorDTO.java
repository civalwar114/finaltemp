package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorDTO {

	private String resultCode; //��� �ڵ�
	private String resultMsg; //����޼���
	private String numOfRows; //�� ������ ����� 
	private String pageNo; //������ ��ȣ
	private String totalCount; //��ü �����
	private String currentCount; //���� �����
	private String country_nm; //�����
	private String country_eng_nm;  //������
	private String country_iso_alp2; //���� �ڵ�
	private String notice_id; //��ġ��Ȳ id
	private String title;  // �ؿ� �Ա��ڿ� ���� ��ġ
	private String txt_origin_cn; //��� ���� �� �Ա�����
	private String html_origin_cn; //ũ�� ����� ���� ����
	
}
