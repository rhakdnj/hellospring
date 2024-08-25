package com.example.hellospring.order;

import java.math.BigDecimal;

public record OrderRequest(
	OrderNumber orderNumber,
	BigDecimal totalPrice
) {
}
