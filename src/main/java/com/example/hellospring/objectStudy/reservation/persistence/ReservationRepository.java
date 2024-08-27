package com.example.hellospring.objectStudy.reservation.persistence;

import com.example.hellospring.objectStudy.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
