package tinario9954.gmail.com.KarapinhaApi.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.KarapinhaApi.Models.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}
