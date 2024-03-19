package bg.softuni.jsonexrcise.Repository;

import bg.softuni.jsonexrcise.data.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserById(int id);

}
