package cat.iticbcn.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;

import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.Exception.OfferNotFoundException;
import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.service.CompanyService;
import cat.iticbcn.demo.service.OfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

class RestAppControllerTest {

    // System under test: SUT
    RestAppController restAppController;

    @Mock
    OfferService offerService;

    @Mock
    CompanyService companyService;

    @Mock
    Model model;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks((this));
        restAppController = new RestAppController(companyService, offerService);
    }

 /*   @Test
    void findZeroCompanies() {
        List<Company> companies = restAppController.findAllCompanies();
        assertEquals(0, companies.size());
    }

    @Test
    void findOneCompanyException() {
        Exception e = assertThrows(CompanyNotFoundException.class, () -> restAppController.findOneCompany(1L));

        String expectedMessage = "Could not find company 1";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findZeroOffers(){
        List<Offer> offers = restAppController.findAllOffers();
        assertEquals(0, offers.size());
    }

    @Test
    void findOneOfferException(){
        Exception e = assertThrows(OfferNotFoundException.class, () -> restAppController.findOneOffer(1L));

        String expectedMessage = "Could not find offer 1";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

  */
}