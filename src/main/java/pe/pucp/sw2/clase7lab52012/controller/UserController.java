package pe.pucp.sw2.clase7lab52012.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.pucp.sw2.clase7lab52012.entity.Juego;
import pe.pucp.sw2.clase7lab52012.entity.Usuario;
import pe.pucp.sw2.clase7lab52012.repository.JuegoRepository;
import pe.pucp.sw2.clase7lab52012.repository.UsuarioRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    JuegoRepository juegoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = {"","/"})
    public String indexUser(){
        return "user/index";
    }

    @GetMapping("/misjuegos")
    public String listaMisJuegos(Model model, HttpSession session){

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        Optional<Usuario> usuarioConJuegosOpt = usuarioRepository.findById(usuarioLogueado.getIdusuario());
        Usuario usuarioConJuegos = usuarioConJuegosOpt.get();

        model.addAttribute("listaJuegos",usuarioConJuegos.getListaJuegos());
        return "user/lista";
    }


    @GetMapping("/tienda")
    public String tienda(Model model, HttpSession session){

        Usuario u = (Usuario) session.getAttribute("usuario");

        List<Juego> juegos = juegoRepository.listaJuegosQueNoTieneUsuario(u.getIdusuario());

        List<Juego> juegosEnSession = (ArrayList<Juego>) session.getAttribute("juegosCarritoDeCompras");

        List<Juego> juegosEnTienda = new ArrayList<>();

        for(Juego j : juegos){
            boolean juegoExisteSesion = false;
            for(Juego js : juegosEnSession){
                if(j.getIdjuego() == js.getIdjuego()){
                    juegoExisteSesion = true;
                    break;
                }
            }
            if(!juegoExisteSesion){
                juegosEnTienda.add(j);
            }
        }

        model.addAttribute("listaJuegos",juegosEnTienda);
        return "user/tienda";
    }

    @GetMapping("/anadirCarrito")
    public String anadirCarrito(@RequestParam("id") int idJuego, HttpSession session){
        ArrayList<Juego> juegoCarrito = (ArrayList<Juego>) session.getAttribute("juegosCarritoDeCompras");
        Optional<Juego> juego = juegoRepository.findById(idJuego);
        juegoCarrito.add(juego.get());
        session.setAttribute("juegosCarritoDeCompras",juegoCarrito);
        return "redirect:/user/tienda";
    }

    @GetMapping("/comprar")
    public String preCompraJuegos(HttpSession session){
        return "user/compra";
    }

    @PostMapping("/comprar")
    public String comprarJuegos(HttpSession session){

        List<Juego> juegoCarrito = (ArrayList<Juego>) session.getAttribute("juegosCarritoDeCompras");

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        Optional<Usuario> usuarioConJuegosOpt = usuarioRepository.findById(usuarioLogueado.getIdusuario());
        Usuario usuarioConJuegos = usuarioConJuegosOpt.get();
        List<Juego> juegosUsuario = usuarioConJuegos.getListaJuegos();

        juegoCarrito.addAll(juegosUsuario);

        usuarioLogueado.setListaJuegos(juegoCarrito);

        usuarioRepository.save(usuarioLogueado);

        session.setAttribute("juegosCarritoDeCompras",new ArrayList<Juego>());

        return "redirect:/user/misjuegos";
    }
}
