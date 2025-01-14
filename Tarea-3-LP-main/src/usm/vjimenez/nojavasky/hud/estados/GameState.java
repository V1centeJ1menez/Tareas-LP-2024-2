package usm.vjimenez.nojavasky.hud.estados;

import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import usm.vjimenez.nojavasky.hud.*;

import java.util.Scanner;
import java.io.IOException;


public abstract class GameState {
    
    //*************************************************** ATRIBUTOS ***************************************************//

    private GameStateManager gsm;
    private Jugador jugador;
    private Nave nave;
    private MapaGalactico mapa;
    private Scanner scanner; // Declarar el Scanner aquí

    //*************************************************** CONSTRUCTOR ***************************************************//

    public GameState(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
        this.gsm = gsm;
        this.jugador = jugador;
        this.nave = nave;
        this.mapa = mapa;
        this.scanner = new Scanner(System.in); // Inicializar el Scanner en el constructor
    }

    //*************************************************** GETTERS ***************************************************//

    public GameStateManager getGsm() {
        return gsm;
    }
    public Jugador getJugador() {
        return jugador;
    }
    public MapaGalactico getMapa() {
        return mapa;
    }
    public Nave getNave() {
        return nave;
    }
    public Scanner getScanner() {
        return scanner;
    }

    //*************************************************** SETTERS ***************************************************//

    public void setGsm(GameStateManager gsm) {
        this.gsm = gsm;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public void setMapa(MapaGalactico mapa) {
        this.mapa = mapa;
    }
    public void setNave(Nave nave) {
        this.nave = nave;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    //*************************************************** METODOS ***************************************************//

    public void cambiarEstado(GameState nuevoEstado) {
        /**
         * Cambia el estado del juego a un nuevo estado especificado.
         *
         * @param nuevoEstado El nuevo estado al que se desea cambiar.
         *                    Debe ser una instancia de `GameState`.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        gsm.cambiarEstado(nuevoEstado);
    }

    /**
     * Método abstracto que debe ser implementado por las clases que heredan de esta.
     * Se encarga de mostrar las opciones disponibles para el jugador en el estado actual del juego.
     *
     * Este método no debe devolver ningún valor.
     */
    public abstract void mostrarOpciones(); 

    // Asegúrate de cerrar el Scanner al final de la aplicación
    public void cerrarScanner() {
        /**
         * Cierra el objeto Scanner utilizado para la entrada de datos.
         * 
         * Este método debe ser llamado al final de la aplicación para liberar
         * los recursos asociados con el objeto Scanner y evitar posibles fugas de memoria.
         * 
         * Nota: Una vez cerrado, el objeto Scanner no debe ser utilizado nuevamente.
         */
        scanner.close();
    }

    // Método para limpiar la consola (esto puede variar según el sistema operativo)
    public static void limpiarPantalla() {
        /**
         * Limpia la consola para proporcionar una salida más clara.
         * 
         * Este método detecta el sistema operativo en el que se está ejecutando la aplicación
         * y utiliza el comando adecuado para limpiar la consola. 
         * - En Windows, se utiliza el comando "cls".
         * - En sistemas Unix/Linux, se utiliza el comando "clear".
         * 
         * Si ocurre un error al intentar limpiar la pantalla, se mostrará un mensaje de error en la consola.
         */
        // Esto es un método simple para limpiar la consola
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la pantalla.");
        }
    }

    // Método para pausar y esperar una entrada del usuario
    public void pausaAnimacion(int milisegundos) {
        /**
         * Pausa la ejecución del programa durante un período de tiempo específico.
         * 
         * Este método utiliza el método `Thread.sleep` para detener la ejecución 
         * durante el tiempo especificado en milisegundos. Esto puede ser útil para 
         * crear efectos de animación o esperar antes de mostrar el siguiente mensaje 
         * en la consola.
         * 
         * @param milisegundos El tiempo en milisegundos que el programa debe esperar.
         *                    Un valor de 1000 ms equivale a 1 segundo.
         * @throws InterruptedException Si el hilo es interrumpido mientras está en espera.
         */
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void pausa() {
        /**
         * Pausa la ejecución del programa hasta que el usuario presione la tecla Enter.
         * 
         * Este método solicita al usuario que presione Enter para continuar. 
         * Esto puede ser útil para crear pausas en la ejecución del programa, 
         * permitiendo al usuario leer mensajes antes de continuar.
         * 
         * @throws IOException Si ocurre un error de entrada/salida durante la lectura.
         */
        try {
            System.out.println("Presiona Enter para continuar...");
            System.in.read(); // Espera a que el usuario presione Enter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
