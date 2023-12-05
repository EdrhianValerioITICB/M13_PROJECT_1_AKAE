package cat.iticbcn.demo.service;

import cat.iticbcn.demo.Exception.CompanyAndOfferNotConnectedException;
import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.OfferNotFoundException;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;

    public OfferServiceImpl(OfferRepository offerRepository, CompanyRepository companyRepository) {
        this.offerRepository = offerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerRepository.findById(id);
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer getOneOffer(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
    }

    public List<Offer> getOffersByCompanyId(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(Company::getOffers).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Optional<Offer> getOfferByCompany(Long companyId, Long offerId) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Offer> offer = offerRepository.findById(offerId);

        if (company.isPresent() && offer.isPresent() && company.get().getOffers().contains(offer.get())) {
            return offer;
        } else {
            throw new CompanyAndOfferNotConnectedException(companyId, offerId);
        }
    }

    public ResponseEntity<Offer> createOfferForCompany(Offer offer, Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            offer.setCompany(company.get());
            Offer newOffer = offerRepository.save(offer);
            return ResponseEntity.ok(newOffer);
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }

    public Offer updateOffer(Long id, Offer newOffer) {
        return offerRepository.findById(id)
                .map(offer -> {
                    offer.setTitle(newOffer.getTitle());
                    offer.setDescription(newOffer.getDescription());
                    return offerRepository.save(offer);
                })
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    @Transactional
    public void deleteOffer(Long companyId, Long offerId) {
        Optional<Company> company = companyRepository.findById(companyId);
        Optional<Offer> offer = offerRepository.findById(offerId);

        if (company.isPresent() && offer.isPresent() && company.get().getOffers().contains(offer.get())) {
            company.get().getOffers().remove(offer.get());
            offerRepository.deleteById(offerId);
        } else {
            throw new CompanyAndOfferNotConnectedException(companyId, offerId);
        }
    }
}
