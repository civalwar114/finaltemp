package com.example.demo.dto;

import com.example.demo.model.EmbEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmbDTO {
	
	
	public EmbDTO(final EmbEntity entity) {
		this.country_iso_alp2=entity.getCountry_iso_alp2();
		this.country_nm=entity.getCountry();
		this.embassy_kor_nm = entity.getEmbassy_kor_nm();
		this.emblgbd_addr = entity.getEmblgbd_addr();
		this.tel_no = entity.getTel_no();
		this.urgency_tel_no = entity.getUrgency_tel_no();
		
	}

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
	
	
}
