package com.p1.JPA;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class GranjaSAApplication {

	public static void main(String[] args) {
		SpringApplication.run(GranjaSAApplication.class, args);
	}

}	
@Configuration
class DatabaseConfig {
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/granja");
		dataSource.setUsername( "root" );
		dataSource.setPassword( "" );
	return dataSource;
	}
}