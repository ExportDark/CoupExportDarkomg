package umustcoup;
public class Accion {
    private Jugador jugador;
    private Jugador jugadorObjetivo;

    public Accion(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Accion(Jugador jugador, Jugador jugadorObjetivo) {
        this.jugador = jugador;
        this.jugadorObjetivo = jugadorObjetivo;
    }
    
    //Acciones generales
        public void ingreso(){
            jugador.agregarMonedas(1);
        }

        public void ayudaExtrangera(){
            jugador.agregarMonedas(2);
        }

        public void golpe(Jugador jugadorObjetivo, int carta){

        }
    //Accion personajes
        //Duque
        public void  impuestos(){
            jugador.agregarMonedas(3);
        }

        //Asesina
        public void asesinato(Jugador jugadorObjetivo, int carta){

        }

        //Capitan
        public void extorision(Jugador jugadorObjetivo){
        }

        public void cambio(){

        }
    //Contraataques
        public void bloquearAyudaExtranjera(){
            
        }
        public void bloquearAsesinato(){
            
        }
        public void bloquearExtorsion(){
            
        }
}
