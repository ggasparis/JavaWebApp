package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long>,JpaSpecificationExecutor<Event> {
   Event findById(int id);
   Event findEventByTitle(String Title);
   @Modifying
   @Transactional
   @Query("UPDATE Event e SET e.availableTickets = e.availableTickets - :tickets WHERE e.id = :eventId AND e.availableTickets >= :tickets")
   int updateAvailableTickets(@Param("eventId") int eventId, @Param("tickets") int tickets);
}

