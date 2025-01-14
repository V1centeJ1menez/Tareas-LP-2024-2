package usm.vjimenez.nojavasky.hud.estados;

import java.util.Scanner;
import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;

public class EstadoMainMenu extends GameState {

    //*************************************************** CONSTRUCTOR ***************************************************//
    public EstadoMainMenu(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
        super(gsm, jugador, nave, mapa);
    }
    Nave nave = this.getNave();
    GameStateManager gsm = this.getGsm();
    Jugador jugador = this.getJugador();
    MapaGalactico mapa = this.getMapa();
    Scanner scanner = this.getScanner();

    //*************************************************** METODOS ***************************************************//
    @Override
    /**
     * Muestra las opciones disponibles para el jugador en el menú principal del juego.
     * Este método presenta al jugador tres opciones: empezar el viaje, cambiar el nombre
     * y salir del juego. Se ejecuta en un bucle hasta que el jugador elige una opción válida
     * o decide salir.
     *
     * Parámetros:
     *   - No recibe parámetros.
     *
     * Devuelve:
     *   - No devuelve ningún valor.
     */
    public void mostrarOpciones() {
        limpiarPantalla();
        // Mostrar arte ASCII y opciones
        mostrarArteASCII();
        
        // No es necesario crear un nuevo Scanner, usar el de la superclase
        pedirNombreJugador();

        boolean jugando = true;
        while (jugando) {
            System.out.println(jugador.getNombreJugador() + " ¿Listo para empezar el viaje?");
            System.out.println("[1] Empezar viaje");
            System.out.println("[2] Cambiar nombre");
            System.out.println("[3] Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = obtenerOpcion(scanner); // Método para obtener una opción válida

            switch (opcion) {
                case 1:
                    // Cambiar al estado de órbita
                    Nave nave = new Nave();  // Inicializar la nave
                    MapaGalactico mapaGalactico = new MapaGalactico(); // Inicializar el mapa
                    mapaGalactico.generadorPlaneta(); // Generar el primer planeta
                    cambiarEstado(new EstadoOrbita(gsm, jugador, nave, mapaGalactico));
                    jugando = false; // Salir del bucle después de cambiar de estado
                    break;
                case 2:
                    limpiarPantalla();
                    mostrarArteASCII();
                    pedirNombreJugador(); // Pedir el nombre de nuevo sin reiniciar todo
                    break;

                case 3:
                    System.out.println("Saliendo del juego...");
                    gsm.cerrarScanner(); // Asegúrate de cerrar el scanner al salir
                    System.exit(0);
                    break;

                default:
                    limpiarPantalla();
                    System.out.println("Opción no válida, intentemos de nuevo.\n");
                    break;
            }
        }
    }

    // Método para pedir y establecer el nombre del jugador
    private void pedirNombreJugador() {
        /**
         * Pide al usuario que ingrese su nombre y lo establece en el objeto jugador.
         * Este método solicita al jugador que introduzca su nombre a través de la consola
         * y luego lo guarda en la instancia correspondiente del jugador.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.print("Por favor, ingresa tu nombre: ");
        String nombreJugador = scanner.nextLine();
        jugador.setNombreJugador(nombreJugador); // Guardar el nombre del jugador
    }

    // Método para mostrar arte ASCII
    private void mostrarArteASCII() {
        /**
         * Muestra arte ASCII en la consola, creando un efecto visual llamativo.
         * Este método imprime múltiples líneas de texto que representan
         * un diseño artístico en ASCII, utilizando diferentes caracteres
         * y símbolos para embellecer la salida.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("✶ *   ✧₊⁺      *       *       *       *    *    *      *   *     *  *    *     *    *    *     *    *   * ✧* *");
        System.out.println("*    *	    *	███╗   ██╗ ██████╗ *    *   ██╗ █████╗ ██╗   ██╗ █████╗ *  *███████╗██╗  ██╗██╗ * ██╗ *   *   *");
        System.out.println("✶   *	*    *	████╗  ██║██╔═══██╗   *   * ██║██╔══██╗██║ * ██║██╔══██╗  * ██╔════╝██║ ██╔╝╚██╗ ██╔╝   ✧₊⁺   ✶");
        System.out.println("*   *	 ✧₊⁺  *	██╔██╗ ██║██║   ██║     *   ██║███████║██║   ██║███████║    ███████╗█████╔╝  ╚████╔╝  *   *   *");
        System.out.println("✶ *    *    *   ██║╚██╗██║██║   ██║ *  ██ * ██║██╔══██║╚██╗ ██╔╝██╔══██║  * ╚════██║██╔═██╗   ╚██╔╝    *    * ✶");
        System.out.println("*   * 	*     *	██║ ╚████║╚██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║ *  ███████║██║  ██╗   ██║   *    *   *");
        System.out.println("✶    ✧₊⁺ *   *	╚═╝  ╚═══╝ ╚═════╝ *  * ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝  * ╚══════╝╚═╝  ╚═╝   ╚═╝   ✧₊  *    ✶");
        System.out.println("*  *     *       *       *       *    *    *      *   *     *   *      *     *    *    *    *     *   *   *   *");
        System.out.println("\n");
    }

    // Método para obtener una opción válida del usuario
    private int obtenerOpcion(Scanner scanner) {
        /**
         * Solicita al usuario que ingrese una opción y devuelve un número entero.
         * Este método sigue solicitando la entrada hasta que el usuario proporciona
         * un número válido. En caso de que la entrada no sea un número, se mostrará
         * un mensaje de error y se volverá a solicitar la entrada.
         *
         * Parámetros:
         *   - scanner: El objeto Scanner utilizado para leer la entrada del usuario.
         *
         * Devuelve:
         *   - Un número entero que representa la opción seleccionada por el usuario.
         */
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, ingresa un número: ");
            }
        }
    }
}
