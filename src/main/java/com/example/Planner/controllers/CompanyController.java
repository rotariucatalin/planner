package com.example.Planner.controllers;

import com.example.Planner.dto.CompanyDTO;
import com.example.Planner.exception.RequestException;
import com.example.Planner.models.Company;
import com.example.Planner.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        return findPaginated(1, model, itemsPerPage);

    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model,
                                @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        Page<Company> page = companyService.getAllCompaniesPaginate(pageNo, Integer.valueOf(itemsPerPage));

        List<Company> companyList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("companies", companyList);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "company/index";
    }

    @GetMapping(value = "/addCompany")
    public String addCompany() {

        return "company/add_company";
    }

    @PostMapping(value = "/saveCompany")
    public String saveActivity(CompanyDTO companyDTO,
                               RedirectAttributes redirectAttributes) {

        try{

            companyService.saveCompany(companyDTO);
            redirectAttributes.addFlashAttribute("message", "Company added successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to add this company! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/company/index";
    }

    @GetMapping(value = "/editCompany/{companyId}")
    public String editCompany(Model model,
                              @PathVariable(value = "companyId") int companyId) {

        Company company = companyService.getCompany(companyId);

        model.addAttribute("company", company);

        return "company/edit_company";
    }

    @PostMapping(value = "/updateCompany/{companyId}")
    public String updateCompany(CompanyDTO companyDTO,
                                @PathVariable(value = "companyId") int companyId,
                                RedirectAttributes redirectAttributes) {


        try{

            companyService.updateCompany(companyDTO, companyId);
            redirectAttributes.addFlashAttribute("message", "Company updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to update this company! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/company/index";
    }

    @GetMapping(value = "/deleteCompany/{companyId}")
    public String deleteCompany(@PathVariable(value = "companyId") int companyId,
                                RedirectAttributes redirectAttributes) {

        try{

            companyService.deleteCompany(companyId);
            redirectAttributes.addFlashAttribute("message", "Company updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to update this company! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/company/index";
    }
}
