package kapadokia.nyandoro.zalego.refferal.repository;

import kapadokia.nyandoro.zalego.refferal.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByName(String name);
}