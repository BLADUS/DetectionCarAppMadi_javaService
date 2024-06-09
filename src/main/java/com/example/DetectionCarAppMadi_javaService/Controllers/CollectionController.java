package com.example.DetectionCarAppMadi_javaService.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Repositories.CollectionRepository;
import com.mongodb.client.MongoCollection;

import jakarta.servlet.http.HttpSession;

@Controller
public class CollectionController {
    private final CollectionRepository collectionRepository;

    public CollectionController(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @GetMapping("/results")
    public String getResultsPage(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("results", collectionRepository.getCollectionsByPrefix(currentUser.getUsername()));

        return "resultsPage";
    }

    @GetMapping("/results/{collectionName}")
    public String getCollectionPage(@PathVariable("collectionName") String collectionName, Model model) {
        MongoCollection<Document> collection = collectionRepository.getCollectionByName(collectionName);
        List<Document> documents = collection.find().into(new ArrayList<>());
        model.addAttribute("documents", documents);
        model.addAttribute("collection_name", collectionName);
    
        return "collectionPage";
    }
        

}
