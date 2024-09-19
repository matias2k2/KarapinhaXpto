package tinario9954.gmail.com.KarapinhaApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.KarapinhaApi.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
