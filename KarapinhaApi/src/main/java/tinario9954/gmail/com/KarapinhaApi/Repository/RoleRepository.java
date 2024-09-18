package tinario9954.gmail.com.KarapinhaApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.KarapinhaApi.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
