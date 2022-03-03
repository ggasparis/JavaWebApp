package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provider.class)
public abstract class Provider_ {

	public static volatile CollectionAttribute<Provider, Event> eventsByUserId;
	public static volatile SingularAttribute<Provider, String> streetNumber;
	public static volatile SingularAttribute<Provider, String> companyName;
	public static volatile SingularAttribute<Provider, String> postalCode;
	public static volatile SingularAttribute<Provider, User> userByUserId;
	public static volatile SingularAttribute<Provider, String> description;
	public static volatile SingularAttribute<Provider, String> afm;
	public static volatile SingularAttribute<Provider, Integer> userId;
	public static volatile SingularAttribute<Provider, String> phone;
	public static volatile SingularAttribute<Provider, String> surname;
	public static volatile SingularAttribute<Provider, String> street;
	public static volatile SingularAttribute<Provider, String> name;
	public static volatile SingularAttribute<Provider, Date> subscriptionExpiryDate;
	public static volatile SingularAttribute<Provider, Date> registrationDate;
	public static volatile SingularAttribute<Provider, Byte> isApproved;
	public static volatile SingularAttribute<Provider, String> photoLink;

}

