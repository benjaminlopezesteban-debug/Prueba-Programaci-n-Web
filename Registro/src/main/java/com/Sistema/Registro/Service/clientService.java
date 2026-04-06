package com.Sistema.Registro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema.Registro.Model.Cliente;
import com.Sistema.Registro.Repository.clienteRepository;


@Service
public class clientService {

    @Autowired
    private clienteRepository clientRepository;
/* *
    * Muestra todos los clientes registrados, permite ver más de un registro con la misma id
*/
    public List<Cliente> listAllClients(){
        try {
            return clientRepository.listAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los clientes" + e.getMessage());

        }
    }

    public Cliente findClientServ(int id){
        return clientRepository.findClient(id);
    }

    public List<Cliente> findClienteByIdService(int id){
        try {
            return clientRepository.findClientId(id); //manera mas eficiente
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar al cliente:  " + id  + e.getMessage());
    }
    }

    public Cliente agregarClienteServ(Cliente Cli){
        try {
            return clientRepository.saveClient2(Cli);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el cliente: "+ e.getMessage());
        }
    }

    public boolean deletClientServ(int id){
        try {
            return clientRepository.deletedClientById(id);
        } catch (Exception e) {
            throw new RuntimeException("error al eliminar el cliente"+ e.getMessage());
        }
    }


}
