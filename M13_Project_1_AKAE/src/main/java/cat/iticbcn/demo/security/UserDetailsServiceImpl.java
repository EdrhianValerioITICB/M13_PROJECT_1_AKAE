package cat.iticbcn.demo.security;

import cat.iticbcn.demo.bean.UserAdministrator;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.service.UserAdministratorService;
import cat.iticbcn.demo.service.UserStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
Permite que Spring Security sepa cómo extraer el usuario de base de datos
para realizar la autenticación
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserStudentService userStudentService;

    private final UserAdministratorService userAdministratorService;

    public UserDetailsServiceImpl(UserStudentService userStudentService, UserAdministratorService userAdministratorService) {
        this.userStudentService = userStudentService;
        this.userAdministratorService = userAdministratorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername {}", username);

        UserStudent student = this.userStudentService.findByUsername(username)
                .orElse(null);

        if (student != null) {
            return student;
        }

        UserAdministrator admin = this.userAdministratorService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
        return admin;

    }
}
