package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.ProductRepository;
import lombok.*;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.sql.Savepoint;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Data
@Builder

@Service
public class ProductService {

    private final ProductRepository repository;

    // Constructor Injection
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Add Product
/*
    public Product addProduct(Product product) {

        product.setCreatedAt(LocalDateTime.now());

        return repository.save(product);
    }
*/
// Without Lombok code...........
    /*
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

        try {
            Product product = new Product();

            // DTO → Entity
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStockQuantity(dto.getStockQuantity());
            product.setCategory(dto.getCategory());
            product.setCreatedAt(LocalDateTime.now());

            Product savedProduct = repository.save(product);

            // Entity → ResponseDTO
            ProductResponseDTO responseDTO = new ProductResponseDTO();

            responseDTO.setId(savedProduct.getId());
            responseDTO.setName(savedProduct.getName());
            responseDTO.setDescription(savedProduct.getDescription());
            responseDTO.setPrice(savedProduct.getPrice());
            responseDTO.setStockQuantity(savedProduct.getStockQuantity());
            responseDTO.setCategory(savedProduct.getCategory());
            responseDTO.setCreatedAt(savedProduct.getCreatedAt());

            return responseDTO;
        }
        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to save product", e);
        }
    }



     */

// With Lombok code................

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

        try {
            Product product = Product.builder()

            // DTO → Entity
            .name(dto.getName())
            .description(dto.getDescription())
            .price(dto.getPrice())
            .stockQuantity(dto.getStockQuantity())
            .category(dto.getCategory())
            .createdAt(LocalDateTime.now())
            .build();

            Product savedProduct = repository.save(product);

            // Entity → ResponseDTO
            //ProductResponseDTO responseDTO = ProductResponseDTO.builder();


            return ProductResponseDTO.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .stockQuantity(savedProduct.getStockQuantity())
                    .category(savedProduct.getCategory())
                    .createdAt(savedProduct.getCreatedAt())
                    .build();

        }
        catch (Exception e) {

            throw new RuntimeException(
                    "Unable to save product", e);
        }
    }


    // Get All Products

   /*
    public List<Product> getAllProducts() {

        return repository.findAll();
    }
*/

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = repository.findAll();

        List<ProductResponseDTO> responseList = new ArrayList<>();

        for (Product product : products) {

            ProductResponseDTO dto = new ProductResponseDTO();

            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setPrice(product.getPrice());
            dto.setStockQuantity(product.getStockQuantity());
            dto.setCategory(product.getCategory());
            dto.setCreatedAt(product.getCreatedAt());

            responseList.add(dto);
        }

        return responseList;
    }

    // Get Product By Id

    /*
    public Product getProductById(Integer id) {

        Optional<Product> optionalProduct = repository.findById(id);

        return optionalProduct.orElse(null);
    }
*/

    public ProductResponseDTO getProductById(Integer id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));
        ProductResponseDTO dto =
                new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(product.getCategory());
        dto.setCreatedAt(product.getCreatedAt());

        return dto;
    }


    // Update Product
/*
    public Product updateProduct(Integer id, Product updatedProduct) {

        Product existingProduct = repository.findById(id).orElse(null);

        if (existingProduct != null) {

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
            existingProduct.setCategory(updatedProduct.getCategory());

            return repository.save(existingProduct);
        }

        return null;
    }


 */

    public ProductResponseDTO updateProduct(
            Integer id,
            ProductRequestDTO dto) {

        Product existingProduct = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStockQuantity(dto.getStockQuantity());
        existingProduct.setCategory(dto.getCategory());

        Product savedProduct = repository.save(existingProduct);

        ProductResponseDTO response = new ProductResponseDTO();

        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());

        return response;
    }





















    // Partial Updated...

    public ProductResponseDTO updatePartialProduct(Integer id, ProductRequestDTO dto) {


        Product existingProduct = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));



       // Product existingProduct = repository.findById(id).orElse(null);

        if (existingProduct != null) {

            // Update only non-null fields

            // requestDTO -> entity.............

            if (dto.getName() != null) {
                existingProduct.setName(dto.getName());
            }

            if (dto.getDescription() != null) {
                existingProduct.setDescription(dto.getDescription());
            }

            if (dto.getPrice() != null) {
                existingProduct.setPrice(dto.getPrice());
            }

            if (dto.getStockQuantity() != null) {
                existingProduct.setStockQuantity(dto.getStockQuantity());
            }

            if (dto.getCategory() != null) {
                existingProduct.setCategory(dto.getCategory());
            }
        }
          Product Savedproduct= repository.save(existingProduct);

            // entity -> responseDTO.........
            ProductResponseDTO response =new ProductResponseDTO();

            response.setName(Savedproduct.getName());
            response.setDescription(Savedproduct.getDescription());
            response.setPrice(Savedproduct.getPrice());
            response.setStockQuantity(Savedproduct.getStockQuantity());
            response.setCategory(Savedproduct.getCategory());
            response.setId(Savedproduct.getId());
        response.setCreatedAt(Savedproduct.getCreatedAt());



        return response;

    }

    // Delete Product
    public void deleteProduct(Integer id) {

        Product existingProduct = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));


        repository.deleteById(id);
    }







}