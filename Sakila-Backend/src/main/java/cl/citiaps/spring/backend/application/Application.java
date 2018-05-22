package cl.citiaps.spring.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"cl.citiaps.spring.backend.application", "cl.citiaps.spring.backend.rest"})
@EntityScan("cl.citiaps.spring.backend.entities")
@EnableJpaRepositories("cl.citiaps.spring.backend.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}