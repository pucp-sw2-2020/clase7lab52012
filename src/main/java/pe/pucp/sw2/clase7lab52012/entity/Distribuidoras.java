package pe.pucp.sw2.clase7lab52012.entity;
@Entity
@Table(name="distribuidoras")
public class Distribuidoras {
    @Id
    private int iddistribuidora;
    private String nombre;
    private String descripcion;

    public int getIddistribuidora() {
        return iddistribuidora;
    }

    public void setIddistribuidora(int iddistribuidora) {
        this.iddistribuidora = iddistribuidora;
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
