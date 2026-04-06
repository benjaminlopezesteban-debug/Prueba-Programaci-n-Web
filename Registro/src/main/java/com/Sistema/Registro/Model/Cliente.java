package com.Sistema.Registro.Model;

import jakarta.validation.constraints.Min;
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
public class Cliente {
    @NotNull
    private int id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String correo;
    @NotNull
    @Min(value = 17, message="Debe ser mayor de edad")
    private int edad;


}
