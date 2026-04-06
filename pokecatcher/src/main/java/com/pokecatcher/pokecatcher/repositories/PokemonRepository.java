package com.pokecatcher.pokecatcher.repositories;

import com.pokecatcher.pokecatcher.models.PokemonModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository // Marca esta clase como repositorio (acceso a datos)
public class PokemonRepository {

    private final List<PokemonModel> pokemonList = new ArrayList<>();
    private int nextId = 1;

    public PokemonRepository() {
        pokemonList.add(PokemonModel.builder()
                .id(nextId++)
                .nombre("Pikachu")
                .tipo("Electrico")
                .nivel(25)
                .entrenador("Ash")
                .region("Kanto")
                .legendario(false)
                .build());

        pokemonList.add(PokemonModel.builder()
                .id(nextId++)
                .nombre("Mewtwo")
                .tipo("Psiquico")
                .nivel(70)
                .entrenador("Giovanni")
                .region("Kanto")
                .legendario(true)
                .build());
    }

    public List<PokemonModel> findAll() {
        return pokemonList.stream() // Convierte la lista en stream para poder aplicar operaciones
                .sorted(Comparator.comparing(PokemonModel::getId)) // Ordena por id ascendente
                .toList(); // Convierte el stream nuevamente a lista
    }

    public List<PokemonModel> findById(int id) {
        return pokemonList.stream() // Stream para recorrer la colección 
                .filter(p -> p.getId() == id) // Filtra dejando solo el que coincide con el id
                .toList();// vuelve a empaquetar todo en una Lista IMPOTANTE AGREGARLO!!!!!
    }

    public PokemonModel save(PokemonModel nuevoPokemon) {
        nuevoPokemon.setId(nextId++); // Genera id autoincremental en el pokemon de entrada
        pokemonList.add(nuevoPokemon);
        return nuevoPokemon;
    }

    public PokemonModel update(int id, PokemonModel pokeActualizado) {

        /* EJEMPLO 1
        Recorro toda la lista con un for normal.
        Si encuentro un pokemon con el mismo id,
        actualizo sus datos y lo retorno.
        Si no lo encuentro, retorno null.
        */
        for (int i = 0; i < pokemonList.size(); i++) {

            if (pokemonList.get(i).getId() == id) {

                pokemonList.get(i).setNombre(pokeActualizado.getNombre());
                pokemonList.get(i).setTipo(pokeActualizado.getTipo());
                pokemonList.get(i).setNivel(pokeActualizado.getNivel());
                pokemonList.get(i).setEntrenador(pokeActualizado.getEntrenador());
                pokemonList.get(i).setRegion(pokeActualizado.getRegion());
                pokemonList.get(i).setLegendario(pokeActualizado.isLegendario());

                return pokemonList.get(i);
            }
        }               


        /* EJEMPLO 2
        stream() convierte la lista en un flujo de datos.
        filter() deja solo el pokemon cuyo id coincide.
        findFirst() toma el primero encontrado.
        */

        /* PokemonModel pokemonEncontrado = pokemonList.stream()
                .filter(pokemon -> pokemon.getId() == id)
                .findFirst()
                .orElse(null);

        if (pokemonEncontrado != null) {
            pokemonEncontrado.setNombre(pokeActualizado.getNombre());
            pokemonEncontrado.setTipo(pokeActualizado.getTipo());
            pokemonEncontrado.setNivel(pokeActualizado.getNivel());
            pokemonEncontrado.setEntrenador(pokeActualizado.getEntrenador());
            pokemonEncontrado.setRegion(pokeActualizado.getRegion());
            pokemonEncontrado.setLegendario(pokeActualizado.isLegendario());

            return pokemonEncontrado;
        }
 */
        return null;
    }

    public boolean deleteById(int id) {
        /* EJEMPLO 1
            funcion especial para listas, .removeIf dentro tiene una funcion de flecha,
        donde p es el elemento(todos los pokemonList de la lista) y 
        compara el id del elemento ( p.getId() ) con el id de entrada */
        return pokemonList.removeIf(p -> p.getId() == id); // Elimina el elemento si el id coincide, retorna true automaticamente


        /* EJEMPLO 2
        Recorro la lista por índice (i).
        Si encuentro el id, elimino usando remove(i)
        y retorno true.
        */
        /* for (int i = 0; i < pokemonList.size(); i++) {

            if (pokemonList.get(i).getId() == id) {
                pokemonList.remove(i); // Elimina por posición
                return true;
            }
        }

        return false; // No encontró el elemento, hay que retornar falso a mano
        */
    }

    public List<PokemonModel> findByTipo(String tipo) {
        return pokemonList.stream() // convierte la lista a un formato legible por java asincronamente
                .filter(p -> p.getTipo().equalsIgnoreCase(tipo)) // Filtra por tipo ignorando mayúsculas/minúsculas
                .sorted(Comparator.comparing(PokemonModel::getNivel).reversed()) // Ordena de mayor a menor nivel (no necesario en la prueba pero para que lo tengan en cuenta)
                .toList(); //recuerden siempre vovler a transformar el onjeto en una nueva lista
    }

    public List<PokemonModel> findLegendarios() {
        return pokemonList.stream()
                /* 
                fijense que tuve que convertir isLegendario solo 
                en legendario para que no diera errores la libreria de lombock
                ya que los booleanos los lee con isNombre en lugaar de getNombre
                y se rompe si el nombre parte con is xD(basura de liberia)
                */
                .filter(PokemonModel::isLegendario) // Filtra solo los que son legendarios (true)
                .sorted(Comparator.comparing(PokemonModel::getId)) // Ordena por id
                .toList();
    }
}