package com.example.DetectionCarAppMadi_javaService.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DetectionCarAppMadi_javaService.Entity.User;
import com.example.DetectionCarAppMadi_javaService.Repositories.CollectionRepository;
import com.mongodb.client.FindIterable;
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
    public String getCollectionPage(
            @PathVariable("collectionName") String collectionName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
        MongoCollection<Document> collection = collectionRepository.getCollectionByName(collectionName);
        FindIterable<Document> iterable = collection.find().skip(page * size).limit(size);
        List<Document> documents = new ArrayList<>();
        iterable.into(documents);

        long totalDocuments = collection.countDocuments();
        int totalPages = (int) Math.ceil((double) totalDocuments / size);

        model.addAttribute("documents", documents);
        model.addAttribute("collection_name", collectionName);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "collectionPage";
    }

}
