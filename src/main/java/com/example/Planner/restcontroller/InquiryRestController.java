package com.example.Planner.restcontroller;

import com.example.Planner.models.Inquiry;
import com.example.Planner.services.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InquiryRestController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping(value = "/inquiries/getInquiry")
    Inquiry getInquiry() {

        return inquiryService.findById(1);

    }

}
