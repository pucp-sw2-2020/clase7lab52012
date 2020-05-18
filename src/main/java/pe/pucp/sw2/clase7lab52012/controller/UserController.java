package pe.pucp.sw2.clase7lab52012.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.pucp.sw2.clase7lab52012.repository.JuegoRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    JuegoRepository juegoRepository;

    @GetMapping(value = {"","/"})
    public String indexUser(){
        return "user/index";
    }

    @GetMapping("/misjuegos")
    public String listaMisJuegos(Model model){
        model.addAttribute("listaJuegos",juegoRepository.findAll());
        return "user/lista";
    }
}
