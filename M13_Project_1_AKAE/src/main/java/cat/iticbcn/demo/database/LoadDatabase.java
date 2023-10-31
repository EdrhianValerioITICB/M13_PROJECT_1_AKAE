package cat.iticbcn.demo.database;

import cat.iticbcn.demo.repository.CompanyRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import cat.iticbcn.demo.bean.Company;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Configuration;

public class LoadDatabase {
	
	 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(CompanyRepository repository) {
			

	    return args -> {
	      log.info("Preloading " + 
	    repository.save(new Company("Empresa 1", 54,190,"pepe", "C/ Sanchez Avilo", 978277378,"empresa1@gmail.com", "financiera")));
	      
	    };
	  }
	

}
