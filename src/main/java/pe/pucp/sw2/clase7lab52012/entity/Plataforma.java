package pe.pucp.sw2.clase7lab52012.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plataformas")
public class Plataforma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idplataforma;
    private String nombre;
    private String descripcion;

    public int getIdplataforma() {
        return idplataforma;
    }

    public void setIdplataforma(int idplataforma) {
        this.idplataforma = idplataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
