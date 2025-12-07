package umustcoup;

import java.util.List;

public class Accion {
    private Jugador jugador;
    private Jugador jugadorObjetivo;
    private EstadoDelJuego estadoJuego;

    // Constructor de acción
    public Accion(Jugador jugador, EstadoDelJuego estadoJuego) {
        this.jugador = jugador;
        this.estadoJuego = estadoJuego;
    }

    // Constructor acción hacia otro jugador (para golpe, robo, asesinato)
    public Accion(Jugador jugador, Jugador jugadorObjetivo, EstadoDelJuego estadoJuego) {
        this.jugador = jugador;
        this.jugadorObjetivo = jugadorObjetivo;
        this.estadoJuego = estadoJuego;
    }

    public void ingreso(){
        System.out.println(jugador.obtenerNombre() + " toma ingreso.");
        jugador.agregarMonedas(1);
    }

    public void ayudaExtrangera(){
        System.out.println(jugador.obtenerNombre() + " intenta ayuda extranjera.");
        jugador.agregarMonedas(2);
    }

    public void golpe(int cartaAEliminar){
        if (jugador.obtenerMonedas() >= 7) {
            System.out.println(jugador.obtenerNombre() + " da un golpe a " + jugadorObjetivo.obtenerNombre());
            jugador.quitarMonedas(7);
            jugadorObjetivo.perderCarta(cartaAEliminar);
        } else {
            System.out.println("Error: No tienes 7 monedas.");
        }
    }

    // --- Acciones de Personaje ---

    // Duque
    public void impuestos(){
        System.out.println(jugador.obtenerNombre() + " cobra impuestos (Duque).");
        jugador.agregarMonedas(3);
    }

    // Asesina
    public void asesinato(int cartaAEliminar){
        if (jugador.obtenerMonedas() >= 3) {
            System.out.println(jugador.obtenerNombre() + " intenta asesinar a " + jugadorObjetivo.obtenerNombre());
            jugador.quitarMonedas(3);
            jugadorObjetivo.perderCarta(cartaAEliminar);
        } else {
            System.out.println("Fallo: No tienes 3 monedas.");
        }
    }

    // Capitán
    public void extorsion(){
        System.out.println(jugador.obtenerNombre() + " extorsiona a " + jugadorObjetivo.obtenerNombre());
        int monto = Math.min(2, jugadorObjetivo.obtenerMonedas());
        jugadorObjetivo.quitarMonedas(monto);
        jugador.agregarMonedas(monto);
    }

    // Embajador
    public void cambio(){
        System.out.println(jugador.obtenerNombre() + " realiza Cambio (Embajador).");
        // Robar 2 cartas
        Carta c1 = estadoJuego.tomarCartaDelMazo();
        Carta c2 = estadoJuego.tomarCartaDelMazo();

        if(c1 != null) jugador.agregarCarta(c1);
        if(c2 != null) jugador.agregarCarta(c2);

        devolverCartasExceso();
    }

    private void devolverCartasExceso() {
        List<Carta> mano = jugador.obtenerCartas();
        while (mano.size() > 2) {
            Carta c = mano.remove(mano.size() - 1);
            estadoJuego.devolverCartaAlMazo(c);
        }
        System.out.println("Cartas intercambiadas.");
    }

    public void bloquearAyudaExtranjera(){
        System.out.println("Bloqueo de ayuda extranjera realizado.");
    }
    public void bloquearAsesinato(){
        System.out.println("Bloqueo de asesinato realizado.");
    }
    public void bloquearExtorsion(){
        System.out.println("Bloqueo de extorsión realizado.");
    }
}