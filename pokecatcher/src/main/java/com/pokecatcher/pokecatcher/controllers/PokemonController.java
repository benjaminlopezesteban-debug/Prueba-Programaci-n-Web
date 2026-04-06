package com.pokecatcher.pokecatcher.controllers;

import com.pokecatcher.pokecatcher.models.PokemonModel;
import com.pokecatcher.pokecatcher.services.PokemonService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que retorna JSON automáticamente
@RequestMapping("/api/v1/pokemones") // Ruta base del controlador
public class PokemonController {


    //recuerden siempre instanciar el servicio en el controlador
    // IMPORTANTE: LLAMEN A LA INSTANCIA NO A LA CLASE(Se difetencian por la mayuscula en la primera letra)
    private PokemonService pokemonService;



    @GetMapping // Endpoint GET para obtener todos
    public ResponseEntity<List<PokemonModel>> obtenerTodos() {

        //si pasan el mouse arriba de obtener todos veran el ejemplo de documentacion que deje en el service(no entra en esta prueba pero es buena practica)
        return ResponseEntity.ok(pokemonService.obtenerTodos());
    }

    //recuerden que la url aqui seria /api/v1/pokemones/1
   @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        try {
            List<PokemonModel> pokemon = pokemonService.obtenerPorId(id);

            //aqui estoy negando is empty para que entre al if solo cuando la lista NO sea vacia
            if (!pokemon.isEmpty()) {
                return ResponseEntity.ok(pokemon);
            }

            //si el if no entra, es porque esta vacia la lista entonces devuelvo el 204 NO CONTENT
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No encontrado");

        } catch (Exception e) { // Captura cualquier error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage()); // Retorna error 500
        }
    }

    @PostMapping // Endpoint POST para crear
    public ResponseEntity<?> guardar(@Valid @RequestBody PokemonModel pokemon, BindingResult result) {
        // @RequestBody convierte JSON(lo que ustedes envian del postman) a objeto
        // @Valid ejecuta las validaciones del model
        // BindingResult guarda el resultado de la validacion, se usa para ver si algo fallo o no en el if
        try {

            //aqui validamos el objeto result, que es de tipo bindingresult, luego leemos el mensaje con result.getFieldError().getDefaultMessage()
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
            }
            //si no hay error en el json de entrada, no entra al if y procede a guardar

            PokemonModel nuevo = pokemonService.guardar(pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")// Endpoint PUT para actualizar
    public ResponseEntity<?> actualizar(@PathVariable int id, @Valid @RequestBody PokemonModel pokemon, BindingResult result ) {
        // @RequestBody convierte JSON(lo que ustedes envian del postman) a objeto
        // @Valid ejecuta las validaciones del model
        // BindingResult guarda el resultado de la validacion, se usa para ver si algo fallo o no en el if
        try {
            /* 
            recuerden primero validar si los datos de entrada tienen error o no con BindingResult
            */
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
            }
            // si no encuentra errores en el objeto de entrada ahi recien continuan con el codigo


            PokemonModel actualizado = pokemonService.actualizar(id, pokemon);

            //si retorna null es porque no encontro el id del pokemon a actualizar
            if (actualizado != null) {
                return ResponseEntity.ok(actualizado);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No encontrado");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")// Endpoint DELETE
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            if (pokemonService.eliminar(id)) {
                return ResponseEntity.ok("Eliminado");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No encontrado");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar: " + e.getMessage());
        }
    }

    @GetMapping("/tipo/{tipo}")// Endpoint GET por tipo
    public ResponseEntity<?> buscarPorTipo(@PathVariable String tipo) {
        try {
            return ResponseEntity.ok(pokemonService.buscarPorTipo(tipo));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por tipo: " + e.getMessage());
        }
    }

    @GetMapping("/legendarios")// Endpoint GET solo legendarios
    public ResponseEntity<?> legendarios() {
        try {
            return ResponseEntity.ok(pokemonService.obtenerLegendarios());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener legendarios: " + e.getMessage());
        }
    }
}