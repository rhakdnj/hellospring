package com.example.hellospring;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.build();
	}

	/*
	 * JPA

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.example.hellospring");
		// 익명 클래스 + 인스턴스 초기화 블록
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
			setGenerateDdl(true);
			setShowSql(true);
		}});

		return emf;
	}

	@Bean
	public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
     *
     */

	@Bean
	public PlatformTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
}
