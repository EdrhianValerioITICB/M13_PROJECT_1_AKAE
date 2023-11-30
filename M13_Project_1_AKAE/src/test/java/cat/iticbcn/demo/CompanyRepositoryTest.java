package cat.iticbcn.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;

@DataJpaTest

public class CompanyRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private CompanyRepository repository;
	
	@Test
	@Sql("import.sql")
	void findAllWithSql(){
		
		List<Company> companies = repository.findAll();
		assertEquals(11,companies.size())
;
		
	}
	
	
	
	private Company insertDemoCompany() {
		
		Company c1=new Company("compañia extra",324,"shfnfkn8374834", "alvaro", "calle uno, barcelona", "732738392","gmailC1@gmail.com", "cosmetica",
				new	ArrayList<>());
		Company c2=new Company("Empresa TECHNOStyle",332,"SS28372329", "Santana Rodriguez", "C/ Ramon Berenguer 33", "36897483947","TECHNO_style@gmail.com", "Regional",
				new	ArrayList<>());
		entityManager.persist(c1);
		entityManager.flush();
	return c1;
	}
	@Test
	void findAll() {
		Company company=insertDemoCompany();
		List<Company> companies = repository.findAll();
		assertEquals(1,companies.size());//comprueba q se ha insertado 1
		assertEquals(company.getId(),companies.get(0).getId());
		
	}
	@Test
	void findAllById() {
		
		Company c1=new Company("Amazon",324,"shfnfkn8374834", "Capri Vazquez", "calle 2, barcelona", "67467348","gmailAmazon@gmail.com", "internacional",
				new	ArrayList<>());
		entityManager.persist(c1);
		entityManager.flush();
		List<Company> companies  = repository.findAllById(c1.getId());
	

		assertEquals(1,companies.size());	
		
		
		
		assertEquals(c1.getId(),companies.get(0).getId());		
		
	}
	

}
