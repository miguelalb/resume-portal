package com.miguelacevedoportfolio.resumeportal.controllers;

import com.miguelacevedoportfolio.resumeportal.models.Job;
import com.miguelacevedoportfolio.resumeportal.models.UserProfile;
import com.miguelacevedoportfolio.resumeportal.repositories.UserProfileRepository;
import com.miguelacevedoportfolio.resumeportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(Model model) {
        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
        profileOptional.orElseThrow(() -> new RuntimeException("Not found "));

        UserProfile profile1 = profileOptional.get();

        Job job1 = new Job();
        job1.setCompany("Company 1");
        job1.setDesignation("Designation");
        job1.setResponsibility("Responsible for X,Y and Z.");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        job1.setEndDate(LocalDate.of(2020, 3, 1));
        Job job2 = new Job();
        job2.setCompany("Company 2");
        job2.setDesignation("Designation");
        job2.setResponsibility("Accomplished A,B and C.");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2019, 5, 1));
        job2.setEndDate(LocalDate.of(2020, 1, 1));
        profile1.getJobs().clear();
        profile1.getJobs().add(job1);
        profile1.getJobs().add(job2);
        userProfileRepository.save(profile1);

        model.addAttribute("profile", profile1);
        return "profile";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);
        System.out.println(userProfile.getJobs());

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
