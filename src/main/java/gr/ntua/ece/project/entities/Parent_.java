package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parent.class)
public abstract class Parent_ {

	public static volatile SingularAttribute<Parent, String> streetNumber;
	public static volatile SingularAttribute<Parent, String> postalCode;
	public static volatile SingularAttribute<Parent, BigDecimal> latitude;
	public static volatile SingularAttribute<Parent, User> userByUserId;
	public static volatile CollectionAttribute<Parent, Report> reportsByUserId;
	public static volatile SingularAttribute<Parent, BigDecimal> longtitude;
	public static volatile SingularAttribute<Parent, Integer> walletPoints;
	public static volatile SingularAttribute<Parent, Integer> userId;
	public static volatile SingularAttribute<Parent, String> phone;
	public static volatile SingularAttribute<Parent, String> surname;
	public static volatile SingularAttribute<Parent, String> street;
	public static volatile SingularAttribute<Parent, String> name;
	public static volatile CollectionAttribute<Parent, Ticket> ticketsByUserId;
	public static volatile SingularAttribute<Parent, String> photoLink;

}

