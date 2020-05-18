package pe.pucp.sw2.clase7lab52012.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.pucp.sw2.clase7lab52012.entity.Plataformas;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataformas,Integer> {
}
