package com.sistema.GestionProductos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.GestionProductos.Model.Product;
import com.sistema.GestionProductos.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> listAllProducts(){
        try {
            return ResponseEntity.ok(productService.listAllProd());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error interno."+ e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveNewProduct(@Valid @RequestBody Product pro, BindingResult result){
        try {
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
            }
            Product newProduct = productService.saveProduct(pro);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al guardar" + e.getMessage());
        }
    }

    @DeleteMapping("/{name}") //Tener ojo con la direccion a ocupar, tuve problemas en postman porque buscaba por id un atributo name. 
    public ResponseEntity<?> deleteProduct(@PathVariable String name){
        try {
            if(productService.deletProduct(name)){
                return ResponseEntity.ok("delete");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No Encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al eliminar");
        }
    }

}
