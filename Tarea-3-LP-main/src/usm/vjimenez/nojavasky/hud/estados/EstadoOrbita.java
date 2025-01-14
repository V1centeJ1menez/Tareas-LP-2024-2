package usm.vjimenez.nojavasky.hud.estados;

import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.CentroGalactico;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import usm.vjimenez.nojavasky.utilidad.RandomNumberGenerator;
import java.util.Scanner;


public class EstadoOrbita extends GameState {

    //*************************************************** CONSTRUCTOR ***************************************************//
    public EstadoOrbita(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
        super(gsm, jugador, nave, mapa); // Llama al constructor de GameState
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
         * Muestra las opciones disponibles para el jugador en el menú principal del juego.
         * Este método ejecuta un bucle que permite al jugador interactuar con el menú principal,
         * incluyendo la exploración del planeta actual, el viaje a otros destinos, la visualización
         * del mapa y el inventario, y la salida de la nave. También maneja un submenú para la
         * exploración del planeta.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        boolean jugando = true;

        while (jugando) {
            limpiarPantalla();
            mostrarCabecera(); // Muestra el arte ASCII y la información relevante
            mostrarEstadoActual(); // Mostrar estado del jugador y la nave
            mostrarOpcionesMenu(); // Muestra las opciones del menú

            int opcion = scanner.nextInt(); // Usar el scanner de la superclase
            Planeta planetaActual = mapa.getPlanetaActual();

            switch (opcion) {
                case 1:
                    efectoCerrarOjos(); // Llama al efecto antes de bajar al planeta
                    boolean enMenuPlaneta = true;

                    while (enMenuPlaneta) {
                        limpiarPantalla();
                        resetCabecera();
                        System.out.println("Submenú: Exploración del Planeta");
                        System.out.println("[1] Mostrar información del planeta");
                        System.out.println("[2] Bajar al planeta");
                        System.out.println("[3] Volver");

                        int opcionPlaneta = scanner.nextInt();

                        switch (opcionPlaneta) {
                            case 1:
                                // Mostrar la información del planeta
                                limpiarPantalla();
                                resetCabecera();
                                mostrarBajarPlaneta();
                                pausa();
                                break;

                            case 2:
                                // Bajar al planeta
                                limpiarPantalla();
                                if (planetaActual.visitar(jugador)) {
                                
                                    if (planetaActual instanceof CentroGalactico) {
                                        cambiarEstado(new EstadoCentroGalactico(gsm, jugador, nave, mapa));
                                    } else{
                                        cambiarEstado(new EstadoVisitandoPlaneta(gsm, jugador, nave, mapa,true));
                                        enMenuPlaneta = false; // Salir del submenú después de bajar al planeta
                                        jugando = false;
                                        break;
                                    }

                                } else {
                                    System.out.println("No se pudo visitar planeta...");
                                }
                              

                            case 3:
                                // Volver al menú principal
                                enMenuPlaneta = false; // Salir del submenú y volver al menú principal
                                break;

                            default:
                                System.out.println("Opción no válida, por favor intente de nuevo.");
                                pausa(); // Pausa antes de continuar
                                break;
                        }
                    }
                            
                    break;
                
                case 2:
                    efectoCerrarOjos(); // Llama al efecto antes de mostrar el submenu de viaje
                    mostrarSubmenuViaje();
                    break;
            
                case 3:
                    efectoCerrarOjos(); // Llama al efecto antes de mostrar el mapa
                    limpiarPantalla();
                    resetCabecera();
                    mapa.mostrarMapa();
                    pausa();
                    break;
            
                case 4:
                    efectoCerrarOjos(); // Llama al efecto antes de mostrar el inventario
                    limpiarPantalla();
                    resetCabecera();
                    mostrarInventario();
                    break;
            
                case 5:
                    efectoCerrarOjos();
                    limpiarPantalla();
                    mostrarCabeceraMuerto();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Saliste de la nave");
                    gsm.cerrarScanner(); 
                    jugando = false;
                    break;
            
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
                    pausa(); // Pausa antes de continuar
                    break;
            }
            
        }
    }

    // Método para mostrar las opciones del menú
    private void mostrarOpcionesMenu() {
        /**
         * Muestra las opciones del menú principal disponibles para el jugador.
         * Este método se encarga de imprimir en consola las acciones que el jugador puede
         * seleccionar durante su turno en el juego, incluyendo bajar al planeta, viajar a otro
         * planeta, mostrar el mapa galáctico, mostrar el inventario y terminar el juego.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("Seleccione una opción:");
        System.out.println("[1] Bajar al planeta");
        System.out.println("[2] Viajar a otro planeta");
        System.out.println("[3] Mostrar mapa galáctico");
        System.out.println("[4] Mostrar inventario");
        System.out.println("[5] Terminar juego");
    }

    // Método para mostrar la información del planeta
    private void mostrarBajarPlaneta() {
        Planeta planetaActual = mapa.getPlanetaActual();
        String tipoPlaneta = planetaActual.getTipo();
        
        // Simular una pantalla de computadora con la descripción
        System.out.println("==============================================");
        System.out.println("|              Información del Planeta        |");
        System.out.println("==============================================");
        System.out.println("| Tipo: " + tipoPlaneta);
        System.out.println("|--------------------------------------------|");
        String descripcion = planetaActual.getDescripcion();
        
        // Formatear la descripción para que parezca dentro de una pantalla
        mostrarDescripcionEnPantalla(descripcion, 44);
        System.out.println("==============================================");

    }

    // Método para mostrar la descripción con ajuste de líneas
    private void mostrarDescripcionEnPantalla(String texto, int anchoMaximo) {
        /**
         * Muestra la información detallada del planeta actual.
         * Este método se encarga de imprimir en consola la descripción del planeta,
         * incluyendo su tipo y otras características relevantes. Simula una pantalla
         * de computadora para presentar la información de manera estructurada.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        String[] palabras = texto.split(" ");
        StringBuilder lineaActual = new StringBuilder();

        for (String palabra : palabras) {
            if (lineaActual.length() + palabra.length() + 1 > anchoMaximo) {
                // Cuando la línea excede el ancho máximo, imprime y resetea la línea
                System.out.println("| " + formatearLinea(lineaActual.toString(), anchoMaximo) + " |");
                lineaActual = new StringBuilder(palabra);
            } else {
                if (lineaActual.length() > 0) {
                    lineaActual.append(" ");
                }
                lineaActual.append(palabra);
            }
        }
        // Imprimir la última línea
        if (lineaActual.length() > 0) {
            System.out.println("| " + formatearLinea(lineaActual.toString(), anchoMaximo) + " |");
        }
    }

    // Método para formatear una línea con espacios si es más corta que el ancho máximo
    private String formatearLinea(String texto, int anchoMaximo) {
        /**
         * Formatea una línea de texto asegurándose de que tenga un ancho específico.
         * Si el texto es más corto que el ancho máximo proporcionado, se completa
         * con espacios en blanco a la derecha. Esto es útil para mantener una
         * presentación uniforme en la salida de texto.
         *
         * Parámetros:
         *   - texto: El texto a formatear. (String)
         *   - anchoMaximo: El ancho máximo deseado para la línea. (int)
         *
         * Devuelve:
         *   - Un String que contiene el texto formateado con espacios adicionales
         *     si es necesario, garantizando que tenga el ancho especificado.
         */
        return String.format("%-" + anchoMaximo + "s", texto); // Completa con espacios si es más corto
    }
    
    // Método para mostrar el inventario del jugador
    private void mostrarInventario() {
        /**
         * Muestra el inventario del jugador en la consola.
         * Este método invoca el método correspondiente en la clase Inventario
         * para mostrar todos los elementos que el jugador ha recogido durante su
         * aventura, así como cualquier información relevante relacionada con la nave.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        jugador.getInventario().mostrarInventario(jugador, nave);
    }


    private void efectoCerrarOjos() {
        /**
         * Aplica un efecto visual de "cerrar los ojos" en la consola,
         * mostrando un arte ASCII correspondiente y la información actual
         * del jugador. El efecto dura un tiempo aleatorio entre 100 y 150
         * milisegundos, después del cual se vuelve a mostrar el arte ASCII
         * original y el estado actual del jugador.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        int milisegundos = RandomNumberGenerator.rand(100, 150);
        limpiarPantalla();
        mostrarCabeceraCerrandoOjos(); // Mostrar arte ASCII "cerrando los ojos"
        mostrarEstadoActual();
        pausaAnimacion(milisegundos); // Mantenerlo visible brevemente
        limpiarPantalla();
        mostrarCabecera(); // Volver a mostrar el arte ASCII original
        mostrarEstadoActual();
    }
    
    

    // Método para mostrar el estado actual de la nave y el jugador
    private void mostrarEstadoActual() {
        /**
         * Muestra el estado actual del jugador y la nave en la consola.
         * Incluye el nombre del jugador, la energía y eficiencia del exotraje,
         * así como el combustible y eficiencia de la nave. También muestra
         * el estado de la órbita actual y el tipo de planeta en el que se
         * encuentra.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("Estado del Jugador: " + jugador.getNombreJugador());
        System.out.println("Estado del Exotraje: Energía: " + jugador.getUnidadesEnergiaProteccion() + " | Eficiencia: " + jugador.getEficienciaEnergiaProteccion() + "%");
        System.out.println("Estado de la Nave: Combustible: " + nave.getUnidadesCombustible() + " | Eficiencia: " + nave.getEficienciaPropulsor() + "%");
        System.out.println("Orbitando | Planeta Actual: " + mapa.getPlanetaActual().getTipo() + " | Posición: " + mapa.getPosicionActual());
        System.out.println("--------------------------------------------------------");
    }

    // Nueva opción: Menú para viajar a otro planeta
    private void mostrarSubmenuViaje() {
        /**
         * Muestra el submenú para viajar a otro planeta.
         * Este método permite al jugador elegir entre realizar un salto espacial,
         * analizar saltos anteriores o volver al menú principal. Se ejecuta en
         * un bucle hasta que el jugador decide salir del submenú.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        boolean enSubmenu = true;
        while (enSubmenu) {
            limpiarPantalla();
            resetCabecera();
            System.out.println("Submenú: Viajar a otro planeta");
            System.out.println("[1] Dar salto espacial");
            System.out.println("[2] Analizar saltos");
            System.out.println("[3] Volver");

            int opcionViaje = scanner.nextInt();

            switch (opcionViaje) {
                case 1:
                    efectoCerrarOjos();
                    limpiarPantalla();
                    resetCabecera();
                    realizarViaje();
                    enSubmenu = false;
                    break;
                case 2:
                    resetCabecera();
                    analizarSaltos();
                    break;
                case 3:
                    enSubmenu = false;  // Volver al menú principal
                    efectoCerrarOjos(); 
                    limpiarPantalla();
                    resetCabecera();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    pausa();
                    break;
            }
        }
    }

    // Método para realizar un viaje a otro planeta
    private void realizarViaje() {
        /**
         * Realiza un viaje a otro planeta según la dirección y el tamaño del salto
         * especificados por el jugador. Dependiendo de la posición actual en el mapa,
         * el jugador puede elegir moverse hacia adelante, hacia atrás o volver al submenú.
         * El método también gestiona la lógica de éxito o fracaso del salto.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        
        resetCabecera();

        if (mapa.getPosicionActual() == 0) {
            System.out.print("Ingrese la dirección de su siguiente salto: \n[1] Adelante\n[3] Volver\n");
        } else {
            System.out.print("Ingrese la dirección de su siguiente salto: \n[1] Adelante\n[2] Atrás\n[3] Volver\n");
        }

        int direccion = scanner.nextInt();

        if (direccion == 1) {
            direccion = 1;
        } else if (direccion == 2) {
            direccion = -1;
        } else if (direccion == 3) {
            efectoCerrarOjos(); 
            limpiarPantalla();
            resetCabecera();
            return; // Volver al submenú
        } else {
            System.out.println("Dirección inválida.");
            pausa();
            return;
        }

        System.out.print("Ingrese el tamaño del salto: ");
        int tamanoSalto = scanner.nextInt();

        efectoCerrarOjos(); 
        limpiarPantalla();
        resetCabecera();

        if (nave.viajarPlaneta(mapa, direccion, tamanoSalto)) {
            System.out.println("El salto ha sido exitoso.");
        } else {
            System.out.println("El salto ha fallado.");
        }
        pausa();
    }

    // Método para analizar los saltos disponibles
    private void analizarSaltos() {
        /**
         * Analiza los saltos disponibles según el tamaño especificado por el jugador.
         * Permite al jugador elegir entre analizar los saltos o volver al menú anterior.
         * Si elige analizar, solicita el tamaño del salto y muestra cuántas opciones
         * de salto están disponibles en función de la capacidad de la nave.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        resetCabecera();
        System.out.println("Stellar Pathway Analyzer 2.0\n¿Qué deseas hacer?");
        boolean analisisTerminado = false;

        while (!analisisTerminado) {
            System.out.println("[1] Analizar saltos");
            System.out.println("[2] Volver");

            int opcionAnalisis = scanner.nextInt();

            if (opcionAnalisis == 1) {
                efectoCerrarOjos(); // Llama al efecto antes de mostrar el inventario
                limpiarPantalla();
                resetCabecera();
                resetCabecera();
                System.out.println("Indica el tamaño del salto que quieres analizar: ");
                int distanciaSalto = scanner.nextInt();
                int opciones = nave.analizarSaltos(distanciaSalto);
                System.out.println("Hay " + opciones + " opciones de salto disponibles.");
            } else if (opcionAnalisis == 2) {
                efectoCerrarOjos(); // Llama al efecto antes de mostrar el inventario
                limpiarPantalla();
                resetCabecera();
                analisisTerminado = true;
                resetCabecera();
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private void resetCabecera() {
        /**
         * Restablece la cabecera de la pantalla limpiando la pantalla actual
         * y mostrando de nuevo la cabecera y el estado actual del jugador
         * y la nave. Este método se utiliza para mantener la interfaz
         * actualizada y proporcionar una visión clara del estado del juego.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        limpiarPantalla();
        mostrarCabecera();
        mostrarEstadoActual();
    }

    // Método para mostrar el arte ASCII y la información del jugador
    private void mostrarCabecera() {
        /**
         * Muestra el arte ASCII y la información del jugador en la cabecera
         * de la pantalla del juego. Este método se utiliza para proporcionar
         * una representación visual del juego y mejorar la experiencia del usuario.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("                                         ████████████                                                ");
        System.out.println("                                      ██████████████████                                             ");
        System.out.println("                                   ████████████████████████                                          ");
        System.out.println("                                   ████████████████████████                                         ");
        System.out.println("                                 ██████                ██████                                       ");
        System.out.println("                               █████    ███████████████   █████                                    ");
        System.out.println("                               ███   ███               ███   ███                                  ");
        System.out.println("                              ███ ███   ███████████████   ███  ███                                ");
        System.out.println("                              ██ █   ██████████████████████   █ ██                                ");
        System.out.println("                             █  █  ██████████████████████████  █  █                               ");
        System.out.println("                            ██  █  ███████ █████████ ████████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  ███████ █████████ ████████  █  ██                               ");
        System.out.println("                              █  █  ████████████████████████  █  █                                 ");
        System.out.println("                              ███  █  ███████      ███████  █  ███                                  ");
        System.out.println("                              ████  █   ████████████████   █  ████                                  ");
        System.out.println("                               █████ ██  ██████████████  ██ █████                                  ");
        System.out.println("                               ███████ ██████████████████ ███████                                    ");
        System.out.println("                              █  ██████████████████████████████  █                                    ");
        System.out.println("                           ██  ██ ████████████████████████████ ██  ██                                ");
        System.out.println("                           ████ ██  █████   █     █   █████  ██ █████                                ");
        System.out.println("                         ████████ ██  ███   █     █   ████  ██ ████████                              ");
        System.out.println("                         ██████████ █ ████████████████████ █ ██████████                             ");
        System.out.println("                       █████████████  ████████████████████  █████████████                           ");
        System.out.println("                      ██████████████  █  █            █  █  ██████████████                          ");
        System.out.println("                     ███████████████  █  ██████████████  █  ███████████████                         ");
        System.out.println("                   █████████          ████            ████          █████████                        ");
        System.out.println("                   █████████  ██████  ███ ████████████ ███  ██████  █████████                       ");
        System.out.println("                 ███████████  █    █  ███ ██        ██ ███  █    █  ██████████                      ");
        System.out.println("                ████████████  ██████  ███ ████████████ ███  ██████  ███████████                     ");
        System.out.println("                ████████████          ███ ██        ██ ███          ███████████                    ");
        System.out.println("                ████████████████████  ███ ████████████ ███  ███████████████████                    ");
        System.out.println();
    }
    
    private void mostrarCabeceraCerrandoOjos() {
        /**
         * Muestra el arte ASCII y la información del jugador en la cabecera
         * de la pantalla del juego, similar al método mostrarCabecera. 
         * Este método se utiliza cuando se simula el efecto de cerrar los ojos
         * para proporcionar una experiencia inmersiva al usuario.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("                                         ████████████                                                ");
        System.out.println("                                      ██████████████████                                             ");
        System.out.println("                                   ████████████████████████                                          ");
        System.out.println("                                   ████████████████████████                                         ");
        System.out.println("                                 ██████                ██████                                       ");
        System.out.println("                               █████    ███████████████   █████                                    ");
        System.out.println("                               ███   ███               ███   ███                                  ");
        System.out.println("                              ███ ███   ███████████████   ███  ███                                ");
        System.out.println("                              ██ █   ██████████████████████   █ ██                                ");
        System.out.println("                             █  █  ██████████████████████████  █  █                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                               ");
        System.out.println("                              █  █  ████████████████████████  █  █                                 ");
        System.out.println("                              ███  █  ███████      ███████  █  ███                                  ");
        System.out.println("                              ████  █   ████████████████   █  ████                                  ");
        System.out.println("                               █████ ██  ██████████████  ██ █████                                  ");
        System.out.println("                               ███████ ██████████████████ ███████                                    ");
        System.out.println("                              █  ██████████████████████████████  █                                    ");
        System.out.println("                           ██  ██ ████████████████████████████ ██  ██                                ");
        System.out.println("                           ████ ██  █████   █     █   █████  ██ █████                                ");
        System.out.println("                         ████████ ██  ███   █     █   ████  ██ ████████                              ");
        System.out.println("                         ██████████ █ ████████████████████ █ ██████████                             ");
        System.out.println("                       █████████████  ████████████████████  █████████████                           ");
        System.out.println("                      ██████████████  █  █            █  █  ██████████████                          ");
        System.out.println("                     ███████████████  █  ██████████████  █  ███████████████                         ");
        System.out.println("                   █████████          ████            ████          █████████                        ");
        System.out.println("                   █████████  ██████  ███ ████████████ ███  ██████  █████████                       ");
        System.out.println("                 ███████████  █    █  ███ ██        ██ ███  █    █  ██████████                      ");
        System.out.println("                ████████████  ██████  ███ ████████████ ███  ██████  ███████████                     ");
        System.out.println("                ████████████          ███ ██        ██ ███          ███████████                    ");
        System.out.println("                ████████████████████  ███ ████████████ ███  ███████████████████                    ");
        System.out.println();
    }

    private void mostrarCabeceraMuerto() {
        /**
         * Muestra el arte ASCII y la información del jugador en la cabecera
         * de la pantalla del juego cuando el jugador ha muerto. Este método
         * proporciona una representación visual del estado del juego, indicando
         * la finalización del juego de manera dramática y envolvente.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        
        System.out.println("                                        ███    ██                                      ");
        System.out.println("                                                       █     █                                  ");
        System.out.println("                                           ███    ██    ██    ███                               ");
        System.out.println("                                                      ███                                           ");
        System.out.println("                                           ██████████                                                ");
        System.out.println("                                      ███████████████████  ██                                        ");
        System.out.println("                                    ██ ███████████████████                                           ");
        System.out.println("                                    ██████████████████████                                           ");
        System.out.println("                                 ██ ██████████████████████ ██                                        ");
        System.out.println("                               ██   ██████████████████████   ██                                      ");
        System.out.println("                               ██ ██████████████████████████ ████                                    ");
        System.out.println("                             ████ █████████████████████████  ██ ██                                  ");
        System.out.println("                              ██  █████████████████████████  ██ ██                                  ");
        System.out.println("                            ██ ██ ███████  █████████  ███████ ██  █                                 ");
        System.out.println("                            ██ ██ █████      █████      █████  █  █                                 ");
        System.out.println("                            ██ ██ ███   ███    █   ███    ███  ████                                 ");
        System.out.println("                            ██ ██ █████      █████      █████  ████                                 ");
        System.out.println("                            ██ ██ ███████  ████  ███  ███████ ██  █                                 ");
        System.out.println("                            █████  ██████████     ███████████ ██  █                                 ");
        System.out.println("                            ██   █   ██████████  ██████████  ██   ██                                ");
        System.out.println("                             ██  ██     █████████████████    ██ ██                                  ");
        System.out.println("                              ███ ████    █████████████    ██  ███                                  ");
        System.out.println("                              ████  ███   █████████████  ██  █████                                  ");
        System.out.println("                             ██████   ███ ██ ██  █  █   ██ ███████                                  ");
        System.out.println("                              ████████                 █  ███████                                    ");
        System.out.println("                              █  ██████████████████████████████  █                                    ");
        System.out.println("                           ██  ██ ████████████████████████████ ██  ██                                ");
        System.out.println("                           ████ ██  █████   █     █   █████  ██ █████                                ");
        System.out.println("                         ████████ ██  ███   █     █   ████  ██ ████████                              ");
        System.out.println("                         ██████████ █ ████████████████████ █ ██████████                             ");
        System.out.println("                       █████████████  ████████████████████  █████████████                           ");
        System.out.println("                      ██████████████  █  █            █  █  ██████████████                          ");
        System.out.println("                     ███████████████  █  ██████████████  █  ███████████████                         ");
        System.out.println("                   █████████          ████            ████          █████████                        ");
        System.out.println("                   █████████  ██████  ███ ████████████ ███  ██████  ██  █  ██                       ");
        System.out.println("                 ███████████  █    █  ███ ██        ██ ███  █    █  ██  █  ███                      ");
        System.out.println("                ████████████  ██████  ███ ████████████ ███  ██████  ███████████                     ");
        System.out.println("                ████████████          ███ ██        ██ ███          ██  █  ████                    ");
        System.out.println("                ████████████████████  ███ ████████████ ███  ███████████████████                       ");
        System.out.println();
    }
    

}
