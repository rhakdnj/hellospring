package com.example.hellospring.objectStudy.reservation.service;

import com.example.hellospring.objectStudy.generic.Money;
import com.example.hellospring.objectStudy.reservation.domain.DiscountCondition;
import com.example.hellospring.objectStudy.reservation.domain.DiscountPolicy;
import com.example.hellospring.objectStudy.reservation.domain.Movie;
import com.example.hellospring.objectStudy.reservation.domain.Reservation;
import com.example.hellospring.objectStudy.reservation.domain.Screening;
import com.example.hellospring.objectStudy.reservation.persistence.DiscountConditionRepository;
import com.example.hellospring.objectStudy.reservation.persistence.DiscountPolicyRepository;
import com.example.hellospring.objectStudy.reservation.persistence.MovieRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ReservationRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ScreeningRepository screeningRepository;
	private final MovieRepository movieRepository;
	private final DiscountPolicyRepository discountPolicyRepository;
	private final DiscountConditionRepository discountConditionRepository;
	private final ReservationRepository reservationRepository;

	public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
		var screening = screeningRepository.findById(screeningId)
			.orElseThrow(() -> new IllegalArgumentException("Screening not found"));

		var movie = movieRepository.findById(screening.getMovieId())
			.orElseThrow(() -> new IllegalArgumentException("Movie not found"));

		var policy = discountPolicyRepository.findByMovieId(movie.getId())
			.orElse(null);

		var conditions = discountConditionRepository.findByPolicyId(policy.getId());

		var condition = findDiscountCondition(screening, conditions);

		Money fee;
		if (condition != null) {
			fee = movie.getFee().minus(calculateDiscount(policy, movie));
		} else {
			fee = movie.getFee();
		}

		var reservation = makeReservation(customerId, screeningId, audienceCount, fee);
		reservationRepository.save(reservation);

		return reservation;
	}

	private DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions) {
		for(DiscountCondition condition : conditions) {
			if (condition.isPeriodCondition()) {
				if (screening.isPlayedIn(condition.getDayOfWeek(),
					condition.getStartTime(),
					condition.getEndTime())) {
					return condition;
				}
			} else {
				if (condition.getSequence().equals(screening.getSequence())) {
					return condition;
				}
			}
		}

		return null;
	}

	private Money calculateDiscount(DiscountPolicy policy, Movie movie) {
		if (policy.isAmountPolicy()) {
			return policy.getAmount();
		} else if (policy.isPercentPolicy()) {
			return movie.getFee().times(policy.getPercent());
		}

		return Money.ZERO;
	}

	private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
		return Reservation.builder()
			.customerId(customerId)
			.screeningId(screeningId)
			.audienceCount(audienceCount)
			.fee(fee.times(audienceCount))
			.build();
	}
}
