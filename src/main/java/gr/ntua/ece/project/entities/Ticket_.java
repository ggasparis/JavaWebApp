package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Timestamp> dateTime;
	public static volatile SingularAttribute<Ticket, Parent> parentByParentUserId;
	public static volatile SingularAttribute<Ticket, Integer> eventId;
	public static volatile SingularAttribute<Ticket, Integer> quantity;
	public static volatile SingularAttribute<Ticket, Event> eventByEventId;
	public static volatile SingularAttribute<Ticket, Integer> parentUserId;
	public static volatile SingularAttribute<Ticket, Integer> id;

}

