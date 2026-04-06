package com.sistema.tareas.Controller;

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

import com.sistema.tareas.Model.Tarea;
import com.sistema.tareas.Service.TaskService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public ResponseEntity<?> listAllTaks() {
        try {
            return ResponseEntity.ok(taskService.listAllTask());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error interno." + e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> saveNewTask(@Valid @RequestBody Tarea task, BindingResult result) {
        try {
            if(result.hasErrors()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
            }
            Tarea newTask = taskService.saveTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al guardar" +e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deteledTask(@PathVariable int id){
        try {
             if(taskService.deleteTask(id)){
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

