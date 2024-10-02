package com.example.cloudproviders.adapters;

import com.example.cloudproviders.libraries.aws.AWSApi;
import com.example.cloudproviders.libraries.aws.AWSConnectionResponse;
import com.example.cloudproviders.models.Connection;
import com.example.cloudproviders.models.ConnectionStatus;

public class AWSAdapter implements CloudAdapter {

    AWSApi api = new AWSApi();

    @Override
    public Connection createConnection(long userId) {
        AWSConnectionResponse response = api.createConnection(userId);
        Connection connection = new Connection();
        connection.setUserId(userId);
        connection.setConnectionId(response.getConnectionId());
        connection.setConnectionStatus(ConnectionStatus.valueOf(response.getConnectionStatus()));
        return connection;
    }
    
}
