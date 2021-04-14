package com.example.Planner.controllers;

import com.example.Planner.dto.InquiryDTO;
import com.example.Planner.exception.RequestException;
import com.example.Planner.models.*;
import com.example.Planner.pdf.InquiryPDF;
import com.example.Planner.services.CompanyService;
import com.example.Planner.services.ContactService;
import com.example.Planner.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private InquiryPDF inquiryPDF;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        return findPaginated(1, model,"all",  itemsPerPage);

    }

    @GetMapping("{statuscode}/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model,
                                @PathVariable(value = "statuscode") String statuscode,
                                @RequestParam(name="itemsPerPage", required = false, defaultValue = "1") String itemsPerPage) {

        Page<Inquiry> page = inquiryService.findAllByInqStatusStartingWith(pageNo, Integer.valueOf(itemsPerPage), statuscode);
        List<Inquiry> inquiryList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("inquiries", inquiryList);
        model.addAttribute("statuscode", statuscode);
        model.addAttribute("itemsPerPage", itemsPerPage);

        return "inquiry/index";
    }

    @GetMapping(value = "/addInquiry")
    public String addInquiry(Model model) {

        List<Company> companyList = companyService.getAllCompanies();
        List<Contact> contactList = contactService.findAll();

        model.addAttribute("companies", companyList);
        model.addAttribute("contacts", contactList);
        model.addAttribute("lastInqId", inquiryService.findLastInq().getId() + 1);

        return "/inquiry/add_inquiry";
    }

    @PostMapping(value = "/saveInquiry")
    public String saveInquiry(InquiryDTO inquiryDTO,
                              RedirectAttributes redirectAttributes) {

        try{

            inquiryService.saveInquiry(inquiryDTO);
            redirectAttributes.addFlashAttribute("message", "Inquiry added successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to add this inquiry! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            //throw new RequestException("There was a problem trying to add this inquiry! Try again!");

        }

        return "redirect:/inquiry/index";

    }

    @GetMapping(value = "/edit/{inquiryId}")
    public String editInquiry(Model model,
                              @PathVariable(name = "inquiryId") int inquiryId) {

        Inquiry inquiry = inquiryService.findById(inquiryId);
        List<Company> companyList = companyService.getAllCompanies();
        List<Contact> contactList = contactService.findAll();
        List<Contact> companyContacts = contactService.findAllContactsByCompanyID(inquiry.getCompany().getId());
        List<Contact> salesContacts = contactService.findAllContactsByCompanyID(inquiry.getSalesCompany().getId());

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("companies", companyList);
        model.addAttribute("companyContacts", companyContacts);
        model.addAttribute("salesContacts", salesContacts);
        model.addAttribute("contacts", contactList);

        return "/inquiry/edit_inquiry";
    }

    @PostMapping(value = "/updateInquiry/{inquiryId}")
    public String updateInquiry(@PathVariable(name = "inquiryId") int inquiryId,
                                RedirectAttributes redirectAttributes,
                                InquiryDTO inquiryDTO) {

        try{

            inquiryService.updateInquiry(inquiryDTO, inquiryId);
            redirectAttributes.addFlashAttribute("message", "Inquiry updated successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to update this inquiry! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            //throw new RequestException("There was a problem trying to add this inquiry! Try again!");

        }

        return "redirect:/inquiry/index";

    }

    @GetMapping(value = "/deleteInquiry/{inquiryId}")
    public String deleteInquiry(@PathVariable(name = "inquiryId") int inquiryId,
                                RedirectAttributes redirectAttributes) {

        try{

            inquiryService.deleteInquiry(inquiryId);
            redirectAttributes.addFlashAttribute("message", "Inquiry deleted successfully");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        } catch (Exception e) {

            RequestException exception = new RequestException("There was a problem trying to delete this inquiry! Try again!");

            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        }

        return "redirect:/inquiry/index";
    }

    @GetMapping(value = "/inquiry_info/{inquiryId}")
    public String inquiry_info(Model model,
                               @PathVariable(name = "inquiryId") int inquiryId) {

        Inquiry inquiry = inquiryService.findById(inquiryId);
        List<Product> productList = inquiry.getProductList();
        List<Order> orderList = inquiry.getOrderList();

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("productList", productList);
        model.addAttribute("orderList", orderList);
        return "/inquiry/inquiry_info";
    }

    @GetMapping(value = "/generateInquiry/{inquiryId}")
    public ResponseEntity<?> createPDF(@PathVariable(value = "inquiryId") int inquiryId) {

        return inquiryPDF.createPDF(inquiryId);
    }
}
