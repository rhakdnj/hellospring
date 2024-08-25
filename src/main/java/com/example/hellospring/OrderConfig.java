package com.example.hellospring;

import com.example.hellospring.infra.repository.JdbcOrderRepository;
import com.example.hellospring.infra.repository.JpaOrderRepository;
import com.example.hellospring.infra.repository.OrderRepositoryImpl;
import com.example.hellospring.order.OrderRepository;
import com.example.hellospring.order.OrderService;
import com.example.hellospring.order.OrderServiceImpl;
import com.example.hellospring.order.OrderServiceTxProxy;
import com.example.hellospring.order.OrderSpringTxService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {
	@Bean
	public OrderService orderService(OrderRepository orderRepository,
									 @Qualifier("jdbcTransactionManager")
									 PlatformTransactionManager transactionManager) {
		return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository), transactionManager);
	}

	@Bean
	public OrderSpringTxService orderSpringTxService(OrderRepository orderRepository) {
		return new OrderSpringTxService(orderRepository);
	}

	@Bean
	public OrderRepository orderRepository(
		JpaOrderRepository jpaOrderRepository,
		JdbcOrderRepository jdbcOrderRepository
	) {
		return new OrderRepositoryImpl(jpaOrderRepository, jdbcOrderRepository);
	}

	@Bean
	public JdbcOrderRepository jdbcOrderRepository(DataSource dataSource) {
		return new JdbcOrderRepository(dataSource);
	}

	@Bean
	public JpaOrderRepository jpaOrderRepository() {
		return new JpaOrderRepository();
	}
}
