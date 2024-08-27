package com.example.hellospring.objectStudy;

import com.example.hellospring.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.example.hellospring.objectStudy")
@EnableJpaRepositories(basePackages = "com.example.hellospring.objectStudy.reservation.persistence", transactionManagerRef = "jpaTransactionManager")
@Import(DataConfig.class)
public class ReservationConfig {
}
