package tinario9954.gmail.com.KarapinhaApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.KarapinhaApi.Models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    Users findByEmail(String email);
}