package bg.softuni.jsonexrcise.Repository;

import bg.softuni.jsonexrcise.data.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
