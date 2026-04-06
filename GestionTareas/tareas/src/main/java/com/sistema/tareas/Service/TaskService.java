
package com.sistema.tareas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.tareas.Model.Tarea;
import com.sistema.tareas.Repository.TaskRepository;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //listar desde repositorio
    public List<?> listAllTask(){
        try {
            return taskRepository.listAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las tareas"+ e.getMessage());
        }
    }

    //agregar un tarea
    public Tarea saveTask(Tarea task){
        try {
            return taskRepository.saveTarea(task);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la tarea"+ e.getMessage());
        }
    }

    //eliminar tarea
    public boolean deleteTask(int id){
        try {
            return taskRepository.deletedTaskById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la tarea" + e.getMessage());
        }
    }

    //Marcar como tarea terminadad
    public Optional<Tarea> updateTaskStatus(int id, boolean isCompleted){
        try {
            return taskRepository.updateTarea(id, isCompleted);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la tarea" + e.getMessage());
        }

    }



}
