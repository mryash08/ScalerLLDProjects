package com.example.databaseconnection;

public class MySQLDatabase extends Database {
    public MySQLDatabase(String databaseName) {
        super(databaseName);

    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void databaseConnection() {
        //Implement the logic to connect MYSQL Database
        System.out.println("Connecting to MYSQL Database");
    }

    @Override
    public DatabaseType supportsType() {
        return DatabaseType.MYSQL;
    }
}
