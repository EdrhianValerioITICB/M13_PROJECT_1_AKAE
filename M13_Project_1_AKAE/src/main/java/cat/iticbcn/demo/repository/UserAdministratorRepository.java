package cat.iticbcn.demo.repository;

import cat.iticbcn.demo.bean.UserAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAdministratorRepository extends JpaRepository<UserAdministrator, Long> {

    Optional<UserAdministrator> findByUsername(String username);
}
