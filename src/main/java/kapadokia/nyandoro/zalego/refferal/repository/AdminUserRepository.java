package kapadokia.nyandoro.zalego.refferal.repository;

import kapadokia.nyandoro.zalego.refferal.model.AdminUser;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
    AdminUser findByUsername(String username);

    AdminUser findByEmail(String email);
}
