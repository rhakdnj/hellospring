package com.example.hellospring.objectStudy.reservation.domain;

import com.example.hellospring.objectStudy.generic.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class DiscountPolicy {
	public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long movieId;
	@Enumerated(value = EnumType.STRING)
	private PolicyType policyType;
	@Embedded @AttributeOverride(name = "amount", column = @Column(name = "amount"))
	private Money amount;
	private Double percent;
	@OneToMany(mappedBy = "policy", fetch = FetchType.EAGER)
	private List<DiscountCondition> conditions;

	public boolean hasSatisfiedCondition(Screening screening) {
		return conditions.stream()
				.anyMatch(condition -> condition.isSatisfiedBy(screening));
	}

	public Money calculateDiscount(Movie movie) {
		if (isAmountPolicy()) {
			return this.amount;
		}

		if (isPercentPolicy()) {
			return movie.getFee().times(this.percent);
		}

		return Money.ZERO;
	}


	private Boolean isAmountPolicy() {
		return PolicyType.AMOUNT_POLICY.equals(policyType);
	}

	private Boolean isPercentPolicy() {
		return PolicyType.PERCENT_POLICY.equals(policyType);
	}
}
