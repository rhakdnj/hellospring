package com.example.hellospring.learningTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
	@Test
	void clock() {
		var clock = Clock.systemDefaultZone();

		var dt1 = LocalDateTime.now(clock);
		var dt2 = LocalDateTime.now(clock);

		Assertions.assertThat(dt2).isAfter(dt1);
	}

	@Test
	void fixedClock() {
		var clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

		var dt1 = LocalDateTime.now(clock);
		var dt2 = LocalDateTime.now(clock);

		var dt3 = LocalDateTime.now(clock).plusHours(1);

		Assertions.assertThat(dt2).isEqualTo(dt1);
		Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1L));
	}
}
