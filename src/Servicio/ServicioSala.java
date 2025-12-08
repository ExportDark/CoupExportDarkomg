package Servicio;

import Dominio.Sala;
import Servidor.UnCliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioSala {

    // lista estática para que sea compartida por todos los hilos
    public static List<Sala> salas = new ArrayList<>();

    private UnCliente cliente;

    public ServicioSala(UnCliente cliente) {
        this.cliente = cliente;
    }

    public void crear(String nombreSala) throws IOException {
        if (existeSala(nombreSala) == -1) {
            Sala nuevaSala = new Sala(nombreSala);
            nuevaSala.agregarMiembro(cliente);
            salas.add(nuevaSala);
            cliente.salida().writeUTF("Sala " + nombreSala + " creada. Esperando jugadores...");
        } else {
            cliente.salida().writeUTF("Error: La sala ya existe.");
        }
    }

    public void unirse(String nombreSala) throws IOException {
        int indice = existeSala(nombreSala);
        if (indice != -1) {
            Sala s = salas.get(indice);
            s.agregarMiembro(cliente);
            cliente.salida().writeUTF("Te has unido a la sala " + nombreSala);
            s.broadcast(cliente.getId() + " se ha unido.");
        } else {
            cliente.salida().writeUTF("Error: Sala no encontrada.");
        }
    }

    public void ver() throws IOException {
        cliente.salida().writeUTF("Salas disponibles: " + salas.toString());
    }

    // encontrar la sala actual del cliente
    public Sala obtenerSalaDelCliente() {
        for (Sala s : salas) {
            if (s.obtenerMiembros().contains(cliente)) {
                return s;
            }
        }
        return null;
    }

    public int existeSala(String nombreSala) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).obtenerNombre().equals(nombreSala)) {
                return i;
            }
        }
        return -1;
    }
}