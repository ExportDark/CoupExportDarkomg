package umustcoup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoDelJuego {
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Carta> baraja = new ArrayList<>();
    private int jugadorActual = 0;

    public EstadoDelJuego(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        for (Rol rol : Rol.values()) {
            baraja.add(new Carta(rol));
            baraja.add(new Carta(rol));
            baraja.add(new Carta(rol));
        }

        Collections.shuffle(baraja);

        // Repartir 2 cartas por jugador
        for (Jugador jugador : jugadores) {
            jugador.agregarCarta(baraja.remove(0));
            jugador.agregarCarta(baraja.remove(0));
        }
    }

    public Jugador obtenerJugadorActual() {
        return jugadores.get(jugadorActual);
    }

    public void siguienteTurno() {
        do {
            jugadorActual = (jugadorActual + 1) % jugadores.size();
        } while (!jugadores.get(jugadorActual).estaVivo());
    }

    public List<Jugador> obtenerJugadores() { return jugadores; }
}
