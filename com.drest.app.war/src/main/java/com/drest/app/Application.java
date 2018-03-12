package com.drest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.drest.app.exceptions.AppException;
import com.drest.engine.Engine;
import com.drest.storage.mysql.service.impl.MysqlStorageServiceFacade;
import com.drest.storage.mysql.service.impl.UserDaoImpl;

@SpringBootApplication
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application.properties")
@Import(UserDaoImpl.class)
@ComponentScan({ "com.drest.app", "com.drest.storage.mysql.service" })
public class Application extends SpringBootServletInitializer {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				try {
					Engine engine = Engine.getInstance();
					engine.boot();
					((MysqlStorageServiceFacade) engine.getStorageService()).setTemplate(jdbcTemplate);
					
				} catch (AppException ex) {
					ex.log();
					throw new RuntimeException(ex);
				}
			}
		};

	}
}