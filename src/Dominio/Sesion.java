package Dominio;
public class Sesion {
private String nombre;
private String contra;
    public Sesion() {
    }

    public Sesion(String nombre, String contra) {
        this.nombre = nombre;
        this.contra = contra;
    }

    public String obtenerContra() {
        return contra;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void asignarContra(String contra) {
        this.contra = contra;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + contra;
    }

}
