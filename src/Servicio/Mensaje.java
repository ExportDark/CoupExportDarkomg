package Servicio;

import Servidor.UnCliente;
import Dominio.Sala;
import java.io.IOException;

public class Mensaje {

    private UnCliente cliente;

    public Mensaje(UnCliente cliente) {
        this.cliente = cliente;
    }

    public void procesarMensaje(String mensaje) throws IOException {
        if (mensaje.startsWith("/")) {
            String[] partes = mensaje.split(" ");
            ServicioSala servSala = new ServicioSala(cliente);

            switch (partes[0]) {
                case "/crear":
                    if(partes.length > 1) servSala.crear(partes[1]);
                    break;
                case "/unirse":
                    if(partes.length > 1) servSala.unirse(partes[1]);
                    break;
                case "/ver":
                    servSala.ver();
                    break;
                case "/iniciar":
                    Sala s = servSala.obtenerSalaDelCliente();
                    if (s != null) s.iniciarPartida();
                    else cliente.salida().writeUTF("No estás en una sala.");
                    break;
                case "/jugar":
                    Sala salaActual = servSala.obtenerSalaDelCliente();
                    if (salaActual != null && salaActual.estaIniciada()) {
                        new ServicioJuego().procesarJugada(cliente, partes, salaActual);
                    } else {
                        cliente.salida().writeUTF("No estás en una partida iniciada.");
                    }
                    break;
                case "/salir":
                    break;
                default:
                    cliente.salida().writeUTF("Comando desconocido.");
            }
            return;
        }

        Sala sala = new ServicioSala(cliente).obtenerSalaDelCliente();
        if (sala != null) {
            sala.broadcast(cliente.getId() + ": " + mensaje);
        } else {
            cliente.salida().writeUTF("No estás en ninguna sala. Usa /crear o /unirse.");
        }
    }
}