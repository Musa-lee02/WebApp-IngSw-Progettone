package pattern.skillmatchbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.model.email.EmailSender;

@SpringBootApplication
@ServletComponentScan
public class SkillMatchBackEndApplication {

	public static void main(String[] args) { SpringApplication.run(SkillMatchBackEndApplication.class, args); }

}
