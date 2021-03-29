package com.example.Planner.controllers;

import com.example.Planner.dto.CompanyDTO;
import com.example.Planner.exception.RequestException;
import com.example.Planner.models.Activity;
import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import com.example.Planner.services.ActivityService;
import com.example.Planner.services.CompanyService;
import com.example.Planner.services.ContactService;
import com.example.Planner.utils.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ActivityService activityService;

    private Set<CompanyType> companyTypes = EnumSet.allOf(CompanyType.class);
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage,
                        @RequestParam(name="type", required = false, defaultValue = "") String type) {

        return findPaginated(1, model, itemsPerPage,type);

    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model,
                                @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage,
                                @RequestParam(name="type", required = false, defaultValue = "") String type) {

        Page<Company> page = companyService.getAllCompaniesPaginate(pageNo, Integer.valueOf(itemsPerPage), type);

        List<Company> companyList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("companies", companyList);
        model.addAttribute("type", type);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "company/index";
    }

    @GetMapping(value = "/addCompany")
    public String addCompany(Model model) {

        List<Company> allCompanies = companyService.getAllCompanies();

        model.addAttribute("type", companyTypes);
        model.addAttribute("companies", allCompanies);

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
        List<Company> companyList = companyService.getAllCompanies();


        ResponseEntity<List<Contact>> responseEntity = restTemplate.exchange("http://localhost:8080/contacts/contactsByCompanyId/"+companyId+"", HttpMethod.GET, null, new ParameterizedTypeReference<List<Contact>>() {});
        List<Contact> contactList = responseEntity.getBody();

        model.addAttribute("company", company);
        model.addAttribute("companies", companyList);
        model.addAttribute("contacts", contactList);
        model.addAttribute("type", companyTypes);

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

    @GetMapping(value = "/company_info/{companyId}")
    public String companyInfo(Model model,
                              @PathVariable(value = "companyId") int companyId) {

        Company company = companyService.getCompany(companyId);

        //ResponseEntity<List<Contact>> responseContact = restTemplate.exchange("http://localhost:8080/contacts/contactsByCompanyId/"+companyId+"", HttpMethod.GET, null, new ParameterizedTypeReference<List<Contact>>(){});
        //List<Contact> contactList = responseContact.getBody();

        //ResponseEntity<List<Activity>> responseActivity = restTemplate.exchange("http://localhost:8080/activities/getAllActivitiesForCompany/"+companyId+"", HttpMethod.GET, null, new ParameterizedTypeReference<List<Activity>>(){});
        //List<Activity> activityList = responseActivity.getBody();

        List<Contact> contactList = contactService.findAllContactsByCompanyID(companyId);
        List<Activity> activityList = activityService.findAllByCompanyId(companyId);

        model.addAttribute("company", company);
        model.addAttribute("contacts", contactList);
        model.addAttribute("activities", activityList);

        return "/company/company_info";
    }
}
