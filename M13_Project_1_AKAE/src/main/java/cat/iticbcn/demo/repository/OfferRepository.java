package cat.iticbcn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
	void deleteByCompany(Company co);

}
