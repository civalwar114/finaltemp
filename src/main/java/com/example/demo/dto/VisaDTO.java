package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaDTO {
	
	private String resultCode;
	private String resultMsg;
	private String numOfRows;
	private String pageNo;
	private String totalCount;
	private String currentCount;
	private String country_nm; //������
	private String have_yn; //���� ���� ����
	private String gnrl_pspt_visa_yn; //�Ϲ� ���� �Ա� �㰡 ��� ����
	private String gnrl_pspt_visa_cn; //�Ϲ� ���� �Ա� �㰡 ��� ����

	private String remark;
}
