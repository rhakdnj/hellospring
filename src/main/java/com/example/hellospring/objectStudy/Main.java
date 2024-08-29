package com.example.hellospring.objectStudy;

import com.example.hellospring.objectStudy.generic.Money;
import com.example.hellospring.objectStudy.generic.TimeInterval;
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
import com.example.hellospring.objectStudy.reservation.service.ReservationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

import static java.time.LocalTime.of;

import static com.example.hellospring.objectStudy.reservation.domain.DiscountCondition.ConditionType.PERIOD_CONDITION;
import static com.example.hellospring.objectStudy.reservation.domain.DiscountCondition.ConditionType.SEQUENCE_CONDITION;
import static com.example.hellospring.objectStudy.reservation.domain.DiscountPolicy.PolicyType.AMOUNT_POLICY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;

public class Main {
	private final AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext(ReservationConfig.class);

	private final DiscountConditionRepository discountConditionRepository = beanFactory.getBean(DiscountConditionRepository.class);
	private final DiscountPolicyRepository discountPolicyRepository = beanFactory.getBean(DiscountPolicyRepository.class);
	private final MovieRepository movieRepository = beanFactory.getBean(MovieRepository.class);
	private final ScreeningRepository screeningRepository = beanFactory.getBean(ScreeningRepository.class);

	private final ReservationService reservationService = beanFactory.getBean(ReservationService.class);

	private Screening initializeData() {
		var movie = Movie.builder()
			.title("Avengers: Endgame")
			.runningTime(150)
			.fee(Money.won(10_000))
			.build();

		movieRepository.save(movie);

		var discountPolicy = DiscountPolicy.builder()
			.movieId(movie.getId())
			.policyType(AMOUNT_POLICY)
			.amount(Money.won(1000))
			.build();

		discountPolicyRepository.save(discountPolicy);

		discountConditionRepository.save(DiscountCondition.builder().policy(discountPolicy).conditionType(SEQUENCE_CONDITION).sequence(1).build());
		discountConditionRepository.save(DiscountCondition.builder().policy(discountPolicy).conditionType(SEQUENCE_CONDITION).sequence(10).build());
		discountConditionRepository.save(DiscountCondition.builder().policy(discountPolicy).conditionType(PERIOD_CONDITION)
			.dayOfWeek(MONDAY)
			.interval(new TimeInterval(of(10, 0), of(12, 0)))
			.build());
		discountConditionRepository.save(DiscountCondition.builder().policy(discountPolicy).conditionType(PERIOD_CONDITION)
			.dayOfWeek(WEDNESDAY)
			.interval(new TimeInterval(of(18, 0), of(21, 0)))
			.build());

		var screening = Screening.builder()
			.movieId(movie.getId())
			.sequence(7)
			.screeningTime(LocalDateTime.of(2024, 12, 11, 18, 0))
			.build();
		screeningRepository.save(screening);

		return screening;
	}

	private void run() {
		var screening = initializeData();

		Reservation reservation = reservationService.reserveScreening(1L, screening.getId(), 2);

		System.out.printf("관객수: %d, 요금: %s%n", reservation.getAudienceCount(), reservation.getFee());
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
