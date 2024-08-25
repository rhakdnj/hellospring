package com.example.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
	Order createOrder(OrderNumber orderNumber, BigDecimal totalPrice);

	List<Order> createOrders(List<OrderRequest> requests);
}
