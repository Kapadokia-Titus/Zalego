package kapadokia.nyandoro.zalego.refferal.service;

import kapadokia.nyandoro.zalego.refferal.model.AdminUser;
import kapadokia.nyandoro.zalego.refferal.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AdminUser adminUser = new AdminUser();
    @Override
    public void save(AdminUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        adminUser.setUsername(user.getUsername());
        userRepository.save(user);
    }

    @Override
    public AdminUser findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public AdminUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AdminUser getAll() {
        return userRepository.findByUsername(adminUser.getUsername() );
    }
}