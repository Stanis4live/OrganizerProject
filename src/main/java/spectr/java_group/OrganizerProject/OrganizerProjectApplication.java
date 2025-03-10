package spectr.java_group.OrganizerProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class OrganizerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizerProjectApplication.class, args);
	}

}
