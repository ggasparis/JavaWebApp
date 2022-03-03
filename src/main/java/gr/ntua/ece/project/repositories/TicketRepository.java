package gr.ntua.ece.project.repositories;

import gr.ntua.ece.project.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>,JpaSpecificationExecutor<Ticket> {
    Ticket findById(int id);
    //Ticket findByEventId(int id);
    //Ticket findByParentByParentUserId(int id);
}
