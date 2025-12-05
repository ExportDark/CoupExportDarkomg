package Servicio;

import Dominio.Sala;
import Servidor.UnCliente;
import java.util.ArrayList;
import java.util.List;

public class ServicioSala {

    private List<Sala> salas = new ArrayList<>();
    private UnCliente cliente;

    public ServicioSala(UnCliente cliente) {
        this.cliente = cliente;
    }

    public void crear(String nombreSala) {
        if (!existeSala(nombreSala)) {
            salas.add(new Sala(nombreSala));
        }
    }

    public void unirse(String nombreSala) {
        if (existeSala(nombreSala)) {
            
        }
    }

    public void salir() {

    }

    public boolean existeSala(String nombreSala) {
        return salas.contains(new Sala(nombreSala));
    }
}
