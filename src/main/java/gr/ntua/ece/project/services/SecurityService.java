package gr.ntua.ece.project.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}