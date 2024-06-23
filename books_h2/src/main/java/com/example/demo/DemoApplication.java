package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		// browse to:
		// http://localhost:8090/h2-console
		// username: sa
		// pwd:
		// JDBC URL: jdbc:h2:mem:bookstore

		Object dataSource = context.getBean("dataSource");

		System.out.println("======================");
		System.out.println(dataSource);
		System.out.println("======================");

	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository repository) {
		return args -> {
			repository.save(new
					Book(null,
					"Spring boot in Action",
					471, "Craig Walls"));
		};
	}

}
