package com.pokecatcher.pokecatcher.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals automáticamente
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los atributos
@Builder // Permite usar PokemonModel.builder()...
public class PokemonModel {

    private int id;

    @NotBlank(message = "El nombre es obligatorio") // No permite null ni string vacío
    private String nombre;

    @NotBlank(message = "El tipo es obligatorio") // No permite null ni string vacío
    private String tipo;

    @Min(value = 1, message = "El nivel mínimo es 1") // El valor mínimo permitido es 1
    private int nivel;

    @NotBlank(message = "El entrenador es obligatorio") // No permite null ni string vacío
    private String entrenador;

    @NotBlank(message = "La región es obligatoria") // No permite null ni string vacío
    private String region;

    private boolean legendario;
}