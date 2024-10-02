package com.example.databaseconnection;

public abstract class Database {
    public String databaseName;
    public Database(String databaseName) {
        this.databaseName = databaseName;
    }
    public abstract DatabaseType supportsType();
    public abstract String getDatabaseName();
    public abstract void databaseConnection();
}
