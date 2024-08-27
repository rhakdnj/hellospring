package com.example.hellospring.objectStudy.reservation.persistence;

import com.example.hellospring.objectStudy.reservation.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
