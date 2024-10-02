package com.example.databaseconnection;

public class DatabaseFactory {

        public static Database createDatabase(DatabaseType databaseType, String databaseName){
            if(databaseType.equals(DatabaseType.MYSQL)){
                return new MySQLDatabase(databaseName);
            }else if(databaseType.equals(DatabaseType.MONGO_DB)){
                return new MongoDBDatabase(databaseName);
            }else{
                return new PostgreSQLDatabase(databaseName);
            }
           
            
        }
    

}
