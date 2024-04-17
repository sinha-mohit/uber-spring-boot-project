package uberbackend.uberreviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // related to createdAt and updatedAt
public class UberReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberReviewServiceApplication.class, args);
	}

}
