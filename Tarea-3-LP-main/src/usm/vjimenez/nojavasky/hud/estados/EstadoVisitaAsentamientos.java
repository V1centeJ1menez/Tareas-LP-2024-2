package usm.vjimenez.nojavasky.hud.estados;

import usm.vjimenez.nojavasky.hud.GameStateManager;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.MapaGalactico;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import usm.vjimenez.nojavasky.juego.entidades.Alienigena;
import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tieneAsentamientos;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.Helado;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.Oceanico;
import usm.vjimenez.nojavasky.juego.inventario.Inventario;
import usm.vjimenez.nojavasky.utilidad.RandomNumberGenerator;
import java.util.Scanner;


public class EstadoVisitaAsentamientos extends GameState {

    //*************************************************** ATRIBUTOS ***************************************************//

    private Alienigena alienigena;  // Para almacenar el alienígena con el que el jugador interactuará

    //*************************************************** CONSTRUCTOR ***************************************************//
    
    public EstadoVisitaAsentamientos(GameStateManager gsm, Jugador jugador, Nave nave, MapaGalactico mapa) {
        super(gsm, jugador, nave, mapa);  // Llama al constructor de GameState
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
         * Muestra las opciones disponibles para el jugador cuando visita un asentamiento
         * en un planeta. Este método presenta las interacciones posibles con un
         * alienígena persistente, incluyendo la aceptación de ofertas para mejoras
         * a cambio de recursos.
         *
         * El método realiza las siguientes acciones:
         * - Limpia la pantalla y muestra información sobre el planeta actual.
         * - Verifica si el planeta tiene asentamientos y, si es así, permite al
         *   jugador interactuar con el alienígena.
         * - Genera valores aleatorios para la cantidad de recursos necesarios para
         *   aceptar la oferta del alienígena.
         * - Permite al jugador aceptar o rechazar la oferta.
         *
         * Parámetros:
         *   - No recibe parámetros.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        boolean enMenuAsentamiento = true;
        Planeta planetaActual = mapa.getPlanetaActual();  // Obtiene el planeta actual
        limpiarPantalla();
        
        while (enMenuAsentamiento) {
            limpiarPantalla();
            EstadoVisitandoPlaneta.mostrarDescenso();
            if (planetaActual instanceof tieneAsentamientos) {
                // Obtiene el alienígena persistente del planeta actual
                ((tieneAsentamientos) planetaActual).visitarAsentamientos(jugador); 

                // Si el planeta es de tipo Helado
                if (planetaActual instanceof Helado) {
                    Helado helado = (Helado) planetaActual;
                    alienigena = helado.getAlienigenaPersistente();
                } 
                // Si el planeta es de tipo Oceanico
                else if (planetaActual instanceof Oceanico) {
                    Oceanico oceanico = (Oceanico) planetaActual;
                    alienigena = oceanico.getAlienigenaPersistente();
                }

                System.out.println(alienigena.getArteASCII());
                System.out.println(alienigena.getMensaje());
                System.out.println(alienigena.getOferta());

                // Generar valores aleatorios para platino y uranio necesarios para el intercambio
                // Obtener la primera oferta del alienígena
                String oferta = alienigena.getOferta(); 
                int cantidadPlatino = RandomNumberGenerator.rand(1, 10); // Por ejemplo, entre 1 y 10
                int cantidadUranio = RandomNumberGenerator.rand(1,10);  // Por ejemplo, entre 1 y 5

                // Mostrar cantidades requeridas
                System.out.println("Para aplicar la mejora necesitas " + cantidadPlatino + " unidades de Platino y " + cantidadUranio + " unidades de Uranio.");
                

                // Muestra el menú de opciones
                System.out.println("ADVERTENCIA: Las ofertas con locatarios son a ciegas, por lo que el resultado de aceptar puede ser injusta.");
                System.out.println("[1] Aceptar");
                System.out.println("[2] Volver a la nave");

                // Captura la opción del jugador
                int opcion = scanner.nextInt();
              
                switch (opcion) {
                    case 1:  // Si el jugador acepta la oferta
                        aceptarOferta(cantidadPlatino, cantidadUranio, oferta);
                        pausa();
                        volverANave();
                        enMenuAsentamiento = false;  // Sale del bucle
                        break;
                    case 2:  // Si el jugador decide volver a la nave
                        volverANave();
                        pausa();
                        enMenuAsentamiento = false;  // Sale del bucle
                        break;
                    default:
                        System.out.println("Opción no válida, por favor elige 1 o 2.");
                        break;
                }
            } else {
                System.out.println("No hay asentamientos en este planeta.");
                enMenuAsentamiento = false;  // Salimos del bucle si el planeta no tiene asentamientos
            }
        }
    }

   private void aceptarOferta(int cantidadPlatino, int cantidadUranio, String oferta) {
    /**
     * Maneja la aceptación de una oferta de un alienígena, verificando si el jugador
     * tiene suficientes recursos para completar el intercambio. Si el intercambio
     * es exitoso, aplica las mejoras correspondientes a la nave o al traje del jugador.
     *
     * El método realiza las siguientes acciones:
     * - Imprime un mensaje indicando que el jugador ha aceptado negociar.
     * - Verifica si el jugador tiene suficientes unidades de platino y uranio
     *   para realizar la oferta.
     * - Si los recursos son suficientes, los retira del inventario y aplica
     *   la mejora correspondiente según la oferta del alienígena.
     * - Si no hay recursos suficientes, muestra un mensaje informando al jugador.
     *
     * Parámetros:
     *   - cantidadPlatino: la cantidad de platino requerida para el intercambio.
     *   - cantidadUranio: la cantidad de uranio requerida para el intercambio.
     *   - oferta: una cadena que describe la oferta del alienígena.
     *
     * Devuelve:
     *   - No devuelve ningún valor.
     */
        System.out.println("Has aceptado negociar con " + alienigena.getNombre());

        // Verificar si el jugador tiene suficientes recursos
        Inventario inventario = jugador.getInventario();
        if (inventario.getPlatino() >= cantidadPlatino && inventario.getUranio() >= cantidadUranio) {
            // Retirar los recursos del inventario
            inventario.retirarPlatino(cantidadPlatino);
            inventario.retirarUranio(cantidadUranio);
            System.out.println("Intercambio realizado con éxito.");

            // Aplicar mejoras según la oferta
            if (oferta.toLowerCase().contains("**capacidad de tu nave**")) {
                nave.mejorarCapacidad();
                System.out.println("Se ha mejorado la capacidad de la nave.");
            }else if(oferta.toLowerCase().contains("**eficiencia de tu nave**")){
                nave.mejorarEficiencia();
                System.out.println("Se ha mejorado la eficiencia de la nave.");

            } else if (oferta.toLowerCase().contains("**capacidad de energía de tu traje**")) {
                jugador.mejorarCapacidad();
                System.out.println("Se ha mejorado la capacidad del traje.");
            } else if (oferta.toLowerCase().contains("**capacidad de energía de tu traje**")) {
                jugador.mejorarEficiencia();
                System.out.println("Se ha mejorado la eficiencia del traje.");
            } else {
                System.out.println("No se ha encontrado una mejora válida para aplicar.");
            }
        } else {
            System.out.println("No tienes suficientes recursos para completar el intercambio.");
        }
    };



    // Método para volver a la nave (cambia el estado al estado de "Visitando Planeta")
    private void volverANave() {
        /**
         * Cambia el estado del juego al de "Visitando Planeta", simulando el regreso del jugador a la nave.
         * Este método imprime un mensaje indicando que el jugador está volviendo a la nave 
         * y luego actualiza el estado del juego a uno que permite al jugador continuar explorando.
         *
         * Devuelve:
         *   - No devuelve ningún valor.
         */
        System.out.println("Volviendo a la nave...");
        cambiarEstado(new EstadoVisitandoPlaneta(gsm, jugador, nave, mapa, false));  // Cambia al estado "Visitando Planeta"
    }
}
