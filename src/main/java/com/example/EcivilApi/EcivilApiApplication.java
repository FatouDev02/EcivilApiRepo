package com.example.EcivilApi;

import com.example.EcivilApi.controller.ActenController;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.repository.Rolerepository;
import com.example.EcivilApi.services.Demandeservice;
import com.example.EcivilApi.services.StructureService;
import com.example.EcivilApi.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;

import static com.example.EcivilApi.models.ERole.*;

@SpringBootApplication
public class EcivilApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcivilApiApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(UtilisateurService utilisateurService,
							 StructureService structureService,
							 Rolerepository rolerepository,
							 Demandeservice demandeservice){
		return args -> {
			//crée des roles des linitialisation de lapp
			if(rolerepository.findByName(USER)==null
					&& rolerepository.findByName(Agent)==null
					&& rolerepository.findByName(ADMIN)==null ){
				utilisateurService.ajoutrole(new Role(null,USER));
				utilisateurService.ajoutrole(new Role(null,Agent));
				utilisateurService.ajoutrole(new Role(null,ADMIN));
			}



			if (utilisateurService.getByEmail("fc@gmail.com")==null)
			{
				utilisateurService.creer(new Utilisateurs(null,"fatou","coll","fc@gmail.com",
						"passss","fcusee","Feminin","000","lieu",new HashSet<>() ));
			/*utilisateurService.creer(new Utilisateurs(null,"fc@gmail.com",
					"fcpassword","fcusername","00223",new HashSet<>() ));*/
			}
			if (structureService.getbytype("Mairies")==null &&structureService.getbytype("Mairies")==null
					&&structureService.getbytype("Mairies")==null)
			{
				structureService.creertype(new Typestructure(null,"Mairies","Les  Mairies  sont  les structures définis pour \n" +
						" vos demandes de déclarations d’acte de naissance\n" +
						"d’acte de mariage et d’acte de décès\n" +
						"Vous pouvez géolocaliser les Mairies\n"));
				structureService.creertype(new Typestructure(null,"Tribunaux","Les  Tribunaux  sont  les structures définis pour \n" +
						" vos demandes de déclarations de Casier Judiciaire \n" +
						"Vous pouvez géolocaliser les Mairies\n"));
				structureService.creertype(new Typestructure(null,"Commissariats","Les  Commissariats  sont  les structures définis \n" +
						"pour vos demandes de déclarations de certificat\n" +
						"de nationnalité,et de résidence\n" +
						"Vous pouvez géolocaliser les Commissariats\n"));

			}
			if (demandeservice.findbytype("Residence")== null &&
					demandeservice.findbytype("Acten")== null &&
					demandeservice.findbytype("Actem")== null &&
					demandeservice.findbytype("Acted")== null &&
					demandeservice.findbytype("Casier")== null &&
					demandeservice.findbytype("Nationnalite")== null ){
				demandeservice.creerdemande(new Demande(null,"Acten","Acte de Naissance",new Date()),1L);
				demandeservice.creerdemande(new Demande(null,"Actem","Acte de Mariage",new Date()),1L);
				demandeservice.creerdemande(new Demande(null,"Acted","Acte de décès",new Date()),1L);
				demandeservice.creerdemande(new Demande(null,"Casier","Casier Judiciaire",new Date()),2L);
				demandeservice.creerdemande(new Demande(null,"Nationnalite","Certificat de Nationnalité",new Date()),2L);
				demandeservice.creerdemande(new Demande(null,"Residence","Certificat de résidence",new Date()),3L);

			}

		};
	}


}
