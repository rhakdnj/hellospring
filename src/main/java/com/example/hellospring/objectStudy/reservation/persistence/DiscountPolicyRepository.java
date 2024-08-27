package com.example.hellospring.objectStudy.reservation.persistence;

import com.example.hellospring.objectStudy.reservation.domain.DiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {
	Optional<DiscountPolicy> findByMovieId(Long movieId);
}
