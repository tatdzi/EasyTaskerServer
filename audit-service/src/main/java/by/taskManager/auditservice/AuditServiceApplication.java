package by.taskManager.auditservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class AuditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditServiceApplication.class, args);
	}

}
