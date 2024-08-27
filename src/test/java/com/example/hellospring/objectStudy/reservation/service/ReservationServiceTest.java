package com.example.hellospring.objectStudy.reservation.service;

import com.example.hellospring.objectStudy.generic.Money;
import com.example.hellospring.objectStudy.reservation.domain.Screening;
import com.example.hellospring.objectStudy.reservation.persistence.DiscountConditionRepository;
import com.example.hellospring.objectStudy.reservation.persistence.DiscountPolicyRepository;
import com.example.hellospring.objectStudy.reservation.persistence.MovieRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ReservationRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
	@InjectMocks private ReservationService reservationService;

	@Mock private ScreeningRepository screeningRepository;
	@Mock private MovieRepository movieRepository;
	@Mock private DiscountPolicyRepository discountPolicyRepository;
	@Mock private DiscountConditionRepository discountConditionRepository;
	@Mock private ReservationRepository reservationRepository;

	@Test
	public void 금액할인정책_계산() {
		// given
//		Long customerId = 1L;
//		Long screeningId = 1L;
//		Long movieId = 1L;
//		Long policyId = 1L;
//
//		Mockito.when(screeningRepository.findById(screeningId))
//			.thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));
//
//		Mockito.when(movieRepository.selectMovie(movieId))
//			.thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));
//
//		Mockito.when(discountPolicyRepository.selectDiscountPolicy(movieId))
//			.thenReturn(new DiscountPolicy(policyId, movieId, AMOUNT_POLICY, Money.wons(1000), null));
//
//		Mockito.when(discountConditionRepository.selectDiscountConditions(policyId))
//			.thenReturn(List.of(
//				new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null, null, 1),
//				new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, null, 10),
//				new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0), null),
//				new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null)));
//
//		// when
//		var reservation = reservationService.reserveScreening(customerId, screeningId, 2);
//
//		// then
//		assertEquals(reservation.getFee(), Money.wons(18000));
	}

	@Test
	public void 비율할인정책_계산() {
		// given
//		Long customerId = 1L;
//		Long screeningId = 1L;
//		Long movieId = 1L;
//		Long policyId = 1L;
//
//		Mockito.when(screeningRepository.findById(screeningId))
//			.thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));
//
//		Mockito.when(movieRepository.findById(movieId))
//			.thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));
//
//		Mockito.when(discountPolicyRepository.findByMovieId(movieId))
//			.thenReturn(new DiscountPolicy(policyId, movieId, PERCENT_POLICY, null, 0.1));
//
//		Mockito.when(discountConditionRepository.findByPolicyId(policyId))
//			.thenReturn(List.of(
//				new DiscountCondition(1L, policyId, SEQUENCE_CONDITION, null, null, null, 1),
//				new DiscountCondition(2L, policyId, SEQUENCE_CONDITION, null, null, null, 10),
//				new DiscountCondition(3L, policyId, PERIOD_CONDITION, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0), null),
//				new DiscountCondition(4L, policyId, PERIOD_CONDITION, WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null)));
//
//		// when
//		var reservation = reservationService.reserveScreening(customerId, screeningId, 2);
//
//		// then
//		assertEquals(reservation.getFee(), Money.won(18000));
	}
}
