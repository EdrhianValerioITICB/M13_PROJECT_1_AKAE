package cat.iticbcn.demo;

import cat.iticbcn.demo.Exception.CompanyAndOfferNotConnectedException;
import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.OfferNotFoundException;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;
import cat.iticbcn.demo.service.OfferServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OfferServiceImplTest {

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        offerService = new OfferServiceImpl(offerRepository, companyRepository);
    }
/*
    @Test
    void findAll() {
        when(offerRepository.findAll()).thenReturn(List.of(new Offer(), new Offer()));

        List<Offer> offers = offerService.findAll();

        assertNotNull(offers);
        assertEquals(2, offers.size());

        verify(offerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(offerRepository.findById(any())).thenReturn(Optional.of(new Offer(1L, "Offer1", "Description", new Company())));

        Optional<Offer> offerOpt = offerService.findById(1L);

        assertNotNull(offerOpt);
        assertTrue(offerOpt.isPresent());
        assertEquals("Offer1", offerOpt.get().getTitle());

        verify(offerRepository, times(1)).findById(1L);
    }

    @Test
    void getOneOffer() {
        when(offerRepository.findById(any())).thenReturn(Optional.of(new Offer(1L, "Offer1", "Description", new Company())));

        Offer offer = offerService.getOneOffer(1L);

        assertNotNull(offer);
        assertEquals("Offer1", offer.getTitle());

        verify(offerRepository, times(1)).findById(1L);
    }

    @Test
    void getOffersByCompanyId() {
        when(companyRepository.findById(any())).thenReturn(Optional.of(new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of(new Offer()))));

        List<Offer> offers = offerService.getOffersByCompanyId(1L);

        assertNotNull(offers);
        assertEquals(1, offers.size());

        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void getOfferByCompany() {
        Company company = new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of());
        Offer offer = new Offer(1L, "Offer1", "Description", company);
        company = new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of(offer));

        when(companyRepository.findById(any())).thenReturn(Optional.of(company));
        when(offerRepository.findById(any())).thenReturn(Optional.of(new Offer(1L, "Offer1", "Description", company)));

        Optional<Offer> offerOpt = offerService.getOfferByCompany(1L, 1L);

        assertNotNull(offerOpt);
        assertTrue(offerOpt.isPresent());
        assertEquals("Offer1", offerOpt.get().getTitle());

        verify(companyRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).findById(1L);
    }

    @Test
    void createOfferForCompany() {
        Company company = new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of());
        when(companyRepository.findById(any())).thenReturn(Optional.of(company));
        when(offerRepository.save(any())).thenReturn(new Offer(1L, "Offer1", "Description", company));

        ResponseEntity<Offer> responseEntity = offerService.createOfferForCompany(new Offer(), 1L);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals("Offer1", responseEntity.getBody().getTitle());

        verify(companyRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).save(any());
    }

    @Test
    void updateOffer() {
        when(offerRepository.findById(any())).thenReturn(Optional.of(new Offer(1L, "Offer1", "Description", new Company())));
        when(offerRepository.save(any())).thenReturn(new Offer(1L, "UpdatedOffer", "UpdatedDescription", new Company()));

        Offer updatedOffer = offerService.updateOffer(1L, new Offer());

        assertNotNull(updatedOffer);
        assertEquals("UpdatedOffer", updatedOffer.getTitle());
        assertEquals("UpdatedDescription", updatedOffer.getDescription());

        verify(offerRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).save(any());
    }

    @Test
    void deleteOffer() {
        Company company = new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of());
        Offer offer = new Offer(1L, "Offer1", "Description", company);
        company = new Company(1L, "Company1", 100, "12345", "Owner1", "Address1", "123456789", "company1@example.com", "Type1", List.of(offer));

        when(companyRepository.findById(any())).thenReturn(Optional.of(company));
        when(offerRepository.findById(any())).thenReturn(Optional.of(new Offer(1L, "Offer1", "Description", company)));

        //Assertions.assertDoesNotThrow(() -> offerService.deleteOffer(1L, 1L));
        assertDoesNotThrow(() -> offerService.deleteOffer(1L, 1L));

        verify(companyRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).findById(1L);
        verify(offerRepository, times(1)).deleteById(1L);
    }


    */

}
