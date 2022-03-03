package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Report.class)
public abstract class Report_ {

	public static volatile SingularAttribute<Report, Timestamp> dateTime;
	public static volatile SingularAttribute<Report, Parent> parentByParentUserId;
	//public static volatile SingularAttribute<Report, Integer> eventId;
	public static volatile SingularAttribute<Report, String> comments;
	public static volatile SingularAttribute<Report, Event> eventByEventId;
	//public static volatile SingularAttribute<Report, Integer> parentUserId;
	public static volatile SingularAttribute<Report, Integer> id;

}

