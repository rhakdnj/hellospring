package com.example.hellospring.objectStudy.generic;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public record Money(
	BigDecimal amount
) {
	public static final Money ZERO = new Money(BigDecimal.ZERO);

	public static Money won(long amount) {
		return new Money(BigDecimal.valueOf(amount));
	}

	public Money plus(Money amount) {
		return new Money(this.amount.add(amount.amount));
	}

	public Money minus(Money amount) {
		return new Money(this.amount.subtract(amount.amount));
	}

	public Money times(double percent) {
		return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
	}

	public Money divide(double divisor) {
		return new Money(this.amount.divide(BigDecimal.valueOf(divisor), 2, RoundingMode.UP));
	}

	public boolean isLessThan(Money other) {
		return this.amount.compareTo(other.amount) < 0;
	}

	public boolean isGreaterThanOrEqual(Money other) {
		return this.amount.compareTo(other.amount) >= 0;
	}

	public Long longValue() {
		return amount.longValue();
	}

	public Double doubleValue() {
		return amount.doubleValue();
	}
}
