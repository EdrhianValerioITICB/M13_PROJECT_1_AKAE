package cat.iticbcn.demo.repository;

import cat.iticbcn.demo.bean.UserStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStudentRepository extends JpaRepository<UserStudent, Long> {
    Optional<UserStudent> findByUsername(String username);
}