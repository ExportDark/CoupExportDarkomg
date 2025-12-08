package Dominio;

import Servidor.UnCliente;
import umustcoup.EstadoDelJuego;
import umustcoup.Jugador;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nombre;
    private List<UnCliente> miembros = new ArrayList<>();
    private EstadoDelJuego juego;
    private boolean juegoIniciado = false;

    public Sala(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void agregarMiembro(UnCliente cliente){
        miembros.add(cliente);
        broadcast("Sala > " + cliente.getId() + " se ha unido.");
    }

    public void eliminarMiembro(UnCliente cliente){
        miembros.remove(cliente);
        broadcast("Sala > " + cliente.getId() + " ha salido.");
    }

    public List<UnCliente> obtenerMiembros(){
        return miembros;
    }

    public void iniciarPartida() {
        if (miembros.size() < 3) {
            broadcast("Servidor > Se necesitan al menos 3 jugadores para iniciar.");
            return;
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (UnCliente cliente : miembros) {
            jugadores.add(new Jugador(cliente.getId()));
        }

        this.juego = new EstadoDelJuego(jugadores);
        this.juegoIniciado = true;

        broadcast("\n¡LA PARTIDA HA INICIADO!");
        anunciarTurno();
    }

    public EstadoDelJuego obtenerJuego() {
        return juego;
    }

    public boolean estaIniciada() {
        return juegoIniciado;
    }

    public void anunciarTurno() {
        if (juego != null) {
            Jugador actual = juego.obtenerJugadorActual();

            StringBuilder menu = new StringBuilder();
            menu.append("\n========================================\n");
            menu.append("       TURNO DE: ").append(actual.obtenerNombre()).append("\n");
            menu.append("========================================\n");
            menu.append("Estado: ").append(actual).append("\n\n");
            menu.append("ACCIONES DISPONIBLES (Escribe el comando):\n");
            menu.append(" 1. Ingreso (1 moneda)           -> /jugar 1\n");
            menu.append(" 2. Ayuda Extranjera (2 monedas) -> /jugar 2\n");
            menu.append(" 3. Golpe (7 monedas)            -> /jugar 3 [nombre_objetivo]\n");
            menu.append(" 4. Impuestos (3 monedas)        -> /jugar 4\n");
            menu.append(" 5. Asesinato (3 monedas)        -> /jugar 5 [nombre_objetivo]\n");
            menu.append(" 6. Extorsión (Robar 2 monedas)  -> /jugar 6 [nombre_objetivo]\n");
            menu.append(" 7. Cambio (Cartas)              -> /jugar 7\n");
            menu.append("========================================\n");

            broadcast(menu.toString());
        }
    }

    public void broadcast(String mensaje) {
        for (UnCliente cliente : miembros) {
            try {
                cliente.salida().writeUTF(mensaje);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + miembros.size() + " jugadores)";
    }
}