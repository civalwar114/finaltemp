package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmbSearchDTO {
	
	
	
	//private String center_tel_no;
	//private String country_eng_nm;
	private String country_iso_alp2;  //iso �ڵ�
	private String country_nm;   // ���� �̸�
	//private String embassy_cd;
	private String embassy_kor_nm; //���� �̸�
	//private String embassy_lat;  //����
	//private String embassy_lng;  //�浵
	//private String embassy_manage_ty_cd;
	//private String embassy_manage_ty_cd_nm;
	//private String embassy_ty_cd ;
	//private String embassy_ty_cd_nm ;
	private String emblgbd_addr;  //�ּ�
	//private String free_tel_no ;
	private String tel_no;  //��ȭ
	private String urgency_tel_no;  //��ȭ2
	
	private String serachdata;
	
}
