package cat.iticbcn.demo.controllers;

import java.util.List;
import java.util.Optional;
import cat.iticbcn.demo.service.CompanyService;
import cat.iticbcn.demo.service.OfferService;
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

	private final OfferService offerService;

	public RestAppController(OfferRepository offerRepository, CompanyRepository companyRepository , CompanyService companyService, OfferService offerService) {
		this.offerRepository = offerRepository;
		this.companyRepository = companyRepository;
		this.companyService = companyService;
		this.offerService = offerService;
	}

	// COMPANY METHODS----------------------------------------------

	//GET ALL COMPANIES
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved companies", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={})
	})
	@Operation(summary = "Find all Companies", description = "Retieves all Companies from database")
	@GetMapping("/companies")
	List<Company> allCompanies() {
		return companyService.findAll();
	}


	//CREATE COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Invalid company", content ={})
	})
	@Operation(summary = "Add a Company", description = "Adds a Company in the database")
	@PostMapping("/companies")
	Company newCompany(@RequestBody Company newCompany) {
		return companyService.save(newCompany);
	}

	//GET ONE COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved companies", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={})
	})
	@Operation(summary = "Find a Company", description = "Find a Company by it's id")
	@GetMapping("/companies/{id}")
	Company oneCompany(@PathVariable Long id) {
		return companyService.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
	}

	//REPLACE COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Modified Company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={})
	})

	@Operation(summary = "Modify a Company", description = "Modifies a Company from the database")
	@PutMapping("/companies/{id}")
	Company replaceCompany(@RequestBody Company newCompany, @PathVariable Long id) {
		return companyService.replaceCompany(newCompany, id);
	}

	//DELETE COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Company.class)))}),
			@ApiResponse(responseCode = "404", description = "Company not found", content ={})
	})
	@Operation(summary = "Delete a Company", description = "Deletes a Company by it's id")
	@DeleteMapping("/companies/{id}")
	void deleteCompany(@PathVariable Long id) {
		companyService.deleteById(id);
		Optional<Company> company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id)));;
		companyRepository.deleteById(id);
	}

	// OFFER METHODS----------------------------------------------

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Find all Offers", description = "Retieves all Offers from the database")
	@GetMapping("/offers")
	List<Offer> allOffers() {
		return offerService.getAllOffers();
	}

	//GET ONE OFFER
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Find an Offer", description = "Retieves an Offer from the database")
	@GetMapping("/offers/{id}")
	Offer oneOffer(@PathVariable Long id) {
		return offerService.getOneOffer(id);
	}

	//GET OFFER BY COMPANY ID
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers from company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Find all Offers of a Company", description = "Retrieves all Offers of a Company by it's id")
	@GetMapping(value = "companies/{id}/offers")
	List<Offer> getOffer(@PathVariable("id") Long id) {
		return offerService.getOffersByCompanyId(id);
	}

	//GET OFFER BY COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieved Offers of the company", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Find and Offer", description = "Retrieves an Offer by it's id and from a specific Company by it's id")
	@GetMapping(value = "companies/{idCo}/offers/{idOf}")
	Optional<Offer> getOfferByCompany(@PathVariable("idCo") Long idCo, @PathVariable("idOf") Long idOf){
		return offerService.getOfferByCompany(idCo, idOf);
	}

	//CREATE OFFER COMPANY
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Created Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Create offer", description = "Adds a new Offer to the database")
	@PostMapping(value = "companies/{id}/offers")
	public ResponseEntity<Offer> createOfferCompany(@RequestBody Offer offer, @PathVariable("id") Long id) {
		return offerService.createOfferForCompany(offer, id);
	}

	//REPLACE OFFER
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Modified Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Modify Offer", description = "Modifies an Offer by it's id")
	@PutMapping("/offers/{id}")
	Offer replaceOffer(@RequestBody Offer newOffer, @PathVariable Long id) {
		return offerService.updateOffer(id, newOffer);
	}

	//DELETE OFFER
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted Offer", content ={
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = Offer.class)))}),
			@ApiResponse(responseCode = "404", description = "Offer not found", content ={})
	})
	@Operation(summary = "Delete Offer", description = "Deletes an Offer from the database")
	@DeleteMapping("companies/{idCo}/offers/{idOf}")
	@Transactional
	public void deleteOffer(@PathVariable Long idCo, @PathVariable Long idOf) {
        offerService.deleteOffer(idCo, idOf);
    }
}
