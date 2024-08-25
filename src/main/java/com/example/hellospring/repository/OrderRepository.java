package com.example.hellospring.repository;

import com.example.hellospring.order.Order;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepository {
	private final EntityManagerFactory emf;

	public void save(Order order) {
		var em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		try {
			em.persist(order);

			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction.isActive()) transaction.rollback();
			throw e;
		} finally {
			if (em.isOpen()) em.close();
		}
	}
}
