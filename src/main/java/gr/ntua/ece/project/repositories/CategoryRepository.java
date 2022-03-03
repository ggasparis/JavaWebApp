package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Category;
import gr.ntua.ece.project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category, Long>,JpaSpecificationExecutor<Category> {
    Category findByName(String name);
}
