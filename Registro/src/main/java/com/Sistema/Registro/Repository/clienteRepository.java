package com.Sistema.Registro.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Sistema.Registro.Model.Cliente;

@Repository
public class clienteRepository {

    private List<Cliente> clientList = new ArrayList<>();
    private int nextId = 1;

    //forma para agregar clientes desde codigo 
    public clienteRepository(){
        clientList.add(Cliente.builder()
        .id(nextId++)
        .nombre("Matias Moncad Lira Fernandez")
        .correo("matiaslira@gmail.com")
        .edad(22)
        .build());
    
        clientList.add(Cliente.builder()
        .id(nextId++)
        .nombre("Francisco Fernando Cruz Sequera")
        .correo("Franciscofernandez@gmail.com")
        .edad(68)
        .build());
    }

    //devolver todos los clientes
    public List<Cliente> listAll(){
        return clientList;
    }
    //buscar un cliente
    public Cliente findClient(int id){
        for (Cliente x: clientList){
            if (x.getId() == id)
                return x;
        }
        return null; //No es buena práctica que retone null
        //podria solucionarse con un optional

    }
    //metodo 2.
    //Que hace, retorna una lista, puede entregar más elementos sobre todo si la id esta duplicada.
    //este metodo me parece que puede ser bueno para entregar registros de un mismo id. 
    //Stream funciona para grandes cantidades de datos, y permite el procesamiento paralelo
    //permite hacer operaciones una vez iniciado, en forma de flujo de datos y no como un bluque
    public List<Cliente> findClientId(int id){
        return clientList.stream() //recorre la coleccion. Convierte una lista en un flujo de datos
        .filter(p -> p.getId() == id)
        .toList(); 
    }


    //AGREGAR CLIENTE
    public Cliente saveClient(Cliente cli){

        for(Cliente x : clientList){
            if(x.getId() == cli.getId()){
                return null;
            }
            clientList.add(cli);
        }
        return cli;
        
    }
    //metodo 2
    public Cliente saveClient2(Cliente cli){
        cli.setId(nextId++);
        clientList.add(cli);
            return cli;
    }



    //ELIMINAR CLIENTE
    //excelente. No te define si elimino uno o más registro con el mismo id solo retorna 0 , 1
    public boolean deletedClientById(int id){ //el mejor metodo
        return clientList.removeIf(x -> x.getId() == id);
    }
    public boolean deletedClient3(int id){
        for(int i = 0; i < clientList.size(); i++){
            if(clientList.get(i).getId() == id){
                clientList.remove(i);
                return true;
            }
        }
        return false;
    }   
    //buscar cliente por otro atributo
    public List<Cliente> findByName(String name){
        return clientList.stream()
        .filter(p -> p.getNombre().equalsIgnoreCase(name))
        .toList();
    }


    //ACTUALIZAR CLIENTE
    //metodo 1
    public Cliente updateClient(int id, Cliente cliupdate){
        Cliente clienteActualizar = clientList.stream()
        .filter(clienteID -> cliupdate.getId() == id)
        .findFirst()
        .orElse(null);

        if(clienteActualizar != null){
            clienteActualizar.setNombre(cliupdate.getNombre());
            clienteActualizar.setCorreo(cliupdate.getCorreo());
            clienteActualizar.setEdad(cliupdate.getEdad());
            return clienteActualizar;
        }
        return null;

    }




    //Falta un metodo para entrenar CRUD con boolenos y saber si son verdaderos o falsos








}
