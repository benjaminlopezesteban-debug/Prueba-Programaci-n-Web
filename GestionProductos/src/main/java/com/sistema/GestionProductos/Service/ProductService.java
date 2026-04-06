package com.sistema.GestionProductos.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.GestionProductos.Model.Product;
import com.sistema.GestionProductos.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //listar desde repositorio
    public List<?> listAllProd(){
        try {
            return productRepository.listAllProducts();
            
        } catch (Exception e) {
            throw new RuntimeException("Error al enlistar los productos" + e.getMessage());
        }
    }

    //agregar producto
    public Product saveProduct(Product Prod){
        try {
            return productRepository.saveProduct(Prod);
            
        } catch (Exception e) {
            throw new  RuntimeException("Error al agregar el nuevo producto" + e.getMessage());
        }
    }

    //eliminar producto
    public boolean deletProduct(String name){
        try {
            return productRepository.deleteProducts(name);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto"+ e.getMessage());
        }
    }


}
