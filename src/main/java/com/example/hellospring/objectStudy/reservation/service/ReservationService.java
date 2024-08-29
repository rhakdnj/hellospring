package com.example.hellospring.objectStudy.reservation.service;

import com.example.hellospring.objectStudy.generic.Money;
import com.example.hellospring.objectStudy.reservation.domain.Reservation;
import com.example.hellospring.objectStudy.reservation.persistence.DiscountPolicyRepository;
import com.example.hellospring.objectStudy.reservation.persistence.MovieRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ReservationRepository;
import com.example.hellospring.objectStudy.reservation.persistence.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ScreeningRepository screeningRepository;
	private final MovieRepository movieRepository;
	private final DiscountPolicyRepository discountPolicyRepository;
	private final ReservationRepository reservationRepository;

	public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
		var screening = screeningRepository.findById(screeningId)
			.orElseThrow(() -> new IllegalArgumentException("Screening not found"));

		var movie = movieRepository.findById(screening.getMovieId())
			.orElseThrow(() -> new IllegalArgumentException("Movie not found"));

		var policy = discountPolicyRepository.findByMovieId(movie.getId())
			.orElse(null);

		assert policy != null;
		var found = policy.findDiscountCondition(screening);

		Money fee;
		if (found) {
			fee = movie.getFee().minus(policy.calculateDiscount(movie));
		} else {
			fee = movie.getFee();
		}

		var reservation = makeReservation(customerId, screeningId, audienceCount, fee);
		reservationRepository.save(reservation);

		return reservation;
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
