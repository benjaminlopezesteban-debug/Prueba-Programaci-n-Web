package com.sistema.GestionProductos.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sistema.GestionProductos.Model.Product;

@Repository
public class ProductRepository {

    private static List<Product> productsList = new ArrayList<>();

    //Agregar producto
    public Product saveProduct(Product prod){
        boolean exist = productsList.stream()
        .anyMatch(p -> p.getName().equalsIgnoreCase(prod.getName()));

        if(exist){
            return null;
        }

        productsList.add(prod);
        return prod;

    }

    //Enlistar productos
    public List<Product> listAllProducts(){
        return productsList;
    }

    //Eliminar productos
    public boolean deleteProducts(String name){
        return productsList.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }
}

