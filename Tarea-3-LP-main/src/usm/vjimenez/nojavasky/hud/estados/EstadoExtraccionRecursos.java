package usm.vjimenez.nojavasky.hud.estados;

import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tieneAsentamientos;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.*;
import usm.vjimenez.nojavasky.juego.inventario.*;
import java.util.Scanner;

public class EstadoExtraccionRecursos extends GameState {

    //*************************************************** ATRIBUTOS ***************************************************//
    private Planeta planetaActual;
    
    //*************************************************** CONSTRUCTOR ***************************************************//
    public EstadoExtraccionRecursos(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
        super(gsm, jugador, nave, mapa);
        this.planetaActual = mapa.getPlanetaActual();
        
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
     * Muestra las opciones de extracción de recursos disponibles en el planeta actual.
     * Este método se ejecuta en un bucle hasta que el jugador decide salir del menú o
     * se desploma por falta de energía de protección.
     *
     * Parámetros:
     *   - No recibe parámetros.
     *
     * Devuelve:
     *   - No devuelve ningún valor.
     */
        
        boolean enMenuExtraccion = true;
        while (enMenuExtraccion) {
            limpiarPantalla();
            mostrarDescenso();
            mostrarInterface();

            if (jugador.getUnidadesEnergiaProteccion() == 0) {
                limpiarPantalla();
                mostrarCabeceraDesplomado();

                System.out.println("*******************************************************");
                System.out.println("*                                                     *");
                System.out.println("*   Te desplomas lentamente mientras la energía de tu  *");
                System.out.println("*   traje se agota por completo. El frío y la oscuridad*");
                System.out.println("*   se ciernen sobre ti mientras sientes como si tu    *");
                System.out.println("*   cuerpo fuera arrastrado hacia un vacío profundo.   *");
                System.out.println("*                                                     *");
                System.out.println("*   Tu consciencia se desvanece... y en ese instante,  *");
                System.out.println("*   sueñas. Sueñas que mueres en este inhóspito planeta,*");
                System.out.println("*   atrapado sin esperanza. Pero, de alguna manera,    *");
                System.out.println("*   algo más grande te envuelve, una fuerza desconocida*");
                System.out.println("*   y antigua.                                        *");
                System.out.println("*                                                     *");
                System.out.println("*   De pronto, despiertas... no en el lugar donde      *");
                System.out.println("*   caíste, sino en el planeta donde todo comenzó.     *");
                System.out.println("*   Confundido, miras alrededor: tu nave está allí,    *");
                System.out.println("*   pero algo ha cambiado. Todas tus mejoras se han    *");
                System.out.println("*   desvanecido, y tu inventario está vacío. Es como   *");
                System.out.println("*   si la aventura nunca hubiera ocurrido.            *");
                System.out.println("*                                                     *");
                System.out.println("*   Sin embargo, en el fondo de tu ser, sientes que    *");
                System.out.println("*   no fue solo un sueño. Has perdido todo, pero la    *");
                System.out.println("*   experiencia aún te acompaña. Un nuevo comienzo...  *");
                System.out.println("*   ¿O un segundo intento?                            *");
                System.out.println("*                                                     *");
                System.out.println("*******************************************************");
            
                nave.setCapcidadTotalCombustible(100);
                nave.setEficienciaPropulsor(0);
                nave.setUnidadesCombustible(100);
                jugador.setCapcidadTotalEnergiaProteccion(100);
                jugador.setEficienciaEnergiaProteccion(0);
                jugador.setUnidadesEnergiaProteccion(100);
                Inventario inventario = jugador.getInventario();
                inventario.setCristalesHidrogeno(0);
                inventario.setFloresDeSodio(0);
                inventario.setPlatino(0);
                inventario.setUranio(0);
                pausa();
                mapa.setPosicionActual('0');
                cambiarEstado(new EstadoOrbita(gsm, jugador, nave, mapa));

                enMenuExtraccion = false;
              
                
            }
            

            System.out.println("=== Extracción de Recursos ===");
            System.out.println("Recursos disponibles en el planeta:");
            System.out.println("[0] Volver");
            System.out.println("[1] Cristales de Hidrógeno: " + planetaActual.getCristalesHidrogeno() + " unidades");
            System.out.println("[2] Flores de Sodio: " + planetaActual.getFloresDeSodio() + " unidades");
           
    
            if (planetaActual instanceof Radioactivo) {
                Radioactivo radioactivo = (Radioactivo) planetaActual;
                System.out.println("[3] Uranio: " + radioactivo.getUranio() + " unidades");
            }
    
            if (planetaActual instanceof Volcanico) {
                Volcanico volcanico = (Volcanico) planetaActual;
                System.out.println("[4] Platino: " + volcanico.getPlatino() + " unidades");
            }
            
            int opcion = scanner.nextInt();
            int cantidadExtraida;
            float consumoEnergia = planetaActual.getConsumoEnergia();
    
            Inventario inventario = jugador.getInventario();  // Obtener el inventario del jugador
    
            switch (opcion) {
                
                case 1:
                    // Extraer Cristales de Hidrógeno
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();

                    // Extracción
                    cantidadExtraida = planetaActual.extraerRecursos(1);
                    if (cantidadExtraida == -1) {
                        System.out.println("Extracción fallida.");
                    }else{

                        // Gasto de energia del traje
                        jugador.consumirEnergia(cantidadExtraida, consumoEnergia);
                        // Agregar al inventario lo consumido
                        inventario.agregarCristalesHidrogeno(cantidadExtraida);  // Agregar al inventario
                        System.out.println("Has extraído " + cantidadExtraida + " Cristales de Hidrógeno.");
                    }
                    pausa();
                    break;
    
                case 2:
                    // Extraer Flores de Sodio
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();
                    cantidadExtraida = planetaActual.extraerRecursos(2);
                    if (cantidadExtraida == -1) {
                        System.out.println("Extracción fallida.");
                    }else{
                        jugador.consumirEnergia(cantidadExtraida, consumoEnergia);
                        inventario.agregarFloresDeSodio(cantidadExtraida);  // Agregar al inventario
                        System.out.println("Has extraído " + cantidadExtraida + " Flores de Sodio.");
                        
                    }
                    pausa();
                    break;
    
                case 3:
                    // Extraer Uranio (solo si el planeta es Radioactivo)
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();
                    if (planetaActual instanceof Radioactivo) {
                        cantidadExtraida = planetaActual.extraerRecursos(3);
                        if (cantidadExtraida == -1) {
                            System.out.println("Extracción fallida.");
                        } else{
                            jugador.consumirEnergia(cantidadExtraida, consumoEnergia);
                            inventario.agregarUranio(cantidadExtraida);  // Agregar al inventario
                            System.out.println("Has extraído " + cantidadExtraida + " unidades de Uranio.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    pausa();
                    break;
    
                case 4:
                    // Extraer Platino (solo si el planeta es Volcánico)
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();
                    if (planetaActual instanceof Volcanico) {
                        cantidadExtraida = planetaActual.extraerRecursos(4);
                        if (cantidadExtraida == -1) {
                            System.out.println("Extracción fallida.");
                        }else{
    
                            jugador.consumirEnergia(cantidadExtraida, consumoEnergia);
                            inventario.agregarPlatino(cantidadExtraida);  // Agregar al inventario
                            System.out.println("Has extraído " + cantidadExtraida + " unidades de Platino.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    pausa();                    
                    break;
    
                case 0:
                    limpiarPantalla();
                    mostrarDescenso();
                    mostrarInterface();
                    // Volver al estado anterior
                    cambiarEstado(new EstadoVisitandoPlaneta(gsm, jugador, nave, mapa, false));
                    enMenuExtraccion = false;
                    break;
    
                default:
                    System.out.println("Opción no válida.");
                    pausa();
                    mostrarOpciones();
                    break;
            }
        }
    }

    public static void mostrarDescenso() {
    /**
    * Muestra un arte ASCII que representa un descenso visual en la consola.
    * Este arte puede ser utilizado para ilustrar una escena o evento en el juego.
    *
    * Parámetros:
    *   - No recibe parámetros.
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

    private void mostrarInterface() {

        /**
         * Muestra la interfaz del juego con la información del planeta actual y del jugador.
         * Se obtiene el planeta actual del mapa y se imprime su información junto con
         * los recursos disponibles y los atributos del jugador.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
        */

        Planeta planetaActual = mapa.getPlanetaActual();
        System.out.println("==========================================================================================================================");
        System.out.println("|                                             Información del Planeta Actual                                             |");
        System.out.println("==========================================================================================================================");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------|");

        if(planetaActual instanceof CentroGalactico){ 
            System.out.println("Estas en Centro Galactico, osea ganasteee.. Aqui cerrar juego.");
        } else {

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
    }


    

    private void mostrarCabeceraDesplomado(){
        /**
         * Muestra una cabecera decorativa en forma de arte ASCII en la consola.
         * Este arte puede ser utilizado como parte de la interfaz de usuario
         * para hacerla más atractiva visualmente.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("                                  	     ████████████                                                ");
        System.out.println("                                      ██████████████████                                              ");
        System.out.println("                                   ████████████████████████                                           ");
        System.out.println("                                   ████████████████████████                                         ");
        System.out.println("                                 ██████                ██████                                        ");
        System.out.println("                               █████    ███████████████   █████                                      ");
        System.out.println("                               ███   ███               ███   ███                                    ");
        System.out.println("                              ███ ███   ███████████████   ███  ███                                  ");
        System.out.println("                              ██ █   ██████████████████████   █ ██                                  ");
        System.out.println("                             █  █  ██████████████████████████  █  █                                 ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                                 ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                                 ");
        System.out.println("                            ██  █  █████     █████     ██████  █  ██                                 ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                                 ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                                 ");
        System.out.println("                            ██  █  ██████████████████████████  █  ██                                 ");
        System.out.println("                              █  █  ████████████████████████  █  █                                    ");
        System.out.println("                              ███  █  ███████      ███████  █  ███                                  ");
        System.out.println("                              ████  █   ████████████████   █  ████                                  ");
        System.out.println("                               █████ ██  ██████████████  ██ █████                                  ");
        System.out.println("                               ███████ ██████████████████ ███████                                   ");
        System.out.println("                              █  ██████████████████████████████  █                                   ");
        System.out.println("                           ██  ██ ████████████████████████████ ██  ██                                ");
        System.out.println("                           ████ ██  █████   █     █   █████  ██ █████                               ");
        System.out.println("                         ████████ ██  ███   █     █   ████  ██ ████████                             ");
        System.out.println("                         ██████████ █ ████████████████████ █ ██████████                             ");
        System.out.println("                       █████████████  ████████████████████  █████████████                           ");
        System.out.println("                      ██████████████  █  █            █  █  ██████████████                          ");
        System.out.println("                     ███████████████  █  ██████████████  █  ███████████████                        ");
        System.out.println("                   █████████          ████            ████          █████████                       ");
        System.out.println("                   █████████  ██████  ███ ████████████ ███  ██████  █████████                      ");
        System.out.println("                 ███████████  █    █  ███ ██        ██ ███  █    █  ██████████                     ");
        System.out.println("                ████████████  ██████  ███ ████████████ ███  ██████  ███████████                    ");
        System.out.println("                ████████████          ███ ██        ██ ███          ███████████                   ");
        System.out.println("                ████████████████████  ███ ████████████ ███  ███████████████████                   ");
    }
}
