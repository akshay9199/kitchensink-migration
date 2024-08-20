package com.jboss.migration.kitchensink_migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class KitchensinkMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitchensinkMigrationApplication.class, args);
	}

}
