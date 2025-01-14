package usm.vjimenez.nojavasky;

import usm.vjimenez.nojavasky.hud.GameStateManager;

public class NoJavaSky {
    /**
     * Clase principal del juego NoJavaSky.
     * 
     * Esta clase contiene el método main, que es el punto de entrada del juego.
     * Se encarga de inicializar el gestor de estados del juego (GameStateManager)
     * y de mostrar el menú principal, permitiendo al usuario interactuar con él.
     */
    public static void main(String[] args) {
        GameStateManager gameStateManager = new GameStateManager(); // Inicializa el GameStateManager
        gameStateManager.actualizar(); // Muestra el menú principal y permite al usuario interactuar
    }
}
 