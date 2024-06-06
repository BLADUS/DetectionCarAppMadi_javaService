package com.example.DetectionCarAppMadi_javaService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Repositories.CollectionRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class CollectionController {
    private final CollectionRepository collectionRepository;
    public CollectionController(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @GetMapping("/results")
    public String getMethodName(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("results", collectionRepository.getCollectionsByPrefix(currentUser.getUsername()));

        return "resultsPage";
    }
    
}
