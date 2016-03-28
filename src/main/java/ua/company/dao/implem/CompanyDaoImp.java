package ua.company.dao.implem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.company.dao.CompanyDao;
import ua.company.entity.Company;

@Repository
public class CompanyDaoImp implements CompanyDao {
	@PersistenceContext(unitName = "Primary")
	private EntityManager entityManager;

	@Transactional
	public void addCompany(Company company) {
		entityManager.merge(company);
	}

	@Transactional
	public List<Company> showAllCompany() {
		return entityManager.createQuery("from Company", Company.class).getResultList();
	}

	@Transactional
	public Company showByID(Integer ID) {
		return entityManager.find(Company.class, ID);
	}

	@Transactional
	public void updateCompany(Company company) {
		entityManager.merge(company);
	}

	@Transactional
	public void deleteCompany(Integer companyID) {
		entityManager.createNamedQuery("Company.delete").setParameter("companyID", (Integer)companyID).executeUpdate();
	}
	
}
