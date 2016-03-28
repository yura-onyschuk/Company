package ua.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.entity.Company;
import ua.company.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/adding")
	public String addingCompany(Model model) {
		List<Company> list = companyService.showAllCompany();
		model.addAttribute("companies", list);
		return "company-new";
	}

	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
	public String addCompany(@RequestParam(value = "companyName") String companyName,
			@RequestParam(value = "estimatedAnnualEarnings") Integer estimatedAnnualEarnings,
			@RequestParam(value = "parent") Integer parent, Model model) {
		List<Company> list = companyService.showAllCompany();
		if (estimatedAnnualEarnings == null) {
			estimatedAnnualEarnings = 0;
		}
		for (Company company : list) {
			if (company.getCompanyName().equals(companyName)) {
				model.addAttribute("companies", list);
				return "company-new";
			}
		}
		Company company = new Company(companyName, estimatedAnnualEarnings);
		company.setAllEarnings(estimatedAnnualEarnings);
		if (parent!=0) {
			Company parentC = companyService.showByID(parent);
			company.setParent(parentC);
			company.setParentID(parent);
			parentC.setAllEarnings(parentC.getAllEarnings() + company.getAllEarnings());
			companyService.updateCompany(parentC);
			editingParants(parentC, company.getAllEarnings());
		}
		companyService.addCompany(company);
		return "redirect:/showAllCompany";
	}

	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	public String saveEdit(@RequestParam(value = "companyID") Integer companyID,
			@RequestParam(value = "companyName") String companyName,
			@RequestParam(value = "estimatedAnnualEarnings") Integer estimatedAnnualEarnings,
			@RequestParam(value = "parent") Integer parent) {
		Company editingC = companyService.showByID(companyID);
		Integer i = editingC.getEstimatedAnnualEarnings();
		editingC.setCompanyName(companyName);
		if (estimatedAnnualEarnings == null) {
			estimatedAnnualEarnings = 0;
		}
		editingC.setAllEarnings(editingC.getAllEarnings() - i + estimatedAnnualEarnings);
		editingC.setEstimatedAnnualEarnings(estimatedAnnualEarnings);
		if (parent.equals(editingC.getCompanyName()) | isChild(parent, companyID)) {
			companyService.updateCompany(editingC);
		} else if (parent==0|parent==editingC.getCompanyID()) {
			if (editingC.getParentID() != null) {
				Company old = companyService.showByID(editingC.getParentID());
				old.setAllEarnings(old.getAllEarnings() - editingC.getAllEarnings());
				editingOldParants(old, editingC.getAllEarnings());
				editingC.setParent(null);
				editingC.setParentID(null);
				companyService.updateCompany(editingC);
				companyService.updateCompany(old);
			} else {
				companyService.updateCompany(editingC);
			}
		} else if (parent==-1) {
			if (i != estimatedAnnualEarnings & editingC.getParentID() != null) {
				Company parentC = companyService.showByID(editingC.getParentID());
				parentC.setAllEarnings(parentC.getAllEarnings() - i + estimatedAnnualEarnings);
				editingParants(parentC, editingC.getAllEarnings());
				companyService.updateCompany(parentC);
				companyService.updateCompany(editingC);
			} else {
				companyService.updateCompany(editingC);
			}
		} else {
			Company parentC = companyService.showByID(parent);
			if (editingC.getParentID() == null) {
				editingC.setParent(parentC);
				editingC.setParentID(parentC.getCompanyID());
				parentC.setAllEarnings(parentC.getAllEarnings() + editingC.getAllEarnings());
				editingParants(parentC, editingC.getAllEarnings());
				companyService.updateCompany(editingC);
				companyService.updateCompany(parentC);
			} else {
				Company old = companyService.showByID(editingC.getParentID());
				old.setAllEarnings(old.getAllEarnings() - editingC.getAllEarnings());
				editingOldParants(old, editingC.getAllEarnings());
				editingC.setParent(parentC);
				editingC.setParentID(parentC.getCompanyID());
				parentC.setAllEarnings(parentC.getAllEarnings() + editingC.getAllEarnings());
				editingParants(parentC, editingC.getAllEarnings());
				companyService.updateCompany(editingC);
				companyService.updateCompany(parentC);
				companyService.updateCompany(old);
			}
		}
		return "redirect:/showAllCompany";
	}

	private boolean isChild(Integer parent, Integer companyID) {
		Company company = companyService.showByID(companyID);
		if (parent==0 | parent==-1) {
			return false;
		} else {
			Company parentCompany = companyService.showByID(parent);
			if (parentCompany.getParentID() != null) {
				if (parentCompany.getParentID() == company.getCompanyID()) {
					return true;
				}
				Company parent2 = companyService.showByID(parentCompany.getParentID());
				return isChild(parent2.getCompanyID(), companyID);
			}
		}

		return false;
	}

	private void editingOldParants(Company old, Integer allEarnings) {
		if (old.getParentID() != null) {
			Company c = companyService.showByID(old.getParentID());
			c.setAllEarnings(c.getAllEarnings() - allEarnings);
			companyService.updateCompany(c);
			editingOldParants(c, allEarnings);
		}

	}

	private void editingParants(Company parentC, Integer allEarnings) {
		if (parentC.getParentID() != null) {
			Company c = companyService.showByID(parentC.getParentID());
			c.setAllEarnings(c.getAllEarnings() + allEarnings);
			companyService.updateCompany(c);
			editingParants(c, allEarnings);
		}
	}

	@RequestMapping(value = "/showAllCompany")
	public String getCompanies(Model model) {
		List<Company> list = companyService.showAllCompany();
		model.addAttribute("companies", list);
		return "company-all";
	}

	@RequestMapping(value = "/deleteCompany")
	public String deleteCompany(@RequestParam(value = "companyID") Integer companyID) {
		Company company = companyService.showByID(companyID);
		if (company.getParentID() != null) {
			Company old = companyService.showByID(company.getParentID());
			old.setAllEarnings(old.getAllEarnings() - company.getAllEarnings());
			editingOldParants(old, company.getAllEarnings());
			companyService.updateCompany(old);
		}
		List<Company> list = companyService.showAllCompany();
		for (Company company2 : list) {
			if (company2.getParentID() != null) {
				if (company2.getParentID().equals(company.getCompanyID())) {
					company2.setParentID(null);
					company2.setParent(null);
					companyService.updateCompany(company2);
				}
			}
		}
		companyService.deleteCompany(companyID);
		return "redirect:/showAllCompany";
	}

	@RequestMapping(value = "/editCompany")
	public String editCompany(@RequestParam(value = "companyID") Integer companyID, Model model) {
		List<Company> list = companyService.showAllCompany();
		Company company = companyService.showByID(companyID);
		model.addAttribute("company", company);
		model.addAttribute("companies", list);
		return "company-edit";
	}

	@RequestMapping(value = "/showCompanyTree")
	public String showCompanyTree(Model model) {
		List<Company> list = companyService.showAllCompany();
		List<Company> head = new ArrayList<Company>();
		for (Company company : list) {
			if (company.getParentID() == null) {
				head.add(company);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("head", head);
		return "company-tree";
	}

	@RequestMapping(value = "/showOneCompanyTree")
	public String showOneCompanyTree(@RequestParam(value = "companyID") Integer companyID, Model model) {
		List<Company> list = companyService.showAllCompany();
		List<Company> head = new ArrayList<Company>();
		Company company = companyService.showByID(companyID);
		head.add(company);
		model.addAttribute("list", list);
		model.addAttribute("head", head);
		return "company-tree";
	}
}
