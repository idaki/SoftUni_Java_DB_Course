package bg.softuni.jsonexrcise.Repository;

import bg.softuni.jsonexrcise.data.entitites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
