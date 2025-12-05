package umustcoup;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private int monedas;
    private List<Carta> cartas = new ArrayList();
    private boolean conVida = true;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.monedas = 2;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerMonedas() {
        return monedas;
    }

    public void agregarMonedas(int monedas) {
        this.monedas += monedas;
    }

    public void quitarMonedas(int monedas) {
        this.monedas -= monedas;
    }

    public List<Carta> obtenerCartas() {
        return cartas;
    }

    public boolean estaVivo() {
        return this.conVida;
    }

    public void matar() {
        this.conVida = false;
    }

    @Override
    public String toString() {
        return nombre + " : " + monedas + " Monedas, " + cartas + " Estado: " + (conVida?"Vivo":"Muerto");
    }

}
