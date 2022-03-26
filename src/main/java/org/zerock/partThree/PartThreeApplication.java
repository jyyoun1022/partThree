package org.zerock.partThree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PartThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartThreeApplication.class, args);
	}

}
