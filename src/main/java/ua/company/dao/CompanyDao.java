package ua.company.dao;

import java.util.List;

import ua.company.entity.Company;



public interface CompanyDao {
	void addCompany(Company company);

	List<Company> showAllCompany();

	Company showByID(Integer ID);

	void updateCompany(Company company);

	void deleteCompany(Integer ID);
	
}
