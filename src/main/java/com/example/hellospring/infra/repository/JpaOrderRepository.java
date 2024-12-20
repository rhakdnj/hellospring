package com.example.hellospring.infra.repository;

import com.example.hellospring.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Order order) {
		entityManager.persist(order);
	}
}
