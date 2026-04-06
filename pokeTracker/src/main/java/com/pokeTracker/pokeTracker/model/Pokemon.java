package com.pokeTracker.pokeTracker.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //AYUDA A NO REALIZAR COSA ESPECIFICAR

public class Pokemon {

    @NotNull(message="el id no puede estar en blanco") //exclusiva notación para numero
    private long id; //control d para seleccionar similares terminos *tip

    @NotBlank(message="the name is obligatory")
    private String name;
    @NotBlank(message="the tipe is obligatory")
    private String tipe;

    @NotNull(message="the level is obligatory")
    @Min(value = 1, message="el nivel debe ser mayor a 0") //cursos sobre la notacion demuestra la documentación, ej. aqui dice que no soporta double o float
    //declara el valor minimo y el mensaje que recibe si no cumple la condicion, tambien existen otras notacioes de validación. 
    private int Level; 

    @NotBlank(message="the catchDate is obligatory")
    private String catchDate;
    @NotNull(message="if is shiny is obligatory")
    private boolean isShiny; //booleano acostumbrarse a proponer boleanos como pregunta para responder con true o false al valor del booleano
    @NotBlank(message="the gender is obligatory")
    private String gender;
    


    //requerimientos o validaciones
    //el nivel debe ser mayor o igual a 1. 
    //get pokemon
    //post agregar pokemo
    //delete poke
    //put pokemon (actualizar)
    //get shiny
}   
