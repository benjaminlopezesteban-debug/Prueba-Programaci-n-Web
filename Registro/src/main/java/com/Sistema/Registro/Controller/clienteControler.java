package com.Sistema.Registro.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sistema.Registro.Model.Cliente;
import com.Sistema.Registro.Service.clientService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/api/v1/clientes")
public class clienteControler {

    @Autowired
    private clientService clientService;

    @GetMapping
    public ResponseEntity<?> lisAllCliente() {
        try {
            return ResponseEntity.ok(clientService.listAllClients());
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Error interno: " + e.getMessage());
    }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity findByID(@PathVariable int id) {
        try {
            List<Cliente> clients = clientService.findClienteByIdService(id); 
            if (!clients.isEmpty()) {
                return ResponseEntity.ok(clients);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body("No se encontro el cliente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error interno: " + e.getMessage());
        }
    }
    //Este metodo solo permite agregar 1 cliente a la vez( 1 objeto), si intentas por json meter varios objetos dara error
    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody Cliente cli, BindingResult result) {
        try {
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
            }
            Cliente newClient = clientService.agregarClienteServ(cli);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al guardar" +e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id){
        try {
            if(clientService.deletClientServ(id)){
                return ResponseEntity.ok("delete");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No encontrado");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al eliminar: " + e.getMessage());
        }
    }

}
