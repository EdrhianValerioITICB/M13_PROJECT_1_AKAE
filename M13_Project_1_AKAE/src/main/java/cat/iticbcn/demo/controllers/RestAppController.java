package cat.iticbcn.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.OfferNotFoundException;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;

@RestController
public class RestAppController {

	@Autowired
	private final OfferRepository offerRepository;
	
	@Autowired
	private final CompanyRepository companyRepository;

	public RestAppController(OfferRepository offerRepository, CompanyRepository companyRepository) {
		this.offerRepository = offerRepository;
		this.companyRepository = companyRepository;
	}
	
	//COMPANY METHODS----------------------------------------------
	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/companies")
	  List<Company> allCompanies() {
	    return companyRepository.findAll();
	  }
	  // end::get-aggregate-root[]

	  @PostMapping("/companies")
	  Company newCompany(@RequestBody Company newCompany) {
	    return companyRepository.save(newCompany);
	  }
	  

	  // Single item
	  
	  @GetMapping("/companies/{id}")
	  Company oneCompany(@PathVariable Long id) {
	    
		  return companyRepository.findById(id)
			      .orElseThrow(() -> new CompanyNotFoundException(id));
	  }
	  
	  @PutMapping("/companies/{id}")
	  Company replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) {
	    
	    return companyRepository.findById(id)
	      .map(company -> {
	        company.setName(newCompany.getName());
	        company.setEmployees(newCompany.getEmployees());
	        company.setSocialSecurityNumber(newCompany.getSocialSecurityNumber());
	        company.setOwner(newCompany.getOwner());
	        company.setAddress(newCompany.getAddress());
	        company.setPhoneNumber(newCompany.getPhoneNumber());
	        company.setEmail(newCompany.getEmail());
	        company.setType(newCompany.getType());
	        return companyRepository.save(company);
	      })
	      .orElseGet(() -> {
	        newCompany.setId(id);
	        return companyRepository.save(newCompany);
	      });
	  }

	  @DeleteMapping("/companies/{id}")
	  void deleteCompany(@PathVariable Long id) {
	    companyRepository.deleteById(id);
	  }
	  
	//OFFER METHODS----------------------------------------------
	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/offers")
	List<Offer> allOffers() {
		return offerRepository.findAll();
	}
	// end::get-aggregate-root[]

	@GetMapping(value="companies/{id}/offers")
	List<Offer> getOffer(@PathVariable("id") Long id){
		Optional<Company> company = companyRepository.findById(id);
		List<Offer> offers = (List<Offer>) company.get().getOffers();
		return offers;
	}
		
	@PostMapping(value="companies/{id}/offers")
	public ResponseEntity<Offer> createOfferCompany(@RequestBody Offer offer, @PathVariable("id") Long id){
		Optional<Company> company = companyRepository.findById(id);
		offer.setCompany(company.get());
		Offer newof = offerRepository.save(offer);
		return ResponseEntity.ok(newof);
	}

	// Single item

	@GetMapping("/offers/{id}")
	Offer oneOffer(@PathVariable Long id) {
		return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
	}

	@PutMapping("/offers/{id}")
	Offer replaceOffer(@RequestBody Offer newOffer, @PathVariable Long id) {

		return offerRepository.findById(id).map(offer -> {
			offer.setTitle(newOffer.getTitle());
			offer.setDescription(newOffer.getDescription());
			return offerRepository.save(offer);
		}).orElseGet(() -> {
			newOffer.setId(id);
			return offerRepository.save(newOffer);
		});
	}

	@DeleteMapping("companies/{idCo}/offers/{idOf}")
	@Transactional
	void deleteOffer(@PathVariable Long idCo, @PathVariable Long idOf) {
		Optional<Company> company = companyRepository.findById(idCo);
		Optional<Offer> offer = offerRepository.findById(idOf);
		company.get().getOffers().remove(offer.get());
		offerRepository.deleteById(idOf);
	}
	
}
