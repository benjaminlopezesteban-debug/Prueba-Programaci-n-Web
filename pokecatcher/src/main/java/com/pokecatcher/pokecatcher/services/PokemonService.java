package com.pokecatcher.pokecatcher.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pokecatcher.pokecatcher.models.PokemonModel;
import com.pokecatcher.pokecatcher.repositories.PokemonRepository;

@Service // Marca esta clase como capa de lógica de negocio
public class PokemonService {

    private PokemonRepository pokemonRepository;

     //EJEMPLO DE DOCUMENTACION DE CODIGO, SI PONEN EL MOUSE ARRIBA DE LA 
    // LLAMADA A obtenerTodos EN EL CONTROLLER, VERAN ESTA INFORMACION
    /**
     * Obtiene todos los Pokémon almacenados en la colección.
     *
     * Este método consulta el repositorio y retorna la lista completa
     * de Pokémon registrados en memoria.
     *
     * @return lista de objetos PokemonModel
     * @throws RuntimeException si ocurre un error durante la consulta
     */
    public List<PokemonModel> obtenerTodos() {
        try {
            return pokemonRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pokemones: " + e.getMessage());
        }
    }

    public List<PokemonModel> obtenerPorId(int id) {
        try {
            return pokemonRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar pokemon: " + e.getMessage());
        }
    }

    public PokemonModel guardar(PokemonModel pokemon) {
        try {
            return pokemonRepository.save(pokemon);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar pokemon: " + e.getMessage());
        }
    }
    //EJEMPLO DE DOCUMENTACION DE CODIGO, SI PONEN EL MOUSE ARRIBA DE LA 
    // LLAMADA A actualizar EN EL CONTROLLER, VERAN ESTA INFORMACION
    /**
     * Actualiza un Pokémon existente en la lista según su id.
     *
     * @param id id del Pokémon que se desea actualizar
     * @param pokemon objeto PokemonModel con los nuevos datos
     * @return el Pokémon actualizado, o null si no se encuentra
     * @throws RuntimeException si ocurre un error durante la actualización
     */
    public PokemonModel actualizar(int id, PokemonModel pokemon) {
        try {
            return pokemonRepository.update(id, pokemon);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar pokemon: " + e.getMessage());
        }
    }

    public boolean eliminar(int id) {
        try {
            return pokemonRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar pokemon: " + e.getMessage());
        }
    }

    public List<PokemonModel> buscarPorTipo(String tipo) {
        try {
            return pokemonRepository.findByTipo(tipo);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por tipo: " + e.getMessage());
        }
    }

    public List<PokemonModel> obtenerLegendarios() {
        try {
            return pokemonRepository.findLegendarios();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener legendarios: " + e.getMessage());
        }
    }
}