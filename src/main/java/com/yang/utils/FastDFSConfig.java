package com.yang.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yangyao
 */
@Component
@PropertySource(value = "classpath:tracker.properties")
public class FastDFSConfig {

    @Value("tracker_server")
    String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
