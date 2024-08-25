package com.example.hellospring.order;

import com.example.hellospring.infra.repository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepository {
	private final JpaOrderRepository jpaOrderRepository;

	public void save(Order order) {
		jpaOrderRepository.save(order);
	}
}
