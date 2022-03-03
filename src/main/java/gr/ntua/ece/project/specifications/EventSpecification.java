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

public class EventSpecification {
//isvalid( arguments) predicate for jpa query with specifications


    public static Specification<Event> isValid(SearchForm searchForm) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String crap= searchForm.getDescription().replaceAll("\\s+","");
        return new Specification<Event>() {
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                if (!(searchForm.getDescription().equals("")||searchForm.getDescription().replaceAll("\\s+","").equals(""))) {
                    if (searchForm.getCategory() == -1) {


                        return builder.and(builder.lessThanOrEqualTo(root.get(Event_.ageFrom), searchForm.getAge()),
                                builder.greaterThanOrEqualTo(root.get(Event_.ageTo), searchForm.getAge()),
                                //builder.equal(root.get(Event_.categoryId), searchForm.getCategory()),
                                builder.greaterThan(root.get(Event_.dateTime), timestamp),
                                builder.greaterThan(root.get(Event_.availableTickets), 0),
                                builder.greaterThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateFrom() + " 00:00:00.000")),
                                builder.lessThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateTo() + " 23:59:00.000")),
                                builder.equal(root.get(Event_.isActive), 1),
                                builder.or(builder.like(root.get(Event_.title), "%" + crap + "%"),
                                builder.like(root.get(Event_.tags), "%" + crap + "%"),
                                builder.like(root.get(Event_.tags), "%" + crap + "%")        )
                        );
                    } else {
                        return builder.and(builder.lessThanOrEqualTo(root.get(Event_.ageFrom), searchForm.getAge()),
                                builder.greaterThanOrEqualTo(root.get(Event_.ageTo), searchForm.getAge()),
                                builder.equal(root.get(Event_.categoryId), searchForm.getCategory()),
                                builder.greaterThan(root.get(Event_.dateTime), timestamp),
                                builder.greaterThan(root.get(Event_.availableTickets), 0),
                                builder.greaterThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateFrom() + " 00:00:00.000")),
                                builder.lessThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateTo() + " 23:59:00.000")),
                                builder.equal(root.get(Event_.isActive), 1),
                                builder.or(builder.like(root.get(Event_.title), "%" + crap + "%"),
                                builder.like(root.get(Event_.tags), "%" + crap + "%"),
                                builder.like(root.get(Event_.tags), "%" + crap + "%")        )
                        );
                    }

                } else {

                    if (searchForm.getCategory() == -1) {


                        return builder.and(builder.lessThanOrEqualTo(root.get(Event_.ageFrom), searchForm.getAge()),
                                builder.greaterThanOrEqualTo(root.get(Event_.ageTo), searchForm.getAge()),
                                //builder.equal(root.get(Event_.categoryId), searchForm.getCategory()),
                                builder.greaterThan(root.get(Event_.dateTime), timestamp),
                                builder.greaterThan(root.get(Event_.availableTickets), 0),
                                builder.greaterThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateFrom() + " 00:00:00.000")),
                                builder.lessThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateTo() + " 23:59:00.000")),
                                builder.equal(root.get(Event_.isActive), 1)
                        );
                    } else {
                        return builder.and(builder.lessThanOrEqualTo(root.get(Event_.ageFrom), searchForm.getAge()),
                                builder.greaterThanOrEqualTo(root.get(Event_.ageTo), searchForm.getAge()),
                                builder.equal(root.get(Event_.categoryId), searchForm.getCategory()),
                                builder.greaterThan(root.get(Event_.dateTime), timestamp),
                                builder.greaterThan(root.get(Event_.availableTickets), 0),
                                builder.greaterThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateFrom() + " 00:00:00.000")),
                                builder.lessThan(root.get(Event_.dateTime), Timestamp.valueOf(searchForm.getDateTo() + " 23:59:00.000")),
                                builder.equal(root.get(Event_.isActive), 1)
                        );
                    }

                }
            }
        };
    }

    public static Specification<Event> doneMonthly(Provider provider) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Timestamp sq = new Timestamp(date.getTime());
        return new Specification<Event>() {
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.and(
                        builder.equal(root.get(Event_.providerUserId),provider.getUserId()),
                        builder.greaterThan(root.get(Event_.dateTime),sq),
                        builder.lessThan(root.get(Event_.dateTime),timestamp)
                );
            }
        };
    }

    public static Specification<Event> monthlyAndBeyond(Provider provider) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Timestamp sq = new Timestamp(date.getTime());
        return new Specification<Event>() {
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.and(
                        builder.equal(root.get(Event_.providerUserId),provider.getUserId()),
                        builder.greaterThan(root.get(Event_.dateTime),sq)

                );
            }
        };
    }

}
