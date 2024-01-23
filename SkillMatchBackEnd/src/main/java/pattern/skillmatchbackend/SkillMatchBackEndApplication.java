package pattern.skillmatchbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import pattern.skillmatchbackend.model.Lavoratore;
import pattern.skillmatchbackend.model.Ambito;
import pattern.skillmatchbackend.model.Observer;
import pattern.skillmatchbackend.model.Utente;
import pattern.skillmatchbackend.model.email.EmailSender;
import pattern.skillmatchbackend.persistenza.DBManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SpringBootApplication
@ServletComponentScan
public class SkillMatchBackEndApplication {

	public static void main(String[] args) {

		SpringApplication.run(SkillMatchBackEndApplication.class, args);

		String[] ambiti = {"Cucina", "Tecnologia", "Edilizia", "Elettronica", "Meccanica", "Informatica", "Altro"};
		for (int i = 0; i < ambiti.length; i++) {

			Ambito ambito = new Ambito();
			ambito.setNome(ambiti[i]);
			DBManager.getInstance().getAmbitoDao().saveOrUpdate(ambito);

		}

		//DBManager.getInstance().getAmbitoDao().findAll().forEach(System.out::println);


	}

}
