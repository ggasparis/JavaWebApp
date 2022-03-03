package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventPhotos.class)
public abstract class EventPhotos_ {

	//public static volatile SingularAttribute<EventPhotos, Integer> eventId;
	public static volatile SingularAttribute<EventPhotos, Event> eventByEventId;
	public static volatile SingularAttribute<EventPhotos, String> link;
	public static volatile SingularAttribute<EventPhotos, Integer> id;

}

