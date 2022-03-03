package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, Timestamp> dateTime;
	public static volatile CollectionAttribute<Event, Report> reportsById;
	public static volatile SingularAttribute<Event, BigDecimal> ticketPrice;
	public static volatile SingularAttribute<Event, String> streetNumber;
	public static volatile SingularAttribute<Event, Integer> availableTickets;
	public static volatile SingularAttribute<Event, BigDecimal> latitude;
	public static volatile SingularAttribute<Event, String> postalCode;
	public static volatile SingularAttribute<Event, BigDecimal> longtitude;
	public static volatile SingularAttribute<Event, String> description;
	public static volatile SingularAttribute<Event, Category> categoryByCategoryId;
	public static volatile CollectionAttribute<Event, Ticket> ticketsById;
	public static volatile SingularAttribute<Event, Provider> providerByProviderUserId;
	public static volatile CollectionAttribute<Event, EventPhotos> eventPhotosById;
	public static volatile SingularAttribute<Event, String> title;
	public static volatile SingularAttribute<Event, Integer> providerUserId;
	public static volatile SingularAttribute<Event, String> tags;
	public static volatile SingularAttribute<Event, String> street;
	public static volatile SingularAttribute<Event, Integer> ageFrom;
	public static volatile SingularAttribute<Event, Integer> id;
	public static volatile SingularAttribute<Event, Integer> ageTo;
	public static volatile SingularAttribute<Event, Integer> categoryId;
	public static volatile SingularAttribute<Event, Integer> isActive;

}

