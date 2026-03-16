package ejemplo.trabajo.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controller{

    @GetMapping("/hola")
    public String holmundo(){
        return "hola mundo";
    }


}