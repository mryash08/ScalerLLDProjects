package com.example.cloudproviders.adapters;

import com.example.cloudproviders.libraries.google.GoogleApi;
import com.example.cloudproviders.libraries.google.GoogleConnectionResponse;
import com.example.cloudproviders.models.Connection;
import com.example.cloudproviders.models.ConnectionStatus;

public class GoogleAdapter implements CloudAdapter {

    GoogleApi api = new GoogleApi();

    @Override
    public Connection createConnection(long userId) {
        GoogleConnectionResponse response = api.createConnection(userId);
        Connection connection = new Connection();
        connection.setUserId(userId);
        connection.setConnectionId(response.getConnectionId());
        connection.setConnectionStatus(ConnectionStatus.valueOf(response.getConnectionStatus()));
        return connection;
    }
    
}
