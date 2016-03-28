package ua.company.service.implem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.company.dao.CompanyDao;
import ua.company.entity.Company;
import ua.company.service.CompanyService;


@Service
public class CompanyServiceImp implements CompanyService{
	@Autowired
	private CompanyDao companyDao;
	@Transactional
	public void addCompany(Company company) {
		companyDao.addCompany(company);
		
	}
	@Transactional
	public List<Company> showAllCompany() {
		return companyDao.showAllCompany();
	}
	@Transactional
	public Company showByID(Integer ID) {
		return companyDao.showByID(ID);
	}
	@Transactional
	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
	}
	@Transactional
	public void deleteCompany(Integer ID) {
		companyDao.deleteCompany(ID);
	}

}

