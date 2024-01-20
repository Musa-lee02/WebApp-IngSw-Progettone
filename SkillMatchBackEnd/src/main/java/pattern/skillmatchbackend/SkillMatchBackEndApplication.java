package pattern.skillmatchbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.model.email.EmailSender;
import pattern.skillmatchbackend.persistenza.DBManager;

@SpringBootApplication
@ServletComponentScan //TODO Andrà scommentato per far funzionare le servelt. Finchè non ci saranno, darà errore
public class SkillMatchBackEndApplication {

	public static void main(String[] args) {

		SpringApplication.run(SkillMatchBackEndApplication.class, args);
		/*for(Lavoratore lavoratore: DBManager.getInstance().getLavoratoreDao().findAll()){

			System.out.println(lavoratore);
		}*/
	}

}
