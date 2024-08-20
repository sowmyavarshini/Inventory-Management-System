package com.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The entry point of the Inventory Management application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class InventorymanageApplication {
	/**
	 * The main method that serves as the entry point for the Java application.
	 * 
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(final String[] args) {
		ApplicationContext ctx = SpringApplication.run(InventorymanageApplication.class, args);

	}

}
