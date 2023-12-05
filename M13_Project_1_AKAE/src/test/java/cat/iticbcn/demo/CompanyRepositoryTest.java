package cat.iticbcn.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.core.support.RepositoryComposition;
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
		//Company c2=new Company("Empresa TECHNOStyle",332,"SS28372329", "Santana Rodriguez", "C/ Ramon Berenguer 33", "36897483947","TECHNO_style@gmail.com", "regional",
			//	new	ArrayList<>());
		entityManager.persist(c1);
		entityManager.flush();
	return c1;
	}

	@Test
	void findAllTest() {
		Company company=insertDemoCompany();
		List<Company> companies = repository.findAll();
		assertEquals(11,companies.size());
		assertEquals(company.getId(),companies.get(10).getId());
		
	}
	@Test
	void findByIdTest() {
		
		Company c1=new Company("Amazon",324,"shfnfkn8374834", "Capri Vazquez", "calle 2, barcelona", "67467348","gmailAmazon@gmail.com", "internacional",
				new	ArrayList<>());
		entityManager.persist(c1);
		entityManager.flush();
		Optional<Company> companies  = repository.findById(c1.getId());
	
		
		assertEquals(c1.getId(),companies.orElseGet(null).getId());		
		
	}
	
	@Test
	 void findByIdInTest(){
		 
		 Company c1=new Company("Amazon",324,"shfnfkn8374834", "Capri Vazquez", "calle 2, barcelona", "67467348","gmailAmazon@gmail.com", "internacional",
					new	ArrayList<>());
			entityManager.persist(c1);
			entityManager.flush();
			
			List<Company> companies=repository.findAll();
					
			List<Long> ids=companies.stream()
                    .map(Company::getId)
                    .collect(Collectors.toList());
			
			assertEquals(true, ids.contains(c1.getId()));
				
				 
	 }
	
	@Test
void findByEmailTest() {
	 Company c1=new Company("Amazon",324,"shfnfkn8374834", "Capri Vazquez", "calle 2, barcelona", "67467348","gmailAmazon@gmail.com", "internacional",
				new	ArrayList<>());
		entityManager.persist(c1);
		entityManager.flush();
	
	 List<Company> companies=repository.findAll();
	 
	 
	List<String> emails=companies.stream().map(Company::getEmail).collect(Collectors.toList());
	assertEquals(true,emails.contains(c1.getEmail()));
	
}
	
	
	@Test
	
	  void findByEmployeesInTest() {
		
		Company c1=new Company("compañia extra",324,"shfnfkn8374834", "alvaro", "calle uno, barcelona", "732738392","gmailC1@gmail.com", "cosmetica",
				new	ArrayList<>());
	
		entityManager.persist(c1);
		entityManager.flush();
		
		 List<Company> companies=repository.findAll();
		
		List<Integer> employees =  companies.stream().map(Company::getEmployees).collect(Collectors.toList());
		
		
		
	assertEquals(true, employees.contains(c1.getEmployees()));
		
	}
	
	
	@Test 
	void findCompaniesBySocialSecurityNumberOrderByNameDescTest() {
		
		

		 List<Company> companies=repository.findAll();
		
		List<String> SS =  companies.stream().map(Company::getSocialSecurityNumber).collect(Collectors.toList());
		
		
		
		
	}
	
	@Test 
	 void findBySocialSecurityNumberTest() {
		
		Company c1=new Company("compañia extra",324,"shfnfkn8374834", "alvaro", "calle uno, barcelona", "732738392","gmailC1@gmail.com", "cosmetica",
				new	ArrayList<>());
	
		entityManager.persist(c1);
		entityManager.flush();
		 boolean companies=repository.findAll().contains(c1.getSocialSecurityNumber());
			
			
			if(companies) {
				System.out.println("Se ha encontrado el SS");
			}else {
				System.out.println("No Se ha encontrado el SS");
			}
	
	}
	
	
	@Test

    void findByNameTest() {
		
		Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
				new	ArrayList<>());
	
		entityManager.persist(company);
		entityManager.flush();
		
		Company compañiaBuscada = repository.findByName(company.getName());
		
		assertEquals(company.getName(),compañiaBuscada.getName());
		
	}
	
	@Test
	  // Finding companies based on the number of employees
	void findByEmployeesGreaterThanTest() {
		Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
				new	ArrayList<>());
	
		entityManager.persist(company);
		entityManager.flush();
		 List<Company> companies = repository.findByEmployeesGreaterThan(50);

		    assertTrue(companies.contains(company));
		
		
		
		
	}
	
	
	
	@Test 
	void findByOwnerTest() {
		
		Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
				new	ArrayList<>());
	
		entityManager.persist(company);
		entityManager.flush();
		
		List<Company> companies = repository.findByOwner(company.getOwner());
	
		
		assertEquals(true,companies.contains(company));
		
		
	}
	
	
	@Test 
	  void findByNameContainingTest(){
		
		
			Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
					new	ArrayList<>());
		
			entityManager.persist(company);
			entityManager.flush();
			
			 List<Company> companies = repository.findByNameContaining("%" + company.getName().substring(1,4) + "%");
			 assertEquals(true, companies.contains(company));
			
		}
		
	@Test 
	
	  // Finding companies by name using 'LIKE' query
	
    void findByNameLikeTest(){
		
		
		Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
				new	ArrayList<>());
	
		entityManager.persist(company);
		entityManager.flush();
		
		 List<Company> companies = repository.findByNameContaining("%" + company.getName() + "%");
		 assertEquals(true, companies.contains(company));
		
	}
	
	
	
	
	@Test 
    // Finding companies by number of employees between a range
    void findByEmployeesBetweenTest() {

		Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
				new	ArrayList<>());
	
		entityManager.persist(company);
		entityManager.flush();
		
		
		List<Company> companies = repository.findByEmployeesBetween(30,50);
			
		
		assertTrue(companies.contains(company));
		//si da false esque esta bien, porque company tiene 57 empleados 
		
		
	}
	
	
	@Test 
    // Finding companies by type
   void  findByTypeTest() {
		
		
		Company comp=new Company("Hotel Martin Rivas",1023,"SS_3u23ui43i4", "Sergio Ramos", "C/ de la Mercè, 34", "8392839038","hotelSergioRamos@gmail.com", "Hotel",
				new	ArrayList<>());
	
		entityManager.persist(comp);
		entityManager.flush();
		
		List<Company> companies = repository.findByType(comp.getType());
		
		assertTrue(companies.contains(comp));
		
		
		
	}
	
	@Test
	  void findByEmployeesLessThanTest() {
		  
		  
		  Company compañiaBuscada=new Company("Call Center",49,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
					new	ArrayList<>());
		
			entityManager.persist(compañiaBuscada);
			entityManager.flush();
			 List<Company> companies = repository.findByEmployeesLessThan(100);

			    assertTrue(companies.contains(compañiaBuscada));
			
		  
	  }
	
	@Test
	void findByTypeAndEmployeesBetweenTest() {
		
		  Company compañiaBuscada=new Company("Call Center",49,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
					new	ArrayList<>());
		
			entityManager.persist(compañiaBuscada);
			entityManager.flush();
			 List<Company> companies = repository.findByTypeAndEmployeesBetween(compañiaBuscada.getType(), 30, 50);
			 
			 assertTrue(companies.contains(compañiaBuscada))
			 ;
			 
		
	}
	
	
	
	@Test 
  void findByEmailOrPhoneNumberTest() {
		
		  Company c=new Company("Call Center",49,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
					new	ArrayList<>());
		
			entityManager.persist(c);
			entityManager.flush();
			
 List<Company> companies = repository.findByEmailOrPhoneNumber(c.getEmail(),c.getPhoneNumber());;
			 
 
 if(companies.contains(c)) {
	 System.out.println("Existe esa empresa y su id es " + c.getId());
 }else{
	 System.out.println("No existe");
	 
 }
			 
		
	}

	
@Test
	    void findByNameAndTypeTest() {
	    	
			Company company=new Company("Call Center",57,"SS_38739879873", "International systems", "calle Mataro,27, barcelona", "936283784","Call_center@gmail.com", "Pequeña",
					new	ArrayList<>());
		
			entityManager.persist(company);
			entityManager.flush();
			
			 List<Company> companies = repository.findByNameAndType(company.getName(),company.getType());
			 assertEquals(false, companies.isEmpty());
	    	
	    }



@Test 

void findCompaniesWithoutOffersTest() {
	
	
List<Company> companies = repository.findCompaniesWithoutOffers();

assertEquals(false, companies.isEmpty());)


	
}
	

}
