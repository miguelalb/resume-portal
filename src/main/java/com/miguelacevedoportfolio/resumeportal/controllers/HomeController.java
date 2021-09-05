package com.miguelacevedoportfolio.resumeportal.controllers;

import com.miguelacevedoportfolio.resumeportal.models.Education;
import com.miguelacevedoportfolio.resumeportal.models.Job;
import com.miguelacevedoportfolio.resumeportal.models.SocialMedia;
import com.miguelacevedoportfolio.resumeportal.models.UserProfile;
import com.miguelacevedoportfolio.resumeportal.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(Model model) {

        return "index";
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model, @RequestParam(required = false) String add) {
        String userId = principal.getName();
        model.addAttribute("userId", userId);

        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();

        if (add != null) {
            switch (add) {
                case "job":
                    userProfile.getJobs().add(new Job());
                    break;
                case "education":
                    userProfile.getEducations().add(new Education());
                    break;
                case "skill":
                    userProfile.getSkills().add("");
                    break;
                case "socialmedia":
                    userProfile.getSocialMedias().add(new SocialMedia());
                    break;
            }
        }

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal, Model model, @ModelAttribute UserProfile userProfile) {
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/view/" + userName;
    }

    @GetMapping("/delete")
    public String delete(Principal principal, Model model, @RequestParam String type, @RequestParam int index) {
        String userId = principal.getName();
        model.addAttribute("userId", userId);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();

        switch (type) {
            case "job":
                userProfile.getJobs().remove(index);
                break;
            case "education":
                userProfile.getEducations().remove(index);
                break;
            case "skill":
                userProfile.getSkills().remove(index);
                break;
            case "socialmedia":
                userProfile.getSocialMedias().remove(index);
                break;
        }
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/view/{userId}")
    public String view(Principal principal, @PathVariable String userId, Model model) {
        if (principal != null && principal.getName() != "") {
            boolean currentUsersProfile = principal.getName().equals(userId);
            model.addAttribute("currentUsersProfile", currentUsersProfile);
        }
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile", userProfile);

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
