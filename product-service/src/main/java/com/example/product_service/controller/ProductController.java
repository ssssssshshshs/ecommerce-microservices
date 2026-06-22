package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    // Constructor Injection
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Add Product
   /*
    @PostMapping
    public Product addProduct(@RequestBody Product product) {

        return service.addProduct(product);
    }
*/
    @PostMapping
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductRequestDTO dto){

        return service.addProduct(dto);
    }






    // Get All Products
   /*
    @GetMapping
    public List<Product> getAllProducts() {

        return service.getAllProducts();
    }
*/

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return service.getAllProducts();
    }





    // Get Product By Id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {

        return service.getProductById(id);
    }

    // Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {

        return service.updateProduct(id, product);
    }

    //partial update......

    @PatchMapping("/{id}")
    public Product updatePartialProduct(
            @PathVariable Integer id,
            @RequestBody Product product) {

        return service.updatePartialProduct(id, product);
    }


    // Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}