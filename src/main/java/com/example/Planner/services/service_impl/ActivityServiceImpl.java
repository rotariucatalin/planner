package com.example.Planner.services.service_impl;

import com.example.Planner.dto.ActivityDTO;
import com.example.Planner.models.Activity;
import com.example.Planner.repositories.ActivityRepository;
import com.example.Planner.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Activity getActivityById(int activity_id) {

        return activityRepository.findById(activity_id).get();
    }

    @Override
    @Transactional
    public Activity updateActivity(ActivityDTO activityDTO, int activityId) {

        Activity activity = activityRepository.findById(activityId).get();

        activity.setCompany(activityDTO.getCompany());
        activity.setContact(activityDTO.getContact());
        activity.setLocation(activityDTO.getLocation());
        activity.setReference(activityDTO.getReference());
        activity.setStatus(activityDTO.getStatus());
        activity.setSubject(activityDTO.getSubject());
        activity.setType(activityDTO.getType());
        activity.setDate(activityDTO.getDate());

        return activity;
    }

    @Override
    @Transactional
    public Activity saveActivity(ActivityDTO activityDTO) {

        Activity newActivity = new Activity();

        newActivity.setCompany(activityDTO.getCompany());
        newActivity.setContact(activityDTO.getContact());
        newActivity.setLocation(activityDTO.getLocation());
        newActivity.setReference(activityDTO.getReference());
        newActivity.setStatus(activityDTO.getStatus());
        newActivity.setSubject(activityDTO.getSubject());
        newActivity.setType(activityDTO.getType());
        newActivity.setDate(activityDTO.getDate());

        return activityRepository.save(newActivity);
    }

    @Override
    public Page<Activity> findPaginate(int pageNo, int pageSize, String status) {
        Pageable pageable = PageRequest.of(pageNo -1 , pageSize);

        if(status.equals("all")) status = "";

        return activityRepository.findByStatusStartingWith(pageable, status);
    }

    @Override
    @Transactional(rollbackFor = EntityNotFoundException.class)
    public void removeActivityById(int activityId) {
        activityRepository.deleteById(activityId);
    }

    @Override
    public Page<Activity> findByStatusStartingWithAndCompany_Name(int pageNo,
                                                                  int pageSize,
                                                                  String status,
                                                                  String companyName,
                                                                  String contactId) {

        Pageable pageable = PageRequest.of(pageNo -1 , pageSize);

        if(status.equals("all"))        status      = "";

        if(!contactId.isEmpty())
            return activityRepository.findByStatusStartingWithAndCompany_NameStartingWithAndContact_Id(pageable, status, companyName, Integer.valueOf(contactId));
        else
            return activityRepository.findByStatusStartingWithAndCompany_NameStartingWith(pageable, status, companyName);
    }

    @Override
    public List<Activity> excelExport(String status, String companyName, String contactId) {

        if(!contactId.isEmpty()) {
            return activityRepository.findByStatusStartingWithAndCompany_NameStartingWithAndContact_Id(status, companyName, Integer.valueOf(contactId));
        } else {
            return activityRepository.findByStatusStartingWithAndCompany_NameStartingWith(status, companyName);
        }
    }
}
