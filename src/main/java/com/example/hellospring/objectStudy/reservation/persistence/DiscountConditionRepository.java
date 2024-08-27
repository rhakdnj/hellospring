package com.example.hellospring.objectStudy.reservation.persistence;

import com.example.hellospring.objectStudy.reservation.domain.DiscountCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountConditionRepository extends JpaRepository<DiscountCondition, Long> {
	List<DiscountCondition> findByPolicyId(Long policyId);
}
