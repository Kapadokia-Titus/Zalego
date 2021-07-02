package kapadokia.nyandoro.zalego.refferal.service;

import kapadokia.nyandoro.zalego.refferal.model.AdminUser;

import java.util.Optional;

public interface UserService {
    void save(AdminUser user);

    AdminUser findByUsername(String username);

    AdminUser findByEmail(String email);

   AdminUser getAll();
}
