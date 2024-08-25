package com.example.hellospring;

import com.example.hellospring.order.Order;
import com.example.hellospring.repository.OrderRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
	public static void main(String[] args) {
		var beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		var orderRepository = beanFactory.getBean(OrderRepository.class);
		var transactionManager = beanFactory.getBean(JpaTransactionManager.class);

		try {
			new TransactionTemplate(transactionManager).execute(status -> {
				Order order1 = new Order("100", BigDecimal.ONE);
				orderRepository.save(order1);

				System.out.println(order1);

				Order order2 = new Order("100", BigDecimal.TWO);
				orderRepository.save(order2);

				return null;
			});

		} catch (DataIntegrityViolationException e) {
			System.out.println("주문번호 중복 복구 작업");
		}
	}
}
