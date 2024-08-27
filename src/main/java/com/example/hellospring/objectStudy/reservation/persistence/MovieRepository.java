package com.example.hellospring.objectStudy.reservation.persistence;

import com.example.hellospring.objectStudy.reservation.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
