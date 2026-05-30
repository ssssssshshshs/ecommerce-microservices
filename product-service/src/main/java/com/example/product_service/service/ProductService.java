
package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository repository;

    // Constructor Injection
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Add Product
  /*  public Product addProduct(Product product) {

        product.setCreatedAt(LocalDateTime.now());

        return repository.save(product);
    }
*/

    // =========================
    // ADD PRODUCT
    // RequestDTO → Entity → ResponseDTO
    // =========================

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

        // Create Entity Object
        Product product = new Product();

        // RequestDTO → Entity Mapping
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(dto.getCategory());

        // Server-side field
        product.setCreatedAt(LocalDateTime.now());

        // Save into Database
        Product savedProduct = repository.save(product);

        // Entity → ResponseDTO Mapping
        ProductResponseDTO responseDTO =
                new ProductResponseDTO();

        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setDescription(savedProduct.getDescription());
        responseDTO.setPrice(savedProduct.getPrice());
        responseDTO.setStockQuantity(savedProduct.getStockQuantity());
        responseDTO.setCategory(savedProduct.getCategory());
        responseDTO.setCreatedAt(savedProduct.getCreatedAt());

        return responseDTO;
    }

// =========================
    // GET ALL PRODUCTS
    // Entity → ResponseDTO
    // =========================

    public List<ProductResponseDTO> getAllProducts() {

        // Fetch Entity List from DB
        List<Product> products = repository.findAll();

        // DTO List
        List<ProductResponseDTO> responseList =
                new ArrayList<>();

        // Convert Entity → DTO
        for (Product product : products) {

            ProductResponseDTO dto =
                    new ProductResponseDTO();

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


























/*
    // Get All Products
    public List<Product> getAllProducts() {

        return repository.findAll();
    }
*/
    // Get Product By Id
    public Product getProductById(Integer id) {

        Optional<Product> optionalProduct = repository.findById(id);

        return optionalProduct.orElse(null);
    }

    // Update Product
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
    public Product updatePartialProduct(Integer id, Product updatedProduct) {

        Product existingProduct = repository.findById(id).orElse(null);

        if (existingProduct != null) {

            // Update only non-null fields

            if (updatedProduct.getName() != null) {
                existingProduct.setName(updatedProduct.getName());
            }

            if (updatedProduct.getDescription() != null) {
                existingProduct.setDescription(updatedProduct.getDescription());
            }

            if (updatedProduct.getPrice() != null) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }

            if (updatedProduct.getStockQuantity() != null) {
                existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
            }

            if (updatedProduct.getCategory() != null) {
                existingProduct.setCategory(updatedProduct.getCategory());
            }

            return repository.save(existingProduct);
        }

        return null;
    }

    // Delete Product
    public void deleteProduct(Integer id) {

        repository.deleteById(id);
    }
}