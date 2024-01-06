package cat.iticbcn.demo.service;

import cat.iticbcn.demo.bean.UserAdministrator;
import cat.iticbcn.demo.bean.UserAuthority;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.repository.UserAdministratorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdministratorService {
    private final UserAdministratorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserAdministratorService(UserAdministratorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserAdministrator> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public UserAdministrator save(UserRegisterDTO userDTO) {
        UserAdministrator user = new UserAdministrator(
                null,
                userDTO.username(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ,UserAuthority.WRITE)
        );
        return this.repository.save(user);
    }
}
