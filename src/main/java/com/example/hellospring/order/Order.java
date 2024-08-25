package com.example.hellospring.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String number;

	private BigDecimal totalPrice;

	public Order(String number, BigDecimal totalPrice) {
		this.number = number;
		this.totalPrice = totalPrice;
	}
}
