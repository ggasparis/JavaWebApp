package gr.ntua.ece.project.specifications;

import gr.ntua.ece.project.entities.Provider;
import gr.ntua.ece.project.entities.Provider_;
import gr.ntua.ece.project.entities.User;

import gr.ntua.ece.project.entities.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {

    public static Specification<User> isParent() {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.equal(root.get(User_.roleId), 2);
            }
        };
    }

    public static Specification<User> isProvider() {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.equal(root.get(User_.roleId), 3);
            }
        };
    }

    public static Specification<User> isAdministrator() {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.equal(root.get(User_.roleId), 1);
            }
        };
    }

    public static Specification<Provider> isNotApproved() {
        return new Specification<Provider>() {
            public Predicate toPredicate(Root<Provider> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                return builder.equal(root.get(Provider_.isApproved), new Byte((byte)0));
            }
        };
    }
}