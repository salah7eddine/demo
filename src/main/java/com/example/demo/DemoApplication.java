package com.example.demo;

import com.example.demo.dao.ContactRepository;
import com.example.demo.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		DateFormat df =new SimpleDateFormat("dd/mm/yyyy");
		contactRepository.save( new Contact("Samy","Med Yassine",df.parse("04/08/1994"),"Samy@gmail.com",0637254462,"Samy.png"));
		contactRepository.save( new Contact("Bakasse","Khalid",df.parse("08/10/2001"),"Bakasse@gmail.com",0663543645,"Bakasse.png"));
		contactRepository.save( new Contact("Bejjaji","mehdi",df.parse("16/06/1994"),"Bejjaji@gmail.com",0637254462,"Bejjaji.png"));

		contactRepository.findAll().forEach(c->{
			System.out.println(c.getNom());
		});
	}
}
