package com.example.hellospring.order;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderServiceTxProxy implements OrderService {
	private final OrderService target;
	private final PlatformTransactionManager transactionManager;

	@Override
	public Order createOrder(OrderNumber orderNumber, BigDecimal totalPrice) {
		return new TransactionTemplate(transactionManager).execute(status ->
			target.createOrder(orderNumber, totalPrice)
		);
	}

	@Override
	public List<Order> createOrders(List<OrderRequest> requests) {
		return new TransactionTemplate(transactionManager).execute(status ->
			target.createOrders(requests)
		);
	}
}
