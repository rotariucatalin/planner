package com.example.Planner.controllers;

import com.example.Planner.dto.ActivityDTO;
import com.example.Planner.excel.ActivityExcel;
import com.example.Planner.exception.RequestException;
import com.example.Planner.models.Activity;
import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import com.example.Planner.services.ActivityService;
import com.example.Planner.services.CompanyService;
import com.example.Planner.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ContactService contactService;

    private Set<String> status = new HashSet<>(Arrays.asList("active","canceled","completed","hold"));
    private Set<String> activity_type = new HashSet<>(Arrays.asList("meeting","telecon","email"));

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="companyName", required = false, defaultValue = "") String companyName,
                        @RequestParam(name="contactId", required = false, defaultValue = "") String contactId,
                        @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        return findPaginated(1, model,"all", companyName, contactId, itemsPerPage);

    }

    @GetMapping("{statuscode}/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model,
                                @PathVariable(value = "statuscode") String statuscode,
                                @RequestParam(name="companyName", required = false, defaultValue = "") String companyName,
                                @RequestParam(name="contactId", required = false, defaultValue = "") String contactId,
                                @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        Page<Activity> page = activityService.findByStatusStartingWithAndCompany_Name(pageNo, Integer.valueOf(itemsPerPage), statuscode, companyName, contactId);
        List<Activity> activityList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("activities", activityList);
        model.addAttribute("statuscode", statuscode);
        model.addAttribute("companyName", companyName);
        model.addAttribute("contactId", contactId);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "activity/index";
    }

    @GetMapping("/addActivity")
    public String addActivity(Model model) {

        List<Company> companies = companyService.getAllCompanies();

        model.addAttribute("activity_type", activity_type);
        model.addAttribute("companies", companies);
        model.addAttribute("status", status);

        return "activity/add_activity";
    }

    @PostMapping("/saveActivity")
    public String saveActivity(ActivityDTO activityDTO,
                               RedirectAttributes redirectAttributes) {

        try{

            activityService.saveActivity(activityDTO);
            redirectAttributes.addFlashAttribute("message", "Activity added successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to add this activity! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            //throw new RequestException("There was a problem trying to add this activity! Try again!");

        }

        return "redirect:/activity/index";
    }

    @GetMapping("/edit/{activity_id}")
    public String editActivity(Model model, @PathVariable(value = "activity_id") int activity_id) {

        Activity activity = activityService.getActivityById(activity_id);

        List<Company> companies = companyService.getAllCompanies();
        List<Contact> contacts = contactService.findAllContactsByCompanyID(activity.getCompany().getId());

        model.addAttribute("activity", activity);
        model.addAttribute("activity_type", activity_type);
        model.addAttribute("companies", companies);
        model.addAttribute("contacts", contacts);
        model.addAttribute("status", status);

        return "activity/edit_activity";
    }

    @PostMapping("/updateActivity/{activityId}")
    public String updateActivity(@PathVariable(value = "activityId") int activityId,
                                 ActivityDTO activityDTO,
                                 RedirectAttributes redirectAttributes) {

        try{

            activityService.updateActivity(activityDTO, activityId);

            redirectAttributes.addFlashAttribute("message", "Activity updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", "There was a problem trying to update this activity! Try again!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/activity/index";
    }

    @GetMapping("/deleteActivity/{activityId}")
    public String deleteActivity(@PathVariable(value = "activityId") int activityId,
                                 RedirectAttributes redirectAttributes) {

        try {

            activityService.removeActivityById(activityId);
            redirectAttributes.addFlashAttribute("message", "Activity deleted successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", "There was a problem trying to delete this activity! Try again!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/activity/index";
    }

    @GetMapping("/exportExcel")
    public void exportActivityToExcel(HttpServletResponse response,
                                      @RequestParam(name="companyName", required = false, defaultValue = "") String companyName,
                                      @RequestParam(name="statusCode", required = false, defaultValue = "") String statusCode,
                                      @RequestParam(name="contactId", required = false, defaultValue = "") String contactId) throws IOException {

        response.setContentType("application/octet-stream");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=activities_"+currentDateFormat+".xlsx";

        response.setHeader(headerKey, headerValue);

        if(statusCode.equals("all")) statusCode = "";

        List<Activity> activityList = activityService.excelExport(statusCode,companyName,contactId);

        activityList.forEach(activity -> {
            System.out.println(activity.getSubject());
        });

        ActivityExcel activityExcel = new ActivityExcel(activityList);

        activityExcel.export(response);
    }

}
