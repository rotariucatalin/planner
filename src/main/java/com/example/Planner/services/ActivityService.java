package com.example.Planner.services;

import com.example.Planner.dto.ActivityDTO;
import com.example.Planner.models.Activity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActivityService {

   List<Activity> getAllActivities();

   @Cacheable(value="activity", key="#p0")
   Activity getActivityById(int activityId);

   @Caching(
           put = {@CachePut(value = "activity", key = "#result.id")},
           evict = {@CacheEvict(value = "allActivities", allEntries = true)}
   )
   Activity updateActivity(ActivityDTO activityDTO, int activityId);

   @Caching(
           put = {@CachePut(value = "activity", key = "#activityDTO.getId()")},
           evict = {@CacheEvict(value = "allActivities", allEntries = true)}
   )
   Activity saveActivity(ActivityDTO activityDTO);

   @Caching(
           evict = {
                   @CacheEvict(value = "activity", key = "#p0"),
                   @CacheEvict(value = "allActivities", allEntries = true)
           }
   )
   void removeActivityById(int activityId);

   @Cacheable(value = "allActivities")
   Page<Activity> findPaginate(int pageNo, int pageSize, String status);

   @Cacheable(value = "allActivities")
   Page<Activity> findByStatusStartingWithAndCompany_Name(int pageNo, int pageSize, String status, String companyName, String contactId);

   List<Activity> excelExport(String status, String companyName, String contactId);

   List<Activity> findAllByCompanyId(int companyId);
}
