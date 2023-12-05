package cat.iticbcn.demo.service;

import cat.iticbcn.demo.bean.Offer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    List<Offer> findAll();
    Optional<Offer> findById(Long id);

    Offer getOneOffer(Long id);

    List<Offer> getOffersByCompanyId(Long id);

    Optional<Offer> getOfferByCompany(Long idCo, Long idOf);

    ResponseEntity<Offer> createOfferForCompany(Offer offer, Long id);

    Offer updateOffer(Long id, Offer newOffer);

    void deleteOffer(Long idCo, Long idOf);
}
