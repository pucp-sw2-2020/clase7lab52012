package pe.pucp.sw2.clase7lab52012.entity;

import javax.persistence.*;

@Entity
@Table(name = "juegos")
public class Juego {
    @Id
    private int idjuego;
    private String nombre;
    private String descripcion;
    private Double precio;

    @ManyToOne
    @JoinColumn("idgenero")
    private Genero genero;

    @ManyToOne
    @JoinColumn("ideditora")
    private Editor editor;

}
