package cat.iticbcn.demo.controllers;

import java.util.List;
import java.util.Optional;
import cat.iticbcn.demo.Service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import cat.iticbcn.demo.Exception.CompanyAndOfferNotConnectedException;
import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.OfferNotFoundException;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;

@RestController
@Tag(name = "Controller", description = "Companies and Offer API REST with CRUD Operations")
public class RestAppController {

	@Autowired
	private final OfferRepository offerRepository;

	@Autowired
	private final CompanyRepository companyRepository;

	private final CompanyService companyService;

	public RestAppController(OfferRepository offerRepository, CompanyRepository companyRepository , CompanyService companyService) {
		this.offerRepository = offerRepository;
		this.companyRepository = companyRepository;
		this.companyService = companyService;
	}
	// COMPANY METHODS----------------------------------------------
	// Aggregate root
	// tag::get-aggregate-root[]
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved companies", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find all Companies", description = "Retieves all Companies from database")
	@GetMapping("/companies")
	List<Company> allCompanies() {
		return (this.companyService.findAll());
	}
	// end::get-aggregate-root[]
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Invalid company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Add a Company", description = "Adds a Company in the database")
	@PostMapping("/companies")
	Company newCompany(@RequestBody Company newCompany) {
		return this.companyRepository.save(newCompany);
	}

	// Single item
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved companies", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find a Company", description = "Find a Company by it's id")
	@GetMapping("/companies/{id}")
	Company oneCompany(@PathVariable Long id) {
		return this.companyService.findById(id).orElseThrow(()
				-> new CompanyNotFoundException(id));
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Modified Company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Modify a Company", description = "Modifies a Company from the database")
	@PutMapping("/companies/{id}")
	Company replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) {

		return companyService.findById(id).map(company -> {
			company.setName(newCompany.getName());
			company.setEmployees(newCompany.getEmployees());
			company.setSocialSecurityNumber(newCompany.getSocialSecurityNumber());
			company.setOwner(newCompany.getOwner());
			company.setAddress(newCompany.getAddress());
			company.setPhoneNumber(newCompany.getPhoneNumber());
			company.setEmail(newCompany.getEmail());
			company.setType(newCompany.getType());
			return companyRepository.save(company);
		}).orElseGet(() -> {
			newCompany.setId(id);
			return companyRepository.save(newCompany);
		});
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Delete a Company", description = "Deletes a Company by it's id")
	@DeleteMapping("/companies/{id}")
	void deleteCompany(@PathVariable Long id) {
		companyService.deleteById(id);
		Optional<Company> company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id)));;
		companyRepository.deleteById(id);
	}

	// OFFER METHODS----------------------------------------------
	// Aggregate root
	// tag::get-aggregate-root[]
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find all Offers", description = "Retieves all Offers from the database")
	@GetMapping("/offers")
	List<Offer> allOffers() {
		return offerRepository.findAll();
	}
	// end::get-aggregate-root[]

	// Single item
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find an Offer", description = "Retieves an Offer from the database")
	@GetMapping("/offers/{id}")
	Offer oneOffer(@PathVariable Long id) {
		return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers from company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find all Offers of a Company", description = "Retrieves all Offers of a Company by it's id")
	@GetMapping(value = "companies/{id}/offers")
	List<Offer> getOffer(@PathVariable("id") Long id) {
		Optional<Company> company = companyRepository.findById(id);
		List<Offer> offers = company.get().getOffers();
		return offers;
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers of the company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company or Offer not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Find and Offer", description = "Retrieves an Offer by it's id and from a specific Company by it's id")
	@GetMapping(value = "companies/{idCo}/offers/{idOf}")
	Optional<Offer> getOfferByCompany(@PathVariable("idCo") Long idCo, @PathVariable("idOf") Long idOf){
		Optional<Company> company = Optional.ofNullable(companyRepository.findById(idCo).orElseThrow(() -> new CompanyNotFoundException(idCo)));
		Optional<Offer> offer = Optional.ofNullable(offerRepository.findById(idOf).orElseThrow(() -> new OfferNotFoundException(idOf)));
		if (!company.get().getOffers().contains(offer.get())) {
			throw new CompanyAndOfferNotConnectedException(idCo, idOf);
		}
		return offer;
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Create offer", description = "Adds a new Offer to the database")
	@PostMapping(value = "companies/{id}/offers")
	public ResponseEntity<Offer> createOfferCompany(@RequestBody Offer offer, @PathVariable("id") Long id) {
		try {
			Optional<Company> company = companyRepository.findById(id);
			offer.setCompany(company.get());
			Offer newof = offerRepository.save(offer);
			return ResponseEntity.ok(newof);
		} catch (Exception e) {
			throw new CompanyNotFoundException(id);
		}
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Modified Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Modify Offer", description = "Modifies an Offer by it's id")
	@PutMapping("/offers/{id}")
	Offer replaceOffer(@RequestBody Offer newOffer, @PathVariable Long id) {
		return offerRepository.findById(id).map(offer -> {
			offer.setTitle(newOffer.getTitle());
			offer.setDescription(newOffer.getDescription());
			return offerRepository.save(offer);
		}).orElseThrow(() -> new OfferNotFoundException(id));
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Can't delete Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))})
	})
	@Operation(summary = "Delete Offer", description = "Deletes an Offer from the database")
@DeleteMapping("companies/{idCo}/offers/{idOf}")
	@Transactional
	public void deleteOffer(@PathVariable Long idCo, @PathVariable Long idOf) {
		Optional<Company> company = Optional
				.of(companyRepository.findById(idCo).orElseThrow(() -> new CompanyNotFoundException(idCo)));
		Optional<Offer> offer = Optional
				.of(offerRepository.findById(idOf).orElseThrow(() -> new OfferNotFoundException(idOf)));
		if (!company.get().getOffers().contains(offer.get())) {
			throw new CompanyAndOfferNotConnectedException(idCo, idOf);
		}
		company.get().getOffers().remove(offer.get());
		offerRepository.deleteById(idOf);
	}
}
