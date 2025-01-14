package usm.vjimenez.nojavasky.juego.inventario;

import usm.vjimenez.nojavasky.hud.estados.GameState;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import usm.vjimenez.nojavasky.juego.controladores.Nave;
import java.util.Scanner;

public class Inventario {

    //*************************************************** ATRIBUTOS ***************************************************//
    private int cristalesHidrogeno;
    private int floresDeSodio;
    private int uranio;
    private int platino;

    private Scanner scanner = new Scanner(System.in);


    //*************************************************** CONSTRUCTOR ***************************************************//
    public Inventario(int cristalesHidrogeno, int floresDeSodio, int uranio, int platino) {
        this.cristalesHidrogeno = cristalesHidrogeno;
        this.floresDeSodio = floresDeSodio;
        this.uranio = uranio;
        this.platino = platino;
    }

    //*************************************************** GETTERS ***************************************************//
    public int getCristalesHidrogeno() {
        return cristalesHidrogeno;
    }

    public int getFloresDeSodio() {
        return floresDeSodio;
    }

    public int getUranio() {
        return uranio;
    }

    public int getPlatino() {
        return platino;
    }
    public Scanner getScanner() {
        return scanner;
    }

    //*************************************************** SETTERS ***************************************************//
    public void setCristalesHidrogeno(int cristalesHidrogeno) {
        this.cristalesHidrogeno = cristalesHidrogeno;
    }

    public void setFloresDeSodio(int floresDeSodio) {
        this.floresDeSodio = floresDeSodio;
    }

    public void setUranio(int uranio) {
        this.uranio = uranio;
    }

    public void setPlatino(int platino) {
        this.platino = platino;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    //*************************************************** METODOS ***************************************************//

    // Métodos para agregar recusos al inventario
    public void agregarCristalesHidrogeno(int cantidad) {
        /**
         * Método para agregar cristales de hidrógeno al inventario del jugador.
         *
         * Este método permite incrementar la cantidad de cristales de hidrógeno en el inventario 
         * del jugador, siempre y cuando la cantidad a agregar sea positiva.
         *
         * @param cantidad La cantidad de cristales de hidrógeno a agregar al inventario.
         */
        if (cantidad > 0) {
            this.cristalesHidrogeno += cantidad;
        }
    }

    public void agregarFloresDeSodio(int cantidad) {
        /**
         * Método para agregar flores de sodio al inventario del jugador.
         *
         * Este método permite incrementar la cantidad de flores de sodio en el inventario 
         * del jugador, asegurándose de que la cantidad a agregar sea positiva.
         *
         * @param cantidad La cantidad de flores de sodio a agregar al inventario.
         */
        if (cantidad > 0) {
            this.floresDeSodio += cantidad;
        }
    }

    public void agregarUranio(int cantidad) {
        /**
         * Método para agregar uranio al inventario del jugador.
         *
         * Este método permite incrementar la cantidad de uranio en el inventario 
         * del jugador, siempre y cuando la cantidad a agregar sea positiva.
         *
         * @param cantidad La cantidad de uranio a agregar al inventario.
         */
        if (cantidad > 0) {
            this.uranio += cantidad;
        }
    }

    public void agregarPlatino(int cantidad) {
        /**
         * Método para agregar platino al inventario del jugador.
         *
         * Este método permite incrementar la cantidad de platino en el inventario 
         * del jugador, asegurando que la cantidad a agregar sea positiva.
         *
         * @param cantidad La cantidad de platino a agregar al inventario.
         */
        if (cantidad > 0) {
            this.platino += cantidad;
        }
    }

    // Métodos para retirar recursos del inventario
    public void retirarCristalesHidrogeno(int cantidad) {
        /**
         * Método para retirar cristales de hidrógeno del inventario del jugador.
         *
         * Este método permite restar una cantidad específica de cristales de hidrógeno del inventario 
         * del jugador, asegurándose de que la cantidad a retirar sea positiva y que el jugador tenga 
         * suficiente cristales de hidrógeno disponible para realizar la operación.
         *
         * @param cantidad La cantidad de cristales de hidrógeno a retirar del inventario.
         */
        if (cantidad > 0 && this.cristalesHidrogeno >= cantidad) {
            this.cristalesHidrogeno -= cantidad;
        }
    }

    public void retirarFloresDeSodio(int cantidad) {
        /**
         * Método para retirar flores de sodio del inventario del jugador.
         *
         * Este método permite restar una cantidad específica de flores de sodio del inventario 
         * del jugador, asegurándose de que la cantidad a retirar sea positiva y que el jugador tenga 
         * suficientes flores de sodio disponible para realizar la operación.
         *
         * @param cantidad La cantidad de flores de sodio a retirar del inventario.
         */
        if (cantidad > 0 && this.floresDeSodio >= cantidad) {
            this.floresDeSodio -= cantidad;
        }
    }

    public void retirarUranio(int cantidad) {
        /**
         * Método para retirar uranio del inventario del jugador.
         *
         * Este método permite restar una cantidad específica de uranio del inventario 
         * del jugador, asegurándose de que la cantidad a retirar sea positiva y que el jugador tenga 
         * suficiente uranio disponible para realizar la operación.
         *
         * @param cantidad La cantidad de uranio a retirar del inventario.
         */
        if (cantidad > 0 && this.uranio >= cantidad) {
            this.uranio -= cantidad;
        }
    }

    public void retirarPlatino(int cantidad) {
        /**
         * Método para retirar platino del inventario del jugador.
         *
         * Este método permite restar una cantidad específica de platino del inventario 
         * del jugador, asegurándose de que la cantidad a retirar sea positiva y que el jugador tenga 
         * suficiente platino disponible para realizar la operación.
         *
         * @param cantidad La cantidad de platino a retirar del inventario.
         */
        if (cantidad > 0 && this.platino >= cantidad) {
            this.platino -= cantidad;
        }
    }

    // Método para mostrar el estado del inventario con presentación mejorada
    public void mostrarInventario(Jugador jugador, Nave nave) {
        /**
         * Método para mostrar el estado del inventario del jugador con una presentación mejorada.
         * 
         * Este método muestra el inventario del jugador, incluyendo la cantidad de 
         * cristales de hidrógeno, flores de sodio, uranio y platino, de manera estructurada 
         * en una interfaz visual amigable. Siempre se muestran los cristales de hidrógeno y 
         * las flores de sodio, mientras que el uranio y el platino solo se muestran si hay 
         * cantidades mayores a cero.
         *
         * Además, se muestra la energía actual del exotraje del jugador y el combustible 
         * actual de la nave. El jugador tiene opciones para recargar la energía del exotraje 
         * utilizando flores de sodio, recargar el combustible de la nave utilizando cristales 
         * de hidrógeno o volver al menú anterior.
         *
         * El método utiliza un bucle que permite al jugador interactuar hasta que elija salir 
         * del menú de inventario. La entrada del jugador se gestiona mediante un `Scanner`, 
         * y se proporciona retroalimentación para opciones no válidas.
         *
         * @param jugador El objeto Jugador que contiene información sobre el estado del jugador.
         * @param nave El objeto Nave que contiene información sobre el estado de la nave.
         */
        boolean enMenuInventario = true;

        while (enMenuInventario) {
            GameState.limpiarPantalla();
            System.out.println("\n=== Inventario del Jugador ===");
            System.out.println("┌─────────────────────────────┐");
            
            // Mostrar Cristales de Hidrógeno siempre
            System.out.printf("│ %-20s: %4d │\n", "Cristales de Hidrógeno", cristalesHidrogeno);
            
            // Mostrar Flores de Sodio siempre
            System.out.printf("│ %-20s: %4d │\n", "Flores de Sodio", floresDeSodio);
            
            // Mostrar Uranio sólo si el jugador tiene más de 0
            if (uranio > 0) {
                System.out.printf("│ %-20s: %4d │\n", "Uranio", uranio);
            }
            
            // Mostrar Platino sólo si el jugador tiene más de 0
            if (platino > 0) {
                System.out.printf("│ %-20s: %4d │\n", "Platino", platino);
            }

            System.out.println("└─────────────────────────────┘");
            
            // Mostrar combustible y energia actual
            System.out.println("Energia actual de traje: " + jugador.getUnidadesEnergiaProteccion());
            System.out.println("Combustible actual de nave: " + nave.getUnidadesCombustible());

            // Mostrar opciones adicionales para recargar y volver
            System.out.println("\nOpciones:");
            System.out.println("[1] Recargar energía de exotraje (Flores de Sodio)");
            System.out.println("[2] Recargar combustible de nave (Cristales de Hidrogeno)");
            System.out.println("[3] Volver");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Recargar energía del exotraje
                    recargarEnergiaExotraje(jugador);
                    GameState.pausa();
                    break;

                case 2:
                    // Recargar combustible de la nave
                    recargarCombustibleNave(nave);
                    GameState.pausa();
                    break;

                case 3:
                    // Volver al menú anterior
                    enMenuInventario = false;
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    // Método para recargar energía del exotraje usando sodio
    private void recargarEnergiaExotraje(Jugador jugador) {
        /**
         * Método para recargar la energía del exotraje utilizando flores de sodio.
         * 
         * Este método permite al usuario ingresar la cantidad de flores de sodio que 
         * desea utilizar para recargar la energía del exotraje. Si hay flores de sodio 
         * disponibles, se solicita al usuario la cantidad. Se valida que la cantidad 
         * ingresada sea mayor que cero y que no exceda la cantidad de flores disponibles.
         * 
         * Si la cantidad es válida, se realiza la recarga de energía en la clase Jugador 
         * y se restan las flores utilizadas del inventario del jugador. En caso contrario, 
         * se informará al usuario de la cantidad no válida o de la falta de flores de sodio.
         *
         * @param jugador El jugador cuyo exotraje se recargará.
         */
        if (floresDeSodio > 0) {
            System.out.print("¿Cuántas flores de sodio desea utilizar para recargar el exotraje? ");
            int cantidad = scanner.nextInt();

            if (cantidad > 0 && cantidad <= floresDeSodio) {
                // Realizar la recarga en la clase Jugador
                jugador.recargarEnergiaProteccion(cantidad);
                retirarFloresDeSodio(cantidad); // Restar las flores del inventario
                System.out.println("Has recargado la energía del exotraje.");
            } else {
                System.out.println("Cantidad no válida.");
            }
        } else {
            System.out.println("No tienes suficiente flores de sodio para recargar el exotraje.");
        }
    }

    // Método para recargar combustible de la nave usando hidrógeno
    private void recargarCombustibleNave(Nave nave) {
        /**
         * Método para recargar el combustible de la nave utilizando cristales de hidrógeno.
         * 
         * Este método permite al usuario ingresar la cantidad de cristales de hidrógeno que 
         * desea utilizar para recargar los propulsores de la nave. Si hay cristales de hidrógeno 
         * disponibles, se solicita la cantidad al usuario. Se valida que la cantidad ingresada 
         * sea mayor que cero y que no exceda la cantidad de cristales disponibles. 
         * 
         * Si la cantidad es válida, se realiza la recarga de combustible en la clase Nave 
         * y se restan los cristales utilizados del inventario del jugador. En caso contrario, 
         * se informará al usuario de la cantidad no válida o de la falta de cristales.
         *
         * @param nave La nave cuya combustible se recargará.
         */
        if (cristalesHidrogeno > 0) {
            System.out.print("¿Cuántos cristales de hidrógeno desea utilizar para recargar la nave? ");
            int cantidad = scanner.nextInt();

            if (cantidad > 0 && cantidad <= cristalesHidrogeno) {
                // Realizar la recarga en la clase Nave
                nave.recargarPropulsores(cantidad);
                retirarCristalesHidrogeno(cantidad); // Restar los cristales del inventario
                System.out.println("Has recargado el combustible de la nave.");
            } else {
                System.out.println("Cantidad no válida.");
            }
        } else {
            System.out.println("No tienes suficientes cristales de hidrógeno para recargar la nave.");
        }
    }
    
}
