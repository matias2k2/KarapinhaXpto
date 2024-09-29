package tinario9954.gmail.com.KarapinhaApi.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.KarapinhaApi.Models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    //Users findByEmail(String email); // Se estiver autenticando com o email
    // Ou Users findByUsernames(String usernames); se for pelo username
    Users findByUsernames(String usernames);
    
}
