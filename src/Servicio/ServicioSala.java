package Servicio;

import Dominio.Sala;
import Servidor.UnCliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioSala {
    
    private List<Sala> salas = new ArrayList<>();
    private UnCliente cliente;
    private int numSala;
    
    public ServicioSala(UnCliente cliente) {
        this.cliente = cliente;
    }
    
    public void crear(String nombreSala) {
        if (existeSala(nombreSala) != -1) {
            salas.add(new Sala(nombreSala));
        }
    }
    
    public void unirse(String nombreSala) {
        int numSala = existeSala(nombreSala);
        if (numSala != -1) {
            salas.get(numSala).agregarMiembro(cliente);
            this.numSala = numSala;
        }
    }
    
    public void ver() throws IOException {
        cliente.salida().writeUTF(salas.toString());
    }
    
    public void salir() {
        salas.get(numSala).eliminarMiembro(cliente);
    }

    
    public int existeSala(String nombreSala) {
        int contadorsito = 0;
        for (Sala salita : salas) {
            if (salita.obtenerNombre().equals(nombreSala)) {
                return contadorsito;
            }
            contadorsito++;
        }
        return -1;
    }
}
