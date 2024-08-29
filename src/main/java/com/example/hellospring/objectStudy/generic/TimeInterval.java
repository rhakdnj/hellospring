package com.example.hellospring.objectStudy.generic;

import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public record TimeInterval(
	LocalTime startTime,
	LocalTime endTime
) {
}
