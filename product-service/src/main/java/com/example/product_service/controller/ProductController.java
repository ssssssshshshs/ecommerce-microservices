package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequestDTO;
import com.example.product_service.dto.ProductResponseDTO;
import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   /*
    @PostMapping
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductRequestDTO dto){

        return service.addProduct(dto);
    }


    */

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(
            @Valid @RequestBody ProductRequestDTO dto) {

        ProductResponseDTO response = service.addProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<ProductResponseDTO> response =
                service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    // Get All Products
   /*
    @GetMapping
    public List<Product> getAllProducts() {

        return service.getAllProducts();
    }


    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return service.getAllProducts();
    }


    */


    // Get Product By Id
  /*
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {

        return service.getProductById(id);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id){
        return service.getProductById(id);
    }


   */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id) {
        ProductResponseDTO response = service.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // Update Product ... Without DTO layer
    /*
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {

        return service.updateProduct(id, product);
    }


    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Integer id,
                                            @Valid @RequestBody ProductRequestDTO dto){
        return service.updateProduct(id,dto);
    }


     */
    @PutMapping("/{id}")

    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @Valid @RequestBody
    ProductRequestDTO dto) {
        ProductResponseDTO response = service.updateProduct(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    //partial update...... without DTO
/*
    @PatchMapping("/{id}")
    public Product updatePartialProduct(
            @PathVariable Integer id,
            @RequestBody Product product) {

        return service.updatePartialProduct(id, product);
    }

    @PatchMapping("/{id}")
    public ProductResponseDTO updatePartialProduct(
            @PathVariable Integer id,
            @RequestBody   ProductRequestDTO dto
    ){
        return service.updatePartialProduct(id,dto);
    }

 */
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updatePartialProduct(
            @PathVariable Integer id, @RequestBody ProductRequestDTO dto
    ) {
        ProductResponseDTO response = service.updatePartialProduct(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Delete Product
  /*
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}


   */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id) {

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}