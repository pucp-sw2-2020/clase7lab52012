package pe.pucp.sw2.clase7lab52012.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.pucp.sw2.clase7lab52012.entity.Juego;

import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {

    @Query(value = "select * from juegos where idjuego not in (select j.idjuego from juegos j \n" +
            "inner join juegosxusuario jxu on j.idjuego = jxu.idjuego\n" +
            "inner join usuarios u on jxu.idusuario = u.idusuario \n" +
            "where u.idusuario = ?1)", nativeQuery = true)
    List<Juego> listaJuegosQueNoTieneUsuario(int idusuario);
}
