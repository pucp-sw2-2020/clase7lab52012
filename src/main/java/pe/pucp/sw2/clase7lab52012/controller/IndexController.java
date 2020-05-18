package pe.pucp.sw2.clase7lab52012.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"","/"})
    public String index(){
        return "index";
    }
}
