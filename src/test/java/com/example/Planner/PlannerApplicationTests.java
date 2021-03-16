package com.example.Planner;

import com.example.Planner.models.Activity;
import com.example.Planner.models.Company;
import com.example.Planner.services.ActivityService;
import com.example.Planner.services.CompanyService;
import com.example.Planner.services.ContactService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlannerApplicationTests {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ContactService contactService;

    @Test
    public void testCompany() {

        Company company = companyService.getCompany(1);

        Assert.assertEquals("DMT", company.getName());
        Assert.assertNotNull(company);


    }

    @Test
    public void testActivity() {

        Activity activity = activityService.getActivityById(5);

        Assert.assertEquals("11111", activity.getSubject());
        Assert.assertNotNull(activity);


    }
}
