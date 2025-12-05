package umustcoup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UMustCoup {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Jugador> listaJugadores = new ArrayList<>();

        listaJugadores.add(new Jugador("Milton"));
        listaJugadores.add(new Jugador("Mango"));
        listaJugadores.add(new Jugador("Arturo"));

        EstadoDelJuego juego = new EstadoDelJuego(listaJugadores);

        while (true) {
            Jugador jugador = juego.obtenerJugadorActual();
            System.out.println("Turno de " + jugador);

            System.out.println("Acciones:");
            System.out.println("1. Ingreso");
            System.out.println("2. Ayuda Extrangera");
            System.out.println("3. Golpe");
            System.out.println("4. Impuestos");
            System.out.println("5. Asesinato");
            System.out.println("6. Extorsion");
            System.out.println("7. Cambio");
            int opcion = sc.nextInt();
            Accion accion = new Accion(jugador);
            String objetivo = "tonto";
                    int carta = 0; //0 o 1
            switch (opcion) {
                
                case 1:
                    //no contraatacable
                    accion.ingreso();
                    break;
                case 2:
                    //contraatacable por duque
                    accion.ayudaExtrangera();
                    break;
                case 3:
                    //no contraatacable
                    accion.golpe(new Jugador(objetivo),carta);
                    break;
                case 4:
                    //contraatacable
                    //tiempo de espera a respuesta
                    accion.impuestos();
                    break;
                case 5:
                    //contraatacable
                    accion.asesinato(new Jugador(objetivo),carta);
                    break;
                case 6:
                    //contraatacable
                    accion.extorision(new Jugador(objetivo));
                    break;
                case 7:
                    //contraatacable
                    accion.cambio();
                    break;
                default:
                    //opcion no valida

            }

            juego.siguienteTurno();
        }
    }
}
