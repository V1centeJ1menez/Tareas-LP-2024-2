package usm.vjimenez.nojavasky.hud;

import usm.vjimenez.nojavasky.hud.estados.*; // Cambiamos a EstadoMenuPrincipal
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;

public class GameStateManager {

    //*************************************************** ATRIBUTOS ***************************************************//

    private GameState estadoActual;

    //*************************************************** CONSTRUCTOR ***************************************************//

    public GameStateManager() {
        // Comenzamos en el menú principal
        estadoActual = new EstadoMainMenu(this, new Jugador(), new Nave(), new MapaGalactico());
    }

    //*************************************************** GETTERS ***************************************************//

    public GameState getEstadoActual() {
        return estadoActual;
    }
    //*************************************************** SETTERS ***************************************************//

    public void setEstadoActual(GameState estadoActual) {
        this.estadoActual = estadoActual;
    }
    //*************************************************** METODOS ***************************************************//

    // Cambia el estado actual del juego
    public void cambiarEstado(GameState nuevoEstado) {
        /**
         * Cambia el estado actual del juego a un nuevo estado proporcionado.
         * 
         * Este método permite cambiar el estado del juego a uno nuevo, 
         * actualizando la variable que mantiene el estado actual y 
         * mostrando las opciones disponibles en el nuevo estado.
         * 
         * @param nuevoEstado El nuevo estado al que se cambiará. 
         *                    Debe ser una instancia de GameState.
         */
        this.estadoActual = nuevoEstado; // Cambia el estado actual
        estadoActual.mostrarOpciones(); // Muestra las opciones del nuevo estado
    }

    // Método para manejar la ejecución de los estados
    public void actualizar() {
        /**
         * Maneja la ejecución de los estados del juego.
         * 
         * Este método verifica si hay un estado actual definido y, de ser así,
         * muestra las opciones disponibles en dicho estado. 
         * Es útil para mantener la interacción del usuario en función del 
         * estado actual del juego.
         */
        if (estadoActual != null) {
            estadoActual.mostrarOpciones(); // Muestra opciones dependiendo del estado actual
        }
    }

    // Método para cerrar el Scanner al final del juego
    public void cerrarScanner() {
        /**
         * Cierra el objeto Scanner al final del juego.
         * 
         * Este método verifica si hay un estado actual definido y, de ser así,
         * llama al método `cerrarScanner` del estado actual para asegurarse de que
         * se cierren adecuadamente todos los recursos asociados al Scanner.
         * Esto ayuda a prevenir posibles fugas de memoria y a liberar recursos
         * al finalizar la ejecución del juego.
         */
        if (estadoActual != null) {
            estadoActual.cerrarScanner(); // Cierra el Scanner
        }
    }
}
