package gr.ntua.ece.project.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.project.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
    List<User> findAllByProviderById_IsApproved(int value);
}