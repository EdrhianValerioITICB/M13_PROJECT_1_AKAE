package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.UserAdministrator;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.repository.UserStudentRepository;
import cat.iticbcn.demo.service.UserAdministratorService;
import cat.iticbcn.demo.service.UserStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AuthControllerTest {

    AuthController authController;

    @Mock
    UserAdministratorService userAdministratorService;

    @Mock
    UserStudentService userStudentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(userStudentService, userAdministratorService);
    }

    @Test
    void registerStudent() {
        UserRegisterDTO userDTO = new UserRegisterDTO("student", "student@gmail.com", "pass", "pass");
        UserStudent userStudent = authController.saveStudent(userDTO);

        verify(userStudentService, times(1)).save(userDTO);

    }

    @Test
    void registerAdministrator() {
        UserRegisterDTO userDTO = new UserRegisterDTO("admin", "admin@gmail.com", "pass", "pass");
        UserAdministrator userAdministrator = authController.saveAdministrator(userDTO);

        verify(userAdministratorService, times(1)).save(userDTO);

    }
}
