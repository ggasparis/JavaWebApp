package gr.ntua.ece.project.repositories;


import gr.ntua.ece.project.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParentRepository extends JpaRepository<Parent, Long> {

}