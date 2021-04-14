package com.example.Planner.restcontroller;

import com.example.Planner.dto.ActivityDTO;
import com.example.Planner.dto.ContactDTO;
import com.example.Planner.models.Activity;
import com.example.Planner.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @GetMapping(value = "/activities/getAllActivitiesForCompany/{companyId}")
    public List<ActivityDTO> activityList(@PathVariable("companyId") int companyId) {

        List<ActivityDTO> activityDTOList = new ArrayList<>();

        List<Activity> activityList = activityService.findAllByCompanyId(companyId);

        activityList.forEach(activity -> {

            ActivityDTO activityDTO = new ActivityDTO();

            activityDTO.setSubject(activity.getSubject());
            activityDTO.setReference(activity.getReference());
            activityDTO.setType(activity.getType());
            activityDTO.setDate(activity.getDate());
            activityDTO.setStatus(activity.getStatus());
            activityDTO.setLocation(activity.getLocation());

            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(activity.getContact().getId());
            contactDTO.setName(activity.getContact().getName());

            activityDTO.setContactDTO(contactDTO);

            activityDTOList.add(activityDTO);

        });

        return activityDTOList;
    }

}
