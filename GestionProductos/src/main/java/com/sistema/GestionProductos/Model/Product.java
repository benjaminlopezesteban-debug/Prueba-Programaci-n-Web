package com.sistema.GestionProductos.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {

    @NotBlank(message="Debe de registrar un nombre.")
    private String name;
    @NotNull(message="Debe de registrar un precio.")
    private int price;
    @NotBlank(message="Debe registrar una categoria.")
    private String categori;
}
