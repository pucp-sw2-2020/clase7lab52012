package pe.pucp.sw2.clase7lab52012.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pe.pucp.sw2.clase7lab52012.entity.Usuario;
import pe.pucp.sw2.clase7lab52012.repository.UsuarioRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/redirectByRol")
    public String redireccionarPorRol(Authentication auth, HttpSession httpSession) {

        ArrayList<String> roles = new ArrayList<>();

        for (GrantedAuthority g : auth.getAuthorities()) {
            roles.add(g.getAuthority());
        }

        Usuario u = usuarioRepository.findByCorreo(auth.getName());
        httpSession.setAttribute("usuario",u);

        if(roles.contains("user") && !roles.contains("admin")){
            return "redirect:/user";
        }else{
            return "redirect:/admin";
        }

    }
}
