package ua.company.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-17T16:24:32.031+0200")
@StaticMetamodel(Company.class)
public class Company {
	public static volatile SingularAttribute<Company, Integer> companyID;
	public static volatile SingularAttribute<Company, String> companyName;
	public static volatile SingularAttribute<Company, Integer> estimatedAnnualEarnings;
	public static volatile SingularAttribute<Company, Integer> parentID;
	public static volatile SingularAttribute<Company, Company> parent;
}
