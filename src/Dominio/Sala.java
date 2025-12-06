package Dominio;

import Servidor.UnCliente;
import java.util.List;

public class Sala {
private String nombre;
private List<UnCliente> miembros;

    public Sala(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return nombre;
    }
    
    public void agregarMiembro(UnCliente cliente){
        miembros.add(cliente);
    }
    public void eliminarMiembro(UnCliente cliente){
        miembros.remove(cliente);
    }
    public List<UnCliente> obtenerMiembros(){
        return miembros;
    }
    @Override
    public String toString() {
        return nombre + ", Miembros :" + miembros;
    }

    
}
