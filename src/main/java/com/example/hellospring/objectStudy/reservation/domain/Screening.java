package com.example.hellospring.objectStudy.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Screening {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long movieId;
	private Integer sequence;
	private LocalDateTime screeningTime;

	public boolean isPlayedIn(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
		return this.screeningTime.getDayOfWeek().equals(dayOfWeek) &&
			(this.screeningTime.toLocalTime().equals(startTime) || this.screeningTime.toLocalTime().isAfter(startTime)) &&
			(this.screeningTime.toLocalTime().equals(endTime) || this.screeningTime.toLocalTime().isBefore(endTime));
	}
}
