package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.forms.UserForm;

import java.util.List;


public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
    Parent makeParent(UserForm userForm);
    Provider makeProvider(UserForm userForm);
    Parent updateParent(UserForm userForm);
    Provider updateProvider(UserForm userForm);
    User approveProvider(String username);
    User banUser(String username);
    User unbanUser(String username);
    List<User> findAllByRole(String role);

    void updateWalletParent(Parent parent,int amount);
    void updateProviderSub(Provider provider);
}