package ejemplo.trabajo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController  //es una anotación de la libreria con el podemos crear un aplicación api rest sin no tener que hacer más codigo a mano
public class controller{ //Esta notación asigna esta clase como un restcontroller ayudando a reducir código, se importa solo por el framwork

    @GetMapping("/hola")//mapea en la url de la ubicación un get, o un post. Entre parentesis se le asigna la url, y esa url se le asigna el getmapping
    public String holmundo(){ //en el get podemos solicitar un parametro, en este caso no lo solicitamos.  
        return "hola mundo";//podria retornar aqui un html o otro archivo, datos o acción. 
    }
}



//en aplicationpropieties de resource dentro de main, uno puede modificar el archivo .propperties. 
//ahi se debe ingresar server.port=8081. Para modeficiar el puerto para conexión.