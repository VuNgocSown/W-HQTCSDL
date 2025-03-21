package com.thunga.web;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.thunga.web.repository.UserRepository;
import com.thunga.web.service.OrderService;

@SpringBootApplication
@ServletComponentScan
public class App implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final OrderService orderService;
	
	public App(UserRepository userRepository, OrderService orderService) {
		this.userRepository = userRepository;
		this.orderService = orderService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		
	}

}
