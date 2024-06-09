package com.example.DetectionCarAppMadi_javaService.Repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectionRepository {

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public CollectionRepository(@Value("${spring.data.mongodb.uri}") String connectionString,
            @Value("${spring.data.mongodb.database}") String dbName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(dbName);
    }

    public List<String> getCollectionsByPrefix(String prefix) {
        List<String> matchingCollections = new ArrayList<>();
        for (String name : database.listCollectionNames()) {
            if (name.startsWith(prefix)) {
                matchingCollections.add(name);
            }
        }
        return matchingCollections;
    }

    public MongoCollection<Document> getCollectionByName(String collectionName){
        return  database.getCollection(collectionName);
    }

}
