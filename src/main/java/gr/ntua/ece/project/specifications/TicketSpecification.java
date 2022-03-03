package gr.ntua.ece.project.specifications;

import gr.ntua.ece.project.entities.*;
import gr.ntua.ece.project.forms.SearchForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

public class TicketSpecification {


    public static Specification<Ticket> isMonthly(Event event) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Timestamp sq = new Timestamp(date.getTime());
        return new Specification<Ticket>() {
            public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.and(builder.greaterThanOrEqualTo(root.get(Ticket_.dateTime),sq),
                        builder.equal(root.get(Ticket_.eventByEventId),event)
                );
            }
        };
    }
}