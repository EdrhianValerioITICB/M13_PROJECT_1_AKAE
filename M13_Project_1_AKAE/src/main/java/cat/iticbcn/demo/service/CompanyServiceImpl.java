package cat.iticbcn.demo.service;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public Company save(Company newCompany) {
        return companyRepository.save(newCompany);
    }

    @Override
    public Company replaceCompany(Company newCompany, Long id) {
        return companyRepository.findById(id).map(company -> {
            company.setName(newCompany.getName());
            company.setEmployees(newCompany.getEmployees());
            company.setSocialSecurityNumber(newCompany.getSocialSecurityNumber());
            company.setOwner(newCompany.getOwner());
            company.setAddress(newCompany.getAddress());
            company.setPhoneNumber(newCompany.getPhoneNumber());
            company.setEmail(newCompany.getEmail());
            company.setType(newCompany.getType());
            return companyRepository.save(company);
        }).orElseGet(() -> {
            newCompany.setId(id);
            return companyRepository.save(newCompany);
        });
    }

}