package cat.iticbcn.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.Exception.*;

@RestController
public class CompanyController {

  private final CompanyRepository repository;

  
  public CompanyController(CompanyRepository repository) {
	this.repository = repository;
}

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/companies")
  List<Company> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/companies")
  Company newCompany(@RequestBody Company newCompany) {
    return repository.save(newCompany);
  }

 
  
  // Single item
  
  @GetMapping("/companies/{id}")
  Optional<Company> one(@PathVariable Long id) {
    
    return repository.findById(id);
  }
  
  
  @DeleteMapping("/companies/{id}")
  void deleteCompany(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
