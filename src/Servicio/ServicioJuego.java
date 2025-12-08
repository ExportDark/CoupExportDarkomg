package Servicio;

import Dominio.Sala;
import Servidor.UnCliente;
import umustcoup.Accion;
import umustcoup.EstadoDelJuego;
import umustcoup.Jugador;

public class ServicioJuego {

    public void procesarJugada(UnCliente cliente, String[] partes, Sala sala) {
        EstadoDelJuego juego = sala.obtenerJuego();
        Jugador jugadorActual = juego.obtenerJugadorActual();

        // Validar turno
        if (!jugadorActual.obtenerNombre().equals(cliente.getId())) {
            try { cliente.salida().writeUTF(">> NO ES TU TURNO. Espera a " + jugadorActual.obtenerNombre()); } catch(Exception e){}
            return;
        }

        // Parsear opción
        int opcion = 0;
        try {
            opcion = Integer.parseInt(partes[1]);
        } catch (Exception e) {
            try { cliente.salida().writeUTF(">> Comando inválido. Ejemplo: /jugar 1"); } catch(Exception ex){}
            return;
        }

        // Buscar objetivo
        String nombreObjetivo = (partes.length > 2) ? partes[2] : "";
        Jugador objetivo = null;
        for (Jugador j : juego.obtenerJugadores()) {
            if (j.obtenerNombre().equals(nombreObjetivo)) {
                objetivo = j;
                break;
            }
        }

        // Auto-selección de objetivo para pruebas si no se especifica
        if (objetivo == null && (opcion == 3 || opcion == 5 || opcion == 6)) {
            for(Jugador j : juego.obtenerJugadores()) {
                if(!j.equals(jugadorActual) && j.estaVivo()) {
                    objetivo = j;
                    break;
                }
            }
        }

        // Ejecutar Acción y obtener resultado
        Accion accion = new Accion(jugadorActual, objetivo, juego);
        String resultado = "";
        int cartaAfectada = 0;

        switch (opcion) {
            case 1: resultado = accion.ingreso(); break;
            case 2: resultado = accion.ayudaExtrangera(); break;
            case 3: resultado = (objetivo != null) ? accion.golpe(cartaAfectada) : "Falta objetivo (/jugar 3 [nombre])"; break;
            case 4: resultado = accion.impuestos(); break;
            case 5: resultado = (objetivo != null) ? accion.asesinato(cartaAfectada) : "Falta objetivo (/jugar 5 [nombre])"; break;
            case 6: resultado = (objetivo != null) ? accion.extorision() : "Falta objetivo (/jugar 6 [nombre])"; break;
            case 7: resultado = accion.cambio(); break;
            default: resultado = "Opción desconocida.";
        }

        // Notificar resultado
        if (resultado.startsWith("ERROR") || resultado.startsWith("Falta")) {
            // Si hubo error, avisamos solo al cliente y NO pasamos turno
            try { cliente.salida().writeUTF(">> " + resultado); } catch(Exception e){}
        } else {
            sala.broadcast("JUEGO > " + resultado);

            // Verificar si hay ganador
            int vivos = 0;
            String ganador = "";
            for(Jugador j : juego.obtenerJugadores()) {
                if(j.estaVivo()) {
                    vivos++;
                    ganador = j.obtenerNombre();
                }
            }

            if(vivos <= 1) {
                sala.broadcast("\n!!! JUEGO TERMINADO !!!");
                sala.broadcast("GANADOR: " + ganador);
            } else {
                juego.siguienteTurno();
                sala.anunciarTurno();
            }
        }
    }
}