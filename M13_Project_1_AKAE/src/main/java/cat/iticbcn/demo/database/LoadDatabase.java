package cat.iticbcn.demo.database;

import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.controllers.*;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

import java.util.List;


import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Configuration;

//@Configuration
public class LoadDatabase {

	/*
	 * private static final Logger log =
	 * LoggerFactory.getLogger(LoadDatabase.class);
	 * 
	 * Company empresa1 = new Company("Empresa 1", 54,190,"pepe",
	 * "C/ Sanchez Avilo", 978277378,"empresa1@gmail.com", "financiera", new
	 * ArrayList<>());
	 * 
	 * @Bean CommandLineRunner initDatabase(CompanyRepository repository) { return
	 * args -> { log.info("Preloading " + repository.save(new Company("Empresa 1",
	 * 54,190,"pepe", "C/ Sanchez Avilo", 978277378,"empresa1@gmail.com",
	 * "financiera", new ArrayList<>()))); log.info("Preloading " +
	 * repository.save(new Company("Empresa 2", 54,190,"pepe", "C/ Sanchez Avilo",
	 * 978277378,"empresa1@gmail.com", "financiera", new ArrayList<>()))); }; }
	 */

}