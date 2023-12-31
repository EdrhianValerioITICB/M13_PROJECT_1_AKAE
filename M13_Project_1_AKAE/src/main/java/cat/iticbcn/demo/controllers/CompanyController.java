package cat.iticbcn.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.Exception.*;

import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.CompanyNotFoundAdvice;

import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;


@RestController
public class CompanyController {

	/*
	 * private final CompanyRepository repository;
	 * 
	 * public CompanyController(CompanyRepository repository) { this.repository =
	 * repository; }
	 * 
	 * //COMPANY METHODS // Aggregate root // tag::get-aggregate-root[]
	 * 
	 * @GetMapping("/companies") List<Company> all() { return repository.findAll();
	 * } // end::get-aggregate-root[]
	 * 
	 * @PostMapping("/companies") Company newCompany(@RequestBody Company
	 * newCompany) { return repository.save(newCompany); }
	 * 
	 * 
	 * // Single item
	 * 
	 * @GetMapping("/companies/{id}") Company one(@PathVariable Long id) {
	 * 
	 * return repository.findById(id) .orElseThrow(() -> new
	 * CompanyNotFoundException(id)); }
	 * 
	 * @PutMapping("/companies/{id}") Company replaceCompany(@RequestBody Company
	 * newCompany, @PathVariable Long id) {
	 * 
	 * return repository.findById(id) .map(company -> {
	 * company.setName(newCompany.getName());
	 * company.setEmployees(newCompany.getEmployees());
	 * company.setSocialSecurityNumber(newCompany.getSocialSecurityNumber());
	 * company.setOwner(newCompany.getOwner());
	 * company.setAddress(newCompany.getAddress());
	 * company.setPhoneNumber(newCompany.getPhoneNumber());
	 * company.setEmail(newCompany.getEmail());
	 * company.setType(newCompany.getType()); return repository.save(company); })
	 * .orElseGet(() -> { newCompany.setId(id); return repository.save(newCompany);
	 * }); }
	 * 
	 * @DeleteMapping("/companies/{id}") void deleteCompany(@PathVariable Long id) {
	 * repository.deleteById(id); }
	 */

  
}
