package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.repository.UserStudentRepository;
import cat.iticbcn.demo.service.UserAdministratorService;
import cat.iticbcn.demo.service.UserStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthControllerTest {

    AuthController authController;

    @Mock
    UserAdministratorService userAdministratorService;

    @Mock
    UserStudentService userStudentService;

    @Mock
    UserStudentRepository userStudentRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(userStudentService, userAdministratorService);
    }

    @Test
    void registerStudent(){
        UserRegisterDTO userDTO = new UserRegisterDTO("student","student@gmail.com","pass","pass");
        UserStudent userStudent = userStudentService.save(userDTO);
        assertEquals(userStudent, userStudentRepository.findByUsername("student"));

    }
}
