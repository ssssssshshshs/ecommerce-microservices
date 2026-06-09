package com.example.orderservice.client;


import com.example.orderservice.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    /*
        public ProductResponseDTO getProduct(Integer id) {

            String url =
                    "http://localhost:8081/products/" + id;

            return restTemplate.getForObject(
                    url,
                    ProductResponseDTO.class
            );
        }
    }


     */
    public ProductResponseDTO getProduct(Integer id) {

        try {

            String url =
                    "http://localhost:8081/products/" + id;

            return restTemplate.getForObject(
                    url,
                    ProductResponseDTO.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            throw e;
        }
    }
}