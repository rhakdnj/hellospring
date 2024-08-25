package com.example.hellospring.infra.repository;

import com.example.hellospring.order.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

public class JdbcOrderRepository {
	private final JdbcClient jdbcClient;

	public JdbcOrderRepository(DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
	}

	/*@PostConstruct
	void init() {
		jdbcClient.sql("""
			create table if not exists orders (id varchar(255) primary key, number varchar(255), total_price numeric(38,2) primary key (id));
			alter table if exists orders add constraint UniqueOrderNumber unique (number);
			create sequence Orders_SEQ start with 1 increment by 50;
			""").update();
	}*/

	public void save(Order order) {
		Long id = jdbcClient.sql("select next value for orders_seq;").query(Long.class).single();
		order.setId(id);

		jdbcClient.sql("insert into orders (id, number, total_price) values (:id, :number, :totalPrice);")
			.param("id", id)
			.param("number", order.getNumber())
			.param("totalPrice", order.getTotalPrice())
			.update();
	}
}
