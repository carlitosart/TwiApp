package bo.ucbx.twiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TwiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwiappApplication.class, args);
	}

}
