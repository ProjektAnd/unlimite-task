package net.enemyofmankind.ultimatetask;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@Import(UltimateTaskConfiguration.class)
public class UltimateTaskApplication {

	public static void main(String[] args) {
		run(UltimateTaskApplication.class, args);
	}

}
