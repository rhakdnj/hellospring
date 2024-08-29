package com.example.hellospring.objectStudy.reservation.domain;

import com.example.hellospring.objectStudy.generic.TimeInterval;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class DiscountCondition {
	public enum ConditionType {
		PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINED_CONDITION
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private DiscountPolicy policy;
	@Enumerated(value = EnumType.STRING)
	private ConditionType conditionType;
	private DayOfWeek dayOfWeek;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "startTime", column = @Column(name = "startTime")),
		@AttributeOverride(name = "endTime", column = @Column(name = "endTime"))
	})
	private TimeInterval interval;
	private Integer sequence;

	public boolean isSatisfiedBy(Screening screening) {
		if (isPeriodCondition()) {
			return screening.isPlayedIn(this.dayOfWeek, this.interval.startTime(), this.interval.endTime());
		}

		if (isSequenceCondition()) {
			return this.sequence.equals(screening.getSequence());
		}

		if (isCombinedCondition()) {
			return screening.isPlayedIn(this.dayOfWeek, this.interval.startTime(), this.interval.endTime())
				&& this.sequence.equals(screening.getSequence());
		}
		return false;
	}

	private boolean isPeriodCondition() {
		return ConditionType.PERIOD_CONDITION.equals(conditionType);
	}

	private boolean isSequenceCondition() {
		return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
	}

	private boolean isCombinedCondition() {
		return ConditionType.COMBINED_CONDITION.equals(conditionType);
	}
}
