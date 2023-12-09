package cat.iticbcn.demo.Service;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id).map(company -> {
            company.setName(company.getName().toUpperCase());
            return company;
        });
    }

    @Override
    public List<Company> findCompaniesByName(String name) {
        return this.companyRepository.findByNameContaining(name);
    }

    @Override
    public Optional<Company> findByName(String name) {
        return Optional.ofNullable(this.companyRepository.findByName(name));
    }

    @Override
    public List<Company> findCompaniesByType(String type) {
        return this.companyRepository.findByType(type);
    }

    @Override
    public Optional<Company> findByEmail(String email) {

        return this.companyRepository.findByEmail(email);
    }

    @Override
    public List<Company> findCompaniesByEmployeesRange(int minEmployees, int maxEmployees) {
        return this.companyRepository.findByEmployeesBetween(minEmployees, maxEmployees);
    }

    @Override
    public List<Company> findCompaniesByEmail(String email) {
        return this.companyRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}