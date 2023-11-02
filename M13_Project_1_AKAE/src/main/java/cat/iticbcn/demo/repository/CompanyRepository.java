package cat.iticbcn.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.iticbcn.demo.bean.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

		//Collection<Company> findById(Long id);

}
