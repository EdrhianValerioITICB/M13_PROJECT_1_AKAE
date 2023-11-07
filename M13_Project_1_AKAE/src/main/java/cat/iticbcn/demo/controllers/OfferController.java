package cat.iticbcn.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OfferController {

	@Autowired
	private final OfferRepository repository;

	public OfferController(OfferRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/offers")
	List<Offer> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/offers")
	Offer newOffer(@RequestBody Offer newOffer) {
		return repository.save(newOffer);
	}

	// Single item

	@GetMapping("/offers/{id}")
	Offer one(@PathVariable Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new OfferNotFoundException(id));
	}

	@PutMapping("/offers/{id}")
	Offer replaceOffer(@RequestBody Offer newOffer, @PathVariable Long id) {

		return repository.findById(id)
			.map(offer -> {
			offer.setTitle(newOffer.getTitle());
			offer.setDescription(newOffer.getDescription());
			return repository.save(offer);
		}).orElseGet(() -> {
			newOffer.setId(id);
			return repository.save(newOffer);
		});
	}

	@DeleteMapping("/offers/{id}")
	void deleteOffer(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
