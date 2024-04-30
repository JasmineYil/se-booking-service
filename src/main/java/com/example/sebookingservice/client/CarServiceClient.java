package com.example.sebookingservice.client;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CarServiceClient {

    private final RestTemplate restTemplate;
    private final String carServiceUrl = "http://localhost:9093";

    @Autowired
    public CarServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Car getCarDetails(String carId) {
        String url = UriComponentsBuilder.fromHttpUrl(carServiceUrl)
                .path("/cars/{id}")
                .buildAndExpand(carId)
                .toUriString();
        return restTemplate.getForObject(url, Car.class);
    }
}
