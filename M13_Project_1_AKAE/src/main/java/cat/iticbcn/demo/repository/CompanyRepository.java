package cat.iticbcn.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.iticbcn.demo.M13Project1AkaeApplication.Company;

interface CompanysRepository extends JpaRepository<Company,String> {
	
	
	
		
		Collection<Company> findByNom(String nom);
		
		
		
		
	

}