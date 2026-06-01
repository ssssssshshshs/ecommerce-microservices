
package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.exception.ProductNotFoundException;
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

    // Get Product By Id

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


        // Entity -> ResponseDTO Mapping
        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(product.getId());
        responseDTO.setName(product.getName());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setCategory(product.getCategory());
        responseDTO.setCreatedAt(product.getCreatedAt());
        responseDTO.setStockQuantity(product.getStockQuantity());
        return responseDTO;
    }


    // Update Product  ... without DTO Layer
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
    public ProductResponseDTO updateProduct(Integer id,ProductRequestDTO dto){
        Product existingProduct=repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(
                        "Product not found with id : " + id));

        //Update entity by RequestDTO...
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setCategory(dto.getCategory());
        existingProduct.setStockQuantity(dto.getStockQuantity());

        Product updatedProduct = repository.save(existingProduct);

// entity -> Response

        ProductResponseDTO responseDTO =new ProductResponseDTO();
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setId(updatedProduct.getId());
        responseDTO.setDescription(updatedProduct.getDescription());
        responseDTO.setPrice(updatedProduct.getPrice());
        responseDTO.setCategory(updatedProduct.getCategory());
        responseDTO.setStockQuantity(updatedProduct.getStockQuantity());
        responseDTO.setCreatedAt(updatedProduct.getCreatedAt());

        return responseDTO;


    }
    // updatePartial  without exception and DTO layer
   /*
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


    */

    //Update Partial with exception Handling and DTO layer....

    public ProductResponseDTO updatePartialProduct(
            Integer id,
            ProductRequestDTO dto) {

        Product existingProduct = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

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

        Product updatedProduct = repository.save(existingProduct);

        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(updatedProduct.getId());
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setDescription(updatedProduct.getDescription());
        responseDTO.setPrice(updatedProduct.getPrice());
        responseDTO.setStockQuantity(updatedProduct.getStockQuantity());
        responseDTO.setCategory(updatedProduct.getCategory());
        responseDTO.setCreatedAt(updatedProduct.getCreatedAt());

        return responseDTO;
    }



  /*  // Delete Product     ... without exception Handling.
    public void deleteProduct(Integer id) {

        repository.deleteById(id);
    }
    */

    //Delete product ... with exception handling.

    public void deleteProduct(Integer id) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

        repository.delete(existingProduct);
    }


    }



