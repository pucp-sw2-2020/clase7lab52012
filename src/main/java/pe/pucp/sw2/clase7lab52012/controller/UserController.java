package pe.pucp.sw2.clase7lab52012.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String indexUser(){
        return "user/index";
    }
}
