package cat.iticbcn.demo.service;

import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserStudentServiceTest {

    @Autowired
    private UserStudentService userStudentService;

    @Test
    public void testSaveUser() {
        UserRegisterDTO userDTO = new UserRegisterDTO("testuser", "testemail@test.com", "password", "password");
        UserStudent savedUser = userStudentService.save(userDTO);
        Optional<UserStudent> opUserStudent = userStudentService.findByUsername("testuser");
        assertNotNull(savedUser);
        assertNotNull(opUserStudent);
    }
}
