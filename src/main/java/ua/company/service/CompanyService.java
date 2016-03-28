package ua.company.service;

import java.util.List;

import ua.company.entity.Company;

public interface CompanyService {
	void addCompany(Company company);

	List<Company> showAllCompany();

	Company showByID(Integer ID);

	void updateCompany(Company company);

	void deleteCompany(Integer ID);

}
