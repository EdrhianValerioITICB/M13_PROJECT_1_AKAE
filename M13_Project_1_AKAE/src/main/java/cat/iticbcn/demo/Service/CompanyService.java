package cat.iticbcn.demo.Service;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();

    Optional<Company> findById(Long id);

    List<Company> findCompaniesByName(String name);

    Optional<Company> findByName(String name);

    List<Company> findCompaniesByType(String type);

    Optional<Company> findByEmail(String email);

    List<Company> findCompaniesByEmployeesRange(int minEmployees,int maxEmployees);

    List<Company> findCompaniesByEmail(String email);

    void deleteById(@PathVariable Long id);
}
