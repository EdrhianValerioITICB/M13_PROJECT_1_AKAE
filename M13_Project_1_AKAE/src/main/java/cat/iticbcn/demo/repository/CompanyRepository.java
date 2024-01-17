package cat.iticbcn.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.iticbcn.demo.bean.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // trial / prueba
    //Collection<Company> findById(Long id);

    @Override
    Optional<Company> findById(Long id);

    @Override
    List<Company> findAll();

    List<Company> findByIdIn(List<Long> ids);

    Optional<Company> findByEmail(String email);

    List<Company> findByEmployeesIn(List<Integer> employees);

    List<Company> findCompaniesBySocialSecurityNumberOrderByNameDesc(String name);

    List<Company> findBySocialSecurityNumber(String SSN);

    Optional<Company> findByName(String name);

    // Finding companies based on the number of employees
    List<Company> findByEmployeesGreaterThan(int numberOfEmployees);

    // Finding companies by their owner's name
    List<Company> findByOwner(String ownerName);

    // Finding companies by name using 'LIKE' query
    List<Company> findByNameContaining(String name);

    // Finding companies by number of employees between a range
    List<Company> findByEmployeesBetween(int minEmployees, int maxEmployees);

    // Finding companies by type
    List<Company> findByType(String type);

    @Query("SELECT c FROM Company c WHERE c.name LIKE %:name%")
    List<Company> findByNameLike(String name);

    List<Company> findByEmployeesLessThan(int maxEmployees);

    List<Company> findByTypeAndEmployeesBetween(String type, int minEmployees, int maxEmployees);

    List<Company> findByEmailOrPhoneNumber(String email, String phoneNumber);

    @Query("SELECT c FROM Company c WHERE c.name = :name AND c.type = :type")
    List<Company> findByNameAndType(String name, String type);

    @Query("SELECT c FROM Company c WHERE c.offers IS EMPTY")
    List<Company> findCompaniesWithoutOffers();

}