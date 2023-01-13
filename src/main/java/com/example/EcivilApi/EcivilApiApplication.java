package com.example.EcivilApi;

import com.example.EcivilApi.models.Genre;
import com.example.EcivilApi.models.Role;
import com.example.EcivilApi.models.Utilisateurs;
import com.example.EcivilApi.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;

import static com.example.EcivilApi.models.ERole.*;

@SpringBootApplication
@EnableAutoConfiguration
public class EcivilApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcivilApiApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(UtilisateurService utilisateurService){
		return args -> {
			//crée des roles des linitialisation de lapp
			utilisateurService.ajoutrole(new Role(null,USER));

			utilisateurService.ajoutrole(new Role(null,Agent));
			utilisateurService.ajoutrole(new Role(null,ADMIN));

			/*utilisateurService.creer(new Utilisateurs(null,"fatou","coll","fc@gmail.com",
					"fcusername","fcpassword",Genre.Feminin,"00223",new HashSet<>() ));
*/
		};
	}

}
