package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	
	//�����͸� ����Ʈ���� �޾� ����
	//�ڸ��� ��ġ�� Ȯ���ϰ�
	//���� ���� �׳� �˾Ƽ� ���� �ִ� ��������	
	//����Ʈ �������� �޾ƿ���?
	//�ƴ� ��Ƽ���� ī�� ��ȣ ���� �Է�/���� �Ȱ��� Ȯ�� �Ǹ�
	//�̶� ���� ���ڸ� �ѷ��ְ�
	
	//length�� Ư�� ���̿� �´��� Ȯ�� �� �� ���� ���� �Ѹ���.
	
	// ������ ���� �ڵ���� - �����-ȣ��-�����-�����н� 4��
	@PostMapping
	public void payment() {
		//if ��¼�� length = 16 && length  ���屸 �켭 
		//�� �� ������ ���� ���� �Ѹ���
		
	}
	
	
}
