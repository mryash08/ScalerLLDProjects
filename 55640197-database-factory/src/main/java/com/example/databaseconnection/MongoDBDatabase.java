package com.example.databaseconnection;

public class MongoDBDatabase extends Database {
    
    public MongoDBDatabase(String databaseName) {
        super(databaseName);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void databaseConnection() {
        //Implement the logic to connect MongoDB Database
        System.out.println("Connecting to MongoDB Database");
    }

    public DatabaseType supportsType() {
        return DatabaseType.MONGO_DB;
    }
}
