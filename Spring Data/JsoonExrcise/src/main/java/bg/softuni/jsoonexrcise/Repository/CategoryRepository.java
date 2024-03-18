package bg.softuni.jsoonexrcise.Repository;

import bg.softuni.jsoonexrcise.data.entitites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {
}
