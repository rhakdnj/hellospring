package com.example.hellospring.objectStudy.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class DiscountCondition {
	public enum ConditionType {
		PERIOD_CONDITION, SEQUENCE_CONDITION
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long policyId;
	@Enumerated(value = EnumType.STRING)
	private ConditionType conditionType;
	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
	private Integer sequence;

	public boolean isPeriodCondition() {
		return ConditionType.PERIOD_CONDITION.equals(conditionType);
	}

	public boolean isSequenceCondition() {
		return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
	}
}
