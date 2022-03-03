package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Integer> roleId;
	public static volatile SingularAttribute<User, Administrator> administratorById;
	public static volatile SingularAttribute<User, Byte> isBanned;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Parent> parentById;
	public static volatile SingularAttribute<User, Provider> providerById;
	public static volatile SingularAttribute<User, String> username;

}

