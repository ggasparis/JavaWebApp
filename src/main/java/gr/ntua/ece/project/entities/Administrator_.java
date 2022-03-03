package gr.ntua.ece.project.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Administrator.class)
public abstract class Administrator_ {

	public static volatile SingularAttribute<Administrator, Byte> canApprove;
	public static volatile SingularAttribute<Administrator, Byte> canAlterRoles;
	public static volatile SingularAttribute<Administrator, User> userByUserId;
	public static volatile SingularAttribute<Administrator, Byte> canAlterRights;
	public static volatile SingularAttribute<Administrator, Integer> userId;

}

