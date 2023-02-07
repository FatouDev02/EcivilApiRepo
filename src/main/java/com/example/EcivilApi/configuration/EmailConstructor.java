package com.example.EcivilApi.configuration;

import com.example.EcivilApi.models.Agents;
import com.example.EcivilApi.models.Structure;
import com.example.EcivilApi.models.Utilisateurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class EmailConstructor {

	@Autowired
	private Environment env;

	@Autowired
	private TemplateEngine templateEngine;

	public MimeMessagePreparator constructNewUserEmail(Utilisateurs utilisateur) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		String text = templateEngine.process("userlink.html", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Bienvenu sur E-Sebenw");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};

		return messagePreparator;
	}
	public MimeMessagePreparator constructagent(Utilisateurs utilisateur) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		String text = templateEngine.process("validelien.html", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Bienvenu sur E-Sebenw");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};

		return messagePreparator;
	}
	public MimeMessagePreparator valideagent(Agents utilisateur, Structure structure) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		context.setVariable("structure", structure);


		String text = templateEngine.process("valideagent.html", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Bienvenu sur E-Sebenw");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};

		return messagePreparator;
	}

	public MimeMessagePreparator constructResetPasswordEmail(Utilisateurs utilisateur, String password) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		context.setVariable("password", password);
		String text = templateEngine.process("resetPasswordEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Nouveau mot de passe - E-Sebenw");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}

	public MimeMessagePreparator constructUpdateUserProfileEmail(Utilisateurs utilisateur) {
		Context context = new Context();
		context.setVariable("utilisateur", utilisateur);
		String text = templateEngine.process("updateUserProfileEmailTemplate", context);
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setPriority(1);
				email.setTo(utilisateur.getEmail());
				email.setSubject("Profile Modifié - ODC interview");
				email.setText(text, true);
				email.setFrom(new InternetAddress(env.getProperty("support.email")));
			}
		};
		return messagePreparator;
	}
}
