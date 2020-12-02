package com.buaa.scse.blockchain.blockchainserver.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
    public MongoDBUtil() {
    }

    public static MongoDatabase getConnect1() {
        MongoClient mongoClient = new MongoClient("10.2.4.43", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }


}