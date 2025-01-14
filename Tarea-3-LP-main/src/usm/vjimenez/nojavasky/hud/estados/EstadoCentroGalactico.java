package usm.vjimenez.nojavasky.hud.estados;


import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import java.util.Scanner;

public class EstadoCentroGalactico extends GameState {

    //*************************************************** CONSTRUCTOR ***************************************************//
    public EstadoCentroGalactico(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
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
     * Muestra las opciones disponibles para el jugador según la eficiencia del propulsor de la nave.
     * Si la eficiencia es menor al 50%, muestra un mensaje sobre la imposibilidad de alcanzar el centro de la galaxia.
     * Si la eficiencia es igual o mayor al 50%, describe la experiencia de alcanzar el centro de la galaxia y
     * presenta opciones de acción al jugador.
     * 
     * Parámetros:
     *   - No recibe parámetros.
     * 
     * Devuelve:
     *   - No devuelve ningún valor.
     */
    public void mostrarOpciones() {

        if(nave.getEficienciaPropulsor() < 50){
            
            System.out.println("******************************************************");
            System.out.println("*                                                    *");
            System.out.println("*   Desde lo lejos, observas el resplandor del centro *");
            System.out.println("*   de la galaxia. Un lugar donde la física parece    *");
            System.out.println("*   no seguir las reglas convencionales. A medida     *");
            System.out.println("*   que tu nave se aproxima, la realidad a tu alrededor*");
            System.out.println("*   se distorsiona como si estuvieras atrapado en la  *");
            System.out.println("*   paradoja de Zenón.                                *");
            System.out.println("*                                                    *");
            System.out.println("*   Cada vez más cerca, pero sin llegar jamás, es como*");
            System.out.println("*   si el destino estuviera fuera de tu alcance. Tras *");
            System.out.println("*   analizar los sistemas de la nave, descubres que la*");
            System.out.println("*   eficiencia de tu propulsor es insuficiente para   *");
            System.out.println("*   sobrepasar la barrera gravitacional del núcleo.   *");
            System.out.println("*                                                    *");
            System.out.println("*   La eficiencia de tu nave debe ser mayor al 50%.   *");
            System.out.println("*                                                    *");
            System.out.println("*   Vuelve cuando hayas mejorado tu propulsor...      *");
            System.out.println("*                                                    *");
            System.out.println("******************************************************");
            pausa();
            cambiarEstado(new EstadoOrbita(gsm, jugador, nave, mapa));

        }else{

            System.out.println("                   '                                .                *                 ");
            System.out.println("            *          .                  .                      .           ' .                        ");
            System.out.println("                   *       '              .                      ;           ");
            System.out.println("              *                *          :                  - --+- -        ");
            System.out.println("                                          !           .          !                   .");
            System.out.println("         .                      .         |        .             .         *  ");
            System.out.println("                                          |_         +");
            System.out.println("  .                 *     .            ,  | `.                                     .");
            System.out.println("                                 --- --+-<#>-+- ---  --  -            '      *   ");
            System.out.println("   *   '*      .                       `._|_,'                            .");
            System.out.println("           *                              T                                           *");
            System.out.println("                *                         |                                       '*");
            System.out.println("                       *                  !                   .:              ");
            System.out.println("                     *                    *             *               .*");

            System.out.println("**********************************************************");
            System.out.println("*                                                        *");
            System.out.println("*   Has logrado lo que pocos han conseguido: alcanzar el  *");
            System.out.println("*   centro de la galaxia. Un lugar donde las leyes de la  *");
            System.out.println("*   física parecen retorcerse. Aquí, el espacio y el tiempo*");
            System.out.println("*   colapsan en un solo punto, y todo lo que sabías parece*");
            System.out.println("*   perder sentido.                                       *");
            System.out.println("*                                                        *");
            System.out.println("*   Tu nave comienza a vibrar, no por fallos mecánicos,   *");
            System.out.println("*   sino por la inmensa energía que irradia del núcleo.   *");
            System.out.println("*   Las pantallas se llenan de lecturas incomprensibles,  *");
            System.out.println("*   y una extraña sensación de ingravidez te invade.      *");
            System.out.println("*                                                        *");
            System.out.println("*   Frente a ti, una brillante esfera de luz aparece,     *");
            System.out.println("*   pulsando como el latido de un corazón cósmico. Este es*");
            System.out.println("*   el origen, el núcleo de toda creación galáctica. Sin  *");
            System.out.println("*   palabras, entiendes que lo que ves es la esencia misma*");
            System.out.println("*   del universo.                                         *");
            System.out.println("*                                                        *");
            System.out.println("*   Una profunda calma te envuelve mientras observas el   *");
            System.out.println("*   espectáculo cósmico. Te das cuenta de que la clave    *");
            System.out.println("*   para comprender la galaxia no está en conquistarla,   *");
            System.out.println("*   sino en respetarla.                                   *");
            System.out.println("*                                                        *");
            System.out.println("*   Aquí, en el corazón de las estrellas, todo parece     *");
            System.out.println("*   posible, pero también frágil. ¿Qué harás ahora?       *");
            System.out.println("*                                                        *");
            System.out.println("**********************************************************");

            
            System.out.println("");
            System.out.println("[1] Continuar explorando");
            System.out.println("[2] Reiniciar juego");
            System.out.println("[3] Salir del juego");

                // Lee la entrada del usuario para realizar una acción
            int opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    cambiarEstado(new EstadoOrbita(gsm, jugador, nave, mapa));
                    break;
                case 2:
                
                    // Reiniciar las instancias
                    GameStateManager nuevoGsm = new GameStateManager(); // Crea una nueva instancia del GameStateManager
                    Jugador nuevoJugador = new Jugador();               // Crea un nuevo jugador
                    Nave nuevaNave = new Nave();                        // Crea una nueva nave
                    MapaGalactico nuevoMapa = new MapaGalactico();      // Crea un nuevo mapa galáctico
                
                    // Cambia el estado actual al menú principal con las nuevas instancias
                    cambiarEstado(new EstadoMainMenu(nuevoGsm, nuevoJugador, nuevaNave, nuevoMapa));
                case 3:
                    // Cerrar el programa
                    System.out.println("Saliendo del juego...");
                    cerrarScanner();  // Cerrar el Scanner antes de salir
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
        }
    }
}

   
