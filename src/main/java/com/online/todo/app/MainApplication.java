package com.online.todo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**	
 * Application Main
 * 
 * @author Manoj Pandey
 */
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
        logger.debug("application started");
    }
}
