package com.codecool.stocktraderspringboot.model;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class RemoteURLReader {
    private String endpoint = null;

//    public RemoteURLReader(String endpoint){
//        this.endpoint = endpoint;
//    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String readFromUrl() throws IOException {
        URL url = new URL(this.endpoint);
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return lines;
    }
}
