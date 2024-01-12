package cat.iticbcn.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import cat.iticbcn.demo.repository.OfferRepository;

@DataJpaTest

public class OfferRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OfferRepository repoOff;
//	@Autowired
//	private Company company = new Company("Starbucks", 35, "u49304833o4", "Starbucks United", "carrer diagonal,33", "3892833402","starbucksDiag@gmail.com","pequeña", new ArrayList<>());
//	@Autowired
//	private CompanyRepository repoCo;


    @Test
        //@Sql("offers.sql")
    void findById() {

        Company c1 = new Company(null, "Amazon", 324, "shfnfkn8374834", "Capri Vazquez", "calle 2, barcelona", "67467348", "gmailAmazon@gmail.com", "internacional",
                new ArrayList<>());
        entityManager.persist(c1);
        entityManager.flush();

        Offer oferta = new Offer(null, "Programacion avanzada en Java de segundo", "Professor de programacion", c1);
        entityManager.persist(oferta);
        entityManager.flush();


        Optional<Offer> off = repoOff.findById(c1.getId());
        assertEquals(c1.getId(),
                off.get().getId());
    }


    @Test
    //@Sql("offers.sql")
    void findAllWithSql() {
        List<Offer> offers = repoOff.findAll();
        assertEquals(10, offers.size());
    }
}
