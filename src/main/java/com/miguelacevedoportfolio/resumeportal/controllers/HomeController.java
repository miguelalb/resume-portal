package com.miguelacevedoportfolio.resumeportal.controllers;

import com.miguelacevedoportfolio.resumeportal.models.Education;
import com.miguelacevedoportfolio.resumeportal.models.Job;
import com.miguelacevedoportfolio.resumeportal.models.UserProfile;
import com.miguelacevedoportfolio.resumeportal.repositories.UserProfileRepository;
import com.miguelacevedoportfolio.resumeportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        List<String> responsibilities = Arrays.asList(
                "Responsible for A, B and C.",
                "Responsible for D, E and F.",
                "Responsible for X, Y and Z.");

        Job job0 = new Job();
        job0.setCompany("Company 0");
        job0.setDesignation("Designation");
        job0.getResponsibilities().addAll(responsibilities);
        job0.setStartDate(LocalDate.of(2020, 3, 1));
        job0.setCurrentJob(true);
        Job job1 = new Job();
        job1.setCompany("Company 1");
        job1.setDesignation("Designation");
        job1.getResponsibilities().addAll(responsibilities);
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        job1.setEndDate(LocalDate.of(2020, 3, 1));
        Job job2 = new Job();
        job2.setCompany("Company 2");
        job2.setDesignation("Designation");
        job2.getResponsibilities().addAll(responsibilities);
        job2.setStartDate(LocalDate.of(2019, 5, 1));
        job2.setEndDate(LocalDate.of(2020, 1, 1));

        profile1.getJobs().clear();
        profile1.getJobs().add(job0);
        profile1.getJobs().add(job1);
        profile1.getJobs().add(job2);

        Education e1 = new Education();
        e1.setCollege("Massachusetts Institute of Technology");
        e1.setQualification("PH.D in Computational Science & Engineering");
        e1.setSummary("Studied a lot.");
        e1.setStartDate(LocalDate.of(2020, 3, 1));
        e1.setCurrent(true);

        Education e2 = new Education();
        e2.setCollege("Carnegie Mellon University");
        e2.setQualification("Masters in Applied Artificial Intelligence");
        e2.setSummary("Studied a lot.");
        e2.setStartDate(LocalDate.of(2018, 3, 1));
        e2.setEndDate(LocalDate.of(2020, 1, 1));

        Education e3 = new Education();
        e3.setCollege("Harvard University");
        e3.setQualification("Bachelors Engineering & Applied Sciences");
        e3.setSummary("Studied a lot.");
        e3.setStartDate(LocalDate.of(2014, 3, 1));
        e3.setEndDate(LocalDate.of(2018, 3, 1));

        profile1.getEducations().clear();
        profile1.getEducations().add(e1);
        profile1.getEducations().add(e2);
        profile1.getEducations().add(e3);

        List<String> skills = Arrays.asList(
                "Java", "Python", "Spring Boot",
                "Flask", "FastAPI", "Django",
                "Javascript", "VueJS", "Angular",
                "Docker", "Kubernetes", "AWS");

        profile1.getSkills().addAll(skills);

        userProfileRepository.save(profile1);

        model.addAttribute("profile", profile1);
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        String userId = principal.getName();
        model.addAttribute("userId", userId);

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal, Model model) {
        String userId = principal.getName();
        // save updated values in the form
        return "redirect:/view/" + userId;
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
