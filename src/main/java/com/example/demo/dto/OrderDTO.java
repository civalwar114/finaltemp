package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private String card;  //ī�� ��ȣ 16�ڸ�
	private String cardper;  //ī�� �Ⱓ
	private String cvc;  //cvc
	private String passport;  //����
	private String engname;  //�����̸�
	private String peoNum;  //�ο���
	
	
	
}
