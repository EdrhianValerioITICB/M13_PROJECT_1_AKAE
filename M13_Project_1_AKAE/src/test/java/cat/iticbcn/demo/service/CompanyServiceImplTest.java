package cat.iticbcn.demo.service;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class CompanyServiceImplTest {

    //System Under Test : SUT
    CompanyService companyService;

    //Dependencies
    @Mock
    CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyServiceImpl(companyRepository);
    }

    @Test
    void findAll() {

        //Given

        when(companyRepository.findAll()).thenReturn(List.of());
        //Then
        List<Company> companies = companyService.findAll();

        //JUnit checking
        assertNotNull(companies);
        assertEquals(0, companies.size());

        //Mockito verification
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void findByIdFound() {

        //Given

        when(companyRepository.findById(anyLong())).thenReturn(Optional.of
                (new Company(1L,"COMPANY1", 50, "A7748190752Z", "Jeff Bezos", "410 Terry Ave N, Seattle 98109, WA"
                        , "713 443 966", "clients@amazon.com", "Tech multinational", null)));
        //Then
        Optional<Company> companyOpt = companyService.findById(1L);

        //JUnit checking
        assertNotNull(companyOpt);
        assertTrue(companyOpt.isPresent());
        assertEquals("COMPANY1", companyOpt.get().getName());

        //Mockito verification
        verify(companyRepository, times(1)).findById(1L);

    }

    @Test
    void findByIdNotFound() {

        //Given

        //When
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Company> company = companyService.findById(1L);

        assertTrue(company.isEmpty());

        //Then
        verify(companyRepository, times(1)).findById(1L);

    }

    @Test
    void findCompaniesByName() {
        when(companyRepository.findByNameContaining("COMPANY1")).thenReturn(List.of(new Company(1L,"COMPANY1", 50, "A7748190752Z", "Jeff Bezos", "410 Terry Ave N, Seattle 98109, WA"
                , "713 443 966", "clients@amazon.com", "Tech multinational", null)));

        List<Company> companies = companyService.findCompaniesByName("COMPANY1");

        assertNotNull(companies);
        assertEquals(1, companies.size());
        assertEquals("COMPANY1", companies.get(0).getName());

        verify(companyRepository, times(1)).findByNameContaining("COMPANY1");
    }


    @Test
    void findCompaniesByType() {

        when(companyRepository.findByType("Tech multinational")).thenReturn(List.of(new Company(1L,"COMPANY1", 50, "A7748190752Z", "Jeff Bezos", "410 Terry Ave N, Seattle 98109, WA"
                , "713 443 966", "clients@amazon.com", "Tech multinational", null)));

        List<Company> companies = companyService.findCompaniesByType("Tech multinational");

        assertNotNull(companies);
        assertEquals(1, companies.size());
        assertEquals("Tech multinational", companies.get(0).getType());

        verify(companyRepository, times(1)).findByType("Tech multinational");

    }


    @Test
    void findCompaniesByEmployeesRange() {

        when(companyRepository.findByEmployeesBetween(20, 60)).thenReturn(List.of(new Company(1L,"COMPANY1", 50, "A7748190752Z", "Jeff Bezos", "410 Terry Ave N, Seattle 98109, WA"
                , "713 443 966", "clients@amazon.com", "Tech multinational", null)));

        List<Company> companies = companyService.findCompaniesByEmployeesRange(20, 60);

        assertNotNull(companies);
        assertEquals(1, companies.size());
        assertEquals(50, companies.get(0).getEmployees());

        verify(companyRepository, times(1)).findByEmployeesBetween(20, 60);

    }

    @Test
    void findCompanyByEmail() {
        // Given
        String emailToSearch = "clients@amazon.com";
        Company expectedCompany = new Company(1L,"COMPANY1", 50, "A7748190752Z", "Jeff Bezos", "410 Terry Ave N, Seattle 98109, WA",
                "713 443 966", "clients@amazon.com", "Tech multinational", null);

        when(companyRepository.findByEmail("clients@amazon.com")).thenReturn(Optional.of(expectedCompany));

        // When
        Optional<Company> company = companyService.findByEmail("clients@amazon.com");

        // Then
        assertTrue(company.isPresent());
        assertEquals(expectedCompany.getName(), company.get().getName());
        assertEquals(expectedCompany.getEmail(), company.get().getEmail());

        verify(companyRepository, times(1)).findByEmail("clients@amazon.com");
    }

    @Test
    void deleteCompanyById() {
        // Given
        long companyIdToDelete = 1L;

        // When
        companyService.deleteById(1L);

        // Then
        verify(companyRepository, times(1)).deleteById(1L);
    }

}