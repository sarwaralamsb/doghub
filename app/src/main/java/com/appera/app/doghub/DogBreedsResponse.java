package com.appera.app.doghub;

// DogBreedsResponse.java
import java.util.List;
import java.util.Map;

// DogBreedsResponse.java
import java.util.List;
import java.util.Map;

public class DogBreedsResponse {
    private String status; // API response status
    private Map<String, List<String>> message; // Map of breeds and their sub-breeds

    public String getStatus() {
        return status;
    }

    public Map<String, List<String>> getMessage() {
        return message;
    }
}

