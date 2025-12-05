package Servicio;

import Servidor.UnCliente;
import java.io.IOException;

public class Mensaje {

    private UnCliente cliente;

    public Mensaje(UnCliente cliente) {
        this.cliente = cliente;
    }

    public void procesarMensaje(String mensaje) throws IOException {
        if (mensaje.startsWith("/")) {
            String[]partes = mensaje.split(" ");
            switch (partes[0]) {
                case "/sala":
                    new ServicioSala(cliente).unirse(partes[1]);
                    break;
                default:
                    throw new AssertionError();
            }
            return;
        }
        
        //broadcast
        for(UnCliente cliente : Servidor.ServidorMulti.clientes.values()){
            cliente.salida().writeUTF(mensaje);
        }
    }

}
