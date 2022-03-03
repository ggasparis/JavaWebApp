package gr.ntua.ece.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.project.entities.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findAll();
    Role findById(int id);
}