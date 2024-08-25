package com.example.hellospring.infra.repository;

import com.example.hellospring.order.Order;
import com.example.hellospring.order.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
	private final JpaOrderRepository jpaOrderRepository;
	private final JdbcOrderRepository jdbcOrderRepository;

	@Override
	public void save(Order order) {
		this.jdbcOrderRepository.save(order);
	}
}
