package com.sistema.tareas.Model;

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
public class Tarea {

    @NotNull(message="Tiene que llevar un id")
    private int id; 
    @NotBlank(message="Debe ingresar una fecha de tarea")
    private String taskDate;
    @NotBlank(message="Debe de tener una descripcion")
    private String taskDescription;
    @NotNull(message="Debe de tener una situación")
    private boolean isComplete;


}
