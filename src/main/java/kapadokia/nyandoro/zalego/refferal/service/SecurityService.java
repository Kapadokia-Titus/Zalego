package kapadokia.nyandoro.zalego.refferal.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
