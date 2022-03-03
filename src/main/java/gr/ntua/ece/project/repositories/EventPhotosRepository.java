package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Category;
import gr.ntua.ece.project.entities.EventPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventPhotosRepository extends JpaRepository<EventPhotos, Long>,JpaSpecificationExecutor<EventPhotos> {
    List<EventPhotos> findById(int id);
}
