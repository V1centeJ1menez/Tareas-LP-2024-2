package usm.vjimenez.nojavasky.juego.entidades.planetas;

import usm.vjimenez.nojavasky.juego.controladores.Jugador;

public interface tieneAsentamientos {

    //*************************************************** METODOS ***************************************************//

    /**
     * Método que verifica si el planeta tiene asentamientos.
     * 
     * Este método determina si el planeta actual cuenta con asentamientos
     * habitados. La implementación puede variar dependiendo de la lógica 
     * del juego. Este método es útil para decidir si el jugador puede 
     * interactuar con los asentamientos existentes en el planeta.
     *
     * @return true si el planeta tiene asentamientos; false en caso contrario.
     */

    boolean verificartieneAsentamientos();

    /**
     * Método que permite al jugador visitar los asentamientos en un planeta.
     * 
     * Si el planeta tiene asentamientos habitados, este método permite que el
     * jugador los visite. Durante la visita, el jugador podría interactuar con
     * alienígenas y recibir ofertas de mejoras u otros beneficios. 
     * 
     * Si no existe un alienígena persistente asociado a los asentamientos, este
     * método generará uno.
     *
     * @param jugador El jugador que está visitando los asentamientos.
     */
    void visitarAsentamientos(Jugador jugador);
}

