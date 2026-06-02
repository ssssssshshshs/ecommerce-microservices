
package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ProductResponseDTO addProduct(
            @Valid @RequestBody ProductRequestDTO dto) {

        return service.addProduct(dto);
    }
*/
    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponseDTO> addProduct(
            @Valid @RequestBody ProductRequestDTO dto) {

        ProductResponseDTO response = service.addProduct(dto);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    // Get All Products

   /*
    @GetMapping
    public List<Product> getAllProducts() {

        return service.getAllProducts();
    }

*/

/*
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return service.getAllProducts();
    }
*/

    // Getmapping with status code...

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<ProductResponseDTO> response =
                service.getAllProducts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    // Get Product By Id
   /*
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {

        return service.getProductById(id);
    }

    @GetMapping("/{id}")
public ProductResponseDTO getProductById(@PathVariable
                                             Integer id){
        return service.getProductById(id);
    }
*/












    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })


    public ResponseEntity <ProductResponseDTO> getProductById(@PathVariable
                                                              Integer id){
        ProductResponseDTO response =service.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    // Update Product
   /*
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {

        return service.updateProduct(id, product);
    }



    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductRequestDTO dto) {

        return service.updateProduct(id, dto);
    }


    */
// put mapping with status code return Response Entity ......


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductRequestDTO dto) {

        ProductResponseDTO response = service.updateProduct(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





    //partially updated....

    @PatchMapping("/{id}")
    public ProductResponseDTO updatePartialProduct(
            @PathVariable Integer id,
            @RequestBody ProductRequestDTO dto) {

        return service.updatePartialProduct(id, dto);
    }


    // Delete Product
  /*
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }


   */
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductI(@PathVariable Integer id){
        service.deleteProduct((id));
        return ResponseEntity.noContent().build();
}



}
