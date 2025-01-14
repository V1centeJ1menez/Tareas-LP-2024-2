package usm.vjimenez.nojavasky.hud.estados;

import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.entidades.planetas.*;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.*;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import java.util.Scanner;

public class EstadoVisitandoPlaneta extends GameState {

    //*************************************************** ATRIBUTOS ***************************************************//
    private boolean primeraVez;

    //*************************************************** CONSTRUCTOR ***************************************************//
    public EstadoVisitandoPlaneta(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa, boolean primeraVez) {
        super(gsm, jugador, nave, mapa); // Llama al constructor de GameState
        this.primeraVez = primeraVez;
    }

    Nave nave = this.getNave();
    GameStateManager gsm = this.getGsm();
    Jugador jugador = this.getJugador();
    MapaGalactico mapa = this.getMapa();
    Scanner scanner = this.getScanner();


    //*************************************************** METODOS ***************************************************//
   
    @Override
    public void mostrarOpciones() {
        /**
         * Muestra las opciones disponibles para el jugador al visitar un planeta.
         * Este método permite al jugador realizar diferentes acciones, como visitar asentamientos,
         * extraer recursos, ver su inventario o volver a la órbita. 
         * Además, gestiona la lógica de interacción del menú y cambia el estado del juego según las opciones seleccionadas.
         *
         * La lógica de cada opción se ejecuta en función de la elección del jugador, 
         * y algunas acciones pueden depender de las características del planeta actual.
         * Si el jugador intenta visitar asentamientos, se verifica que el planeta actual los tenga disponibles.
         * 
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        boolean enMenuVisita = true;
        if (primeraVez) {
            simularEncendidoMonitor();
            animarEntradaAlPlaneta();
        }
       
        while (enMenuVisita) {
            limpiarPantalla();
            mostrarDescenso();
            mostrarInterface();
    
            System.out.println("¿Qué deseas hacer?");
            // Verifica si el planeta actual implementa la interfaz TieneAsentamientos
            if (mapa.getPlanetaActual() instanceof tieneAsentamientos) {
                System.out.println("[0] Visitar asentamientos");
            }
            System.out.println("[1] Extraer recursos");
            System.out.println("[2] Ver inventario");
            System.out.println("[3] Volver a órbita");
    
            int opcion = scanner.nextInt();
    
            switch (opcion) {
                case 0:
                    if ( (mapa.getPlanetaActual() instanceof tieneAsentamientos)) {
                       
                        limpiarPantalla();
                        mostrarDescenso();
                        animarCaminoAsentamientos();
                        cambiarEstado(new EstadoVisitaAsentamientos(gsm, jugador, nave, mapa));
                        enMenuVisita = false;
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    pausa();
                    break;
                case 1:
                    // Lógica para extraer recursos (se implementará después)
                    limpiarPantalla();
                    cambiarEstado(new EstadoExtraccionRecursos(gsm, jugador, nave, mapa));
                    enMenuVisita = false;
                    break;
    
                case 2:
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();
                    mostrarInventario();
                    break;
    
                case 3:

                if(mapa.getPlanetaActual().salir()){
                    if (mapa.getPlanetaActual().salir()) {
                        limpiarPantalla();
                        simularEncendidoMonitor();
                        animarSalidaDelPlaneta();
                        cambiarEstado(new EstadoOrbita(gsm, jugador, nave, mapa));
                        enMenuVisita = false; // Salir del menú
                        break;
    
                    }else{ System.out.println("Error por poco combustible");}
                }
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
                    pausa();
                    break;
            }
        }
    }


    private void animarCaminoAsentamientos() {
        /**
         * Anima el recorrido hacia los asentamientos del planeta.
         * Este método muestra una serie de mensajes que simulan la caminata del jugador
         * hacia las estructuras de asentamiento en el planeta. 
         * Los mensajes se muestran uno por uno con una pausa entre ellos para dar un efecto de animación.
         * 
         * Después de mostrar todos los mensajes, se invoca el método mostrarDescenso()
         * para actualizar la visualización del descenso en el planeta.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String[] mensajes = {
            "Observas unas estructuras a lo lejos..",
            "Que curiosidad...",
            "Caminas...",
            "Y caminas..."
        };

        for (String mensaje : mensajes) {
            System.out.println(mensaje);
            pausaAnimacion(500); // Pausa por 1 segundo entre mensajes
        }
        mostrarDescenso(); // Mostrar el descenso después del efecto del monitor
    }

    private void animarSalidaDelPlaneta() {
        /**
         * Anima la salida del planeta y el regreso a órbita.
         * Este método muestra una serie de mensajes que simulan el proceso de
         * empacar suministros y encender los motores de la nave durante el despegue.
         * 
         * Los mensajes se presentan uno a uno con una pausa entre ellos para crear un efecto de animación.
         * 
         * Después de mostrar todos los mensajes, se invoca el método mostrarDescenso()
         * para actualizar la visualización del descenso en el planeta.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String[] mensajes = {
            "Empacando suministros...",
            "Encendiendo motores de la nave...",
            "Despegue...",
            "Ya en orbira, listo para saltar por el espacio..."
        };

        for (String mensaje : mensajes) {
            System.out.println(mensaje);
            pausaAnimacion(300); // Pausa por 1 segundo entre mensajes
        }
        mostrarDescenso(); // Mostrar el descenso después del efecto del monitor
    }
    
    private void animarEntradaAlPlaneta() {
        /**
         * Anima la entrada al planeta antes de iniciar la exploración.
         * Este método muestra una serie de mensajes que simulan el proceso de 
         * descenso hacia el planeta, incluyendo la preparación del exotraje 
         * y el análisis de datos necesarios para la exploración.
         *
         * Los mensajes se presentan uno a uno con una pausa entre ellos para crear 
         * un efecto de animación, mejorando la inmersión del jugador.
         *
         * Después de mostrar todos los mensajes, se invoca el método mostrarDescenso()
         * para actualizar la visualización del descenso en el planeta.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String[] mensajes = {
            "Bajando al planeta...",
            "Analizando datos...",
            "Preparando exotraje...",
            "Listo para la exploración..."
        };

        for (String mensaje : mensajes) {
            System.out.println(mensaje);
            pausaAnimacion(300); // Pausa por 1 segundo entre mensajes
        }
        mostrarDescenso(); // Mostrar el descenso después del efecto del monitor
    }

    private void simularEncendidoMonitor() {
        /**
         * Simula el encendido del monitor de la nave.
         * Este método presenta una serie de mensajes que simulan la 
         * secuencia de encendido de un monitor, utilizando caracteres 
         * que representan el proceso de inicialización.
         *
         * La pantalla se limpia antes de mostrar cada mensaje para 
         * mejorar el efecto visual, creando una experiencia más 
         * inmersiva para el jugador.
         *
         * Después de mostrar todos los mensajes, se limpia la pantalla 
         * nuevamente para preparar la siguiente visualización.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String[] encendido = {
            " . . .",
            " . . █",
            " . . .",
            " . . █",
            " . . .",
            " . . █",
            " . . .",
        };

        for (String mensaje : encendido) {
            limpiarPantalla(); // Limpia la pantalla para un mejor efecto visual
            System.out.println(mensaje);
            pausaAnimacion(200); // Pausa por 500 ms entre cada línea
        }
        limpiarPantalla();
    }

    public static void mostrarDescenso() {
        /**
         * Muestra el arte ASCII que representa el descenso de la nave 
         * hacia un planeta. Este método imprime en la consola un diseño 
         * visual que simula la entrada de la nave a la atmósfera de un 
         * planeta, creando una experiencia inmersiva para el jugador.
         *
         * El arte incluye diferentes elementos visuales que representan 
         * la nave, el espacio y el entorno planetario.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String arte = 
            "*                                            ✧₊                                        ₊                 \n" +
            "                    ooooo \n" +
            "           /█████\\oo    o                                   *                                       ₊                 \n" +
            "          /███████\\  ooo             ██                                           *            \n" +
            "          ████████ooo\n" +
            "        oo██████oo█\n" +
            "       o  \\███oo██/         *                   ₊                       █                 *                    ₊\n" +
            "      o    ooo███/\n" +
            "      ooooo                  \n" +
            "                                    *          ₊                                 *                   ✧₊  \n" +
            "                                                         *                                            \n" +
            "  ₊  \n" +
            "                 *                        ₊                           *                   ₊     \n" +
            "\n" +
            "                                                                                                      /\\\n" +
            "        ██                     *                                                                 /\\  /  \\     ₊ \n" +
            "                                                                                                /  \\/    \\\n" +
            "                    /\\                                                           ✧₊            /    \\     \\\n" +
            "                   /  \\                          ₊                    /\\                      /      \\     \\\n" +
            "__________________/    \\___________________________________________/\\/  \\____________________/        \\_____\\______\n";

        System.out.println(arte); // Muestra el arte en la consola
    }

    public void mostrarInterface() {
        /**
         * Muestra la información del planeta actual y del jugador en la interfaz del juego.
         * Este método imprime en la consola detalles como el tipo de planeta, su posición,
         * recursos disponibles y la energía y eficiencia del traje del jugador, así como 
         * la información de la nave.
         *
         * Incluye información específica dependiendo del tipo de planeta actual (radioactivo,
         * volcánico, oceánico o helado) y muestra si el planeta tiene asentamientos.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        Planeta planetaActual = mapa.getPlanetaActual();
        System.out.println("==========================================================================================================================");
        System.out.println("|                                             Información del Planeta Actual                                             |");
        System.out.println("==========================================================================================================================");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------|");

        

        System.out.println("Tipo de planeta: " + planetaActual.getTipo()); // Suponiendo que tienes un método para obtener el tipo de planeta
        System.out.println("Posición del planeta: " + mapa.getPosicionActual()); // Método que obtiene la posición
        System.out.println("Radio del planeta: " + planetaActual.getRadio() + " metros"); // Suponiendo que tienes un método para obtener el radio
        
        // Mostrar los recursos
        System.out.println("Recursos disponibles:");
        System.out.println("Cristales de Hidrógeno: " + planetaActual.getCristalesHidrogeno() + " unidades");
        System.out.println("Flores de Sodio: " + planetaActual.getFloresDeSodio() + " unidades");

        if (planetaActual instanceof Radioactivo) {
            Radioactivo radioactivo = (Radioactivo) planetaActual;
            System.out.println("Uranio: " + radioactivo.getUranio() + " unidades");
            System.out.println("Radiación: " + radioactivo.getRadiacion() + " Rad");
        } else if (planetaActual instanceof Volcanico) {
            Volcanico volcanico = (Volcanico) planetaActual;
            System.out.println("Platino: " + volcanico.getPlatino() + " unidades");
            System.out.println("Temperatura: " + volcanico.getTemperatura()+ " ºC");
        } else if (planetaActual instanceof Oceanico) {
            Oceanico oceanico = (Oceanico) planetaActual;
            System.out.println("Profundidad: " + oceanico.getProfundidad()+ " metros");
        } else if (planetaActual instanceof Helado) {
            Helado helado = (Helado) planetaActual;
            System.out.println("Temperatura: " + helado.getTemperatura() + " ºC");
        }   

        if (planetaActual instanceof tieneAsentamientos) {
            System.out.println("Este planeta tiene asentamientos.");
        } else {
            System.out.println("Este planeta no tiene asentamientos.");
        }
        
        // Información del jugador
        System.out.println("|------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("Energía del traje: " + jugador.getUnidadesEnergiaProteccion() + " unidades de energia de proteccion");
        System.out.println("Eficiencia del traje: " + jugador.getEficienciaEnergiaProteccion() + " %");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("Combustible: " + nave.getUnidadesCombustible() + " unidades de combustible");
        System.out.println("Eficiencia de la nave: " + nave.getEficienciaPropulsor()+ " %");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("==========================================================================================================================");
    
    }
        

    // Método para mostrar el inventario del jugador
    private void mostrarInventario() {
        /**
         * Muestra el inventario del jugador en la interfaz del juego.
         * Este método llama al método `mostrarInventario` del objeto `Inventario`
         * del jugador, pasando el jugador y la nave como parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        jugador.getInventario().mostrarInventario(jugador, nave);
    }
}


