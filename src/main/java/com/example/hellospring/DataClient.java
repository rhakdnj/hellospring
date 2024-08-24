package com.example.hellospring;

import com.example.hellospring.order.Order;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
	public static void main(String[] args) {
		var beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		var emf = beanFactory.getBean(EntityManagerFactory.class);

		var em = emf.createEntityManager();
		em.getTransaction().begin();

		var order = new Order("100", BigDecimal.TEN);
		em.persist(order);

		em.getTransaction().commit();
		em.close();
	}
}
