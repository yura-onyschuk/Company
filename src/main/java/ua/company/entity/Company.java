package ua.company.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
		@NamedQuery(name = "Company.delete", query = "delete from Company a where a.companyID = :companyID") })
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer companyID;
	@Column(unique = true)
	private String companyName;
	@Column
	private Integer estimatedAnnualEarnings;
	@Column
	private Integer allEarnings;
	@Column
	private Integer parentID;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Company parent;
	public Company() {
		super();
	}

	public Company(String companyName, Integer estimatedAnnualEarnings, Integer parentID, Company parent) {
		super();
		this.companyName = companyName;
		this.estimatedAnnualEarnings = estimatedAnnualEarnings;
		this.parentID = parentID;
		this.parent = parent;
	}

	public Company(String companyName, Integer estimatedAnnualEarnings, Integer allEarnings, Integer parentID,
			Company parent) {
		super();
		this.companyName = companyName;
		this.estimatedAnnualEarnings = estimatedAnnualEarnings;
		this.allEarnings = allEarnings;
		this.parentID = parentID;
		this.parent = parent;
	}

	public Company(String companyName, Integer estimatedAnnualEarnings) {
		super();
		this.companyName = companyName;
		this.estimatedAnnualEarnings = estimatedAnnualEarnings;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getAllEarnings() {
		return allEarnings;
	}

	public void setAllEarnings(Integer allEarnings) {
		this.allEarnings = allEarnings;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEstimatedAnnualEarnings() {
		return estimatedAnnualEarnings;
	}

	public void setEstimatedAnnualEarnings(Integer estimatedAnnualEarnings) {
		this.estimatedAnnualEarnings = estimatedAnnualEarnings;
	}


	public Company getParent() {
		return parent;
	}

	public void setParent(Company parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allEarnings == null) ? 0 : allEarnings.hashCode());
		result = prime * result + ((companyID == null) ? 0 : companyID.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((estimatedAnnualEarnings == null) ? 0 : estimatedAnnualEarnings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (allEarnings == null) {
			if (other.allEarnings != null)
				return false;
		} else if (!allEarnings.equals(other.allEarnings))
			return false;
		if (companyID == null) {
			if (other.companyID != null)
				return false;
		} else if (!companyID.equals(other.companyID))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (estimatedAnnualEarnings == null) {
			if (other.estimatedAnnualEarnings != null)
				return false;
		} else if (!estimatedAnnualEarnings.equals(other.estimatedAnnualEarnings))
			return false;
		return true;
	}


}
