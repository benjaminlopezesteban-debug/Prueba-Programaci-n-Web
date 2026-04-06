package com.pokeTracker.pokeTracker.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pokeTracker.pokeTracker.model.Pokemon;

@Repository
public class pokemonRepository {

    private List<Pokemon> pokemonList = new ArrayList<Pokemon>();



    public pokemonRepository(){
        pokemonList.add(new Pokemon(
            1,
            "bolbasuir",
            "hojas",
            2,
            "macho",
            false,
            "fem"
        ));
    }

 



}
