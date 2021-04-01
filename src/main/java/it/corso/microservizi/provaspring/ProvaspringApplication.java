package it.corso.microservizi.provaspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProvaspringApplication {

	@Value("${server.port}")
	int port;

	public static void main(String[] args) {
		SpringApplication.run(ProvaspringApplication.class, args);
	}

}
