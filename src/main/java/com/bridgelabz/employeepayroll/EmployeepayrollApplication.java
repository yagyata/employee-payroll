package com.bridgelabz.employeepayroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class EmployeepayrollApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeepayrollApplication.class, args);
		log.info("Employee Payroll App Started in {} Environment",
				context.getEnvironment().getProperty("environment"));
	}
}
