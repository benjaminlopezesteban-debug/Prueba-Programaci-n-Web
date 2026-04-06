package  com.sistema.tareas.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sistema.tareas.Model.Tarea;

@Repository
public class TaskRepository {

    private static List<Tarea> taskList = new ArrayList<>();
    

    //Agregar 1 tarea
    public Tarea saveTarea(Tarea task){
        boolean existe = taskList.stream()
        .anyMatch(t -> t.getId() == task.getId());

            if(existe){
                return null;
            }

        taskList.add(task);
        return task;
    }

    //enlistar Tareas
    public List<Tarea> listAll(){
        return taskList;
    }

    //eliminar tarea
    public boolean deletedTaskById(int id){ //el mejor metodo
        return taskList.removeIf(x -> x.getId() == id);
    }

    //Marcar tarea como completada.

    /*public Optional<Tarea> updateTarea(int id, boolean isComplete) {
        return taskList.stream()
        .filter(t -> t.getId() == id) // Filtramos por el ID que recibimos
        .findFirst()                  // Buscamos el primero
        .map(tarea -> {               
            tarea.setComplete(isComplete);
            return tarea;
        });
        }*/
    
    public Optional<Tarea> updateTarea(int id, boolean isComplete) {
    return taskList.stream()
        .filter(t -> t.getId() == id)
        .findFirst()
        .map(tarea -> {               
            tarea.setComplete(isComplete); // <--- Usa el Setter: setComplete
            return tarea;
        });
    }

}
