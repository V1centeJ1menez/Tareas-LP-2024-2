package usm.vjimenez.nojavasky.juego.entidades.planetas.tipos;

import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;

public class Volcanico extends Planeta {

    //*************************************************** ATRIBUTOS ***************************************************//

    private int temperatura;
    private int platino;

    //*************************************************** CONSTRUCTOR ***************************************************//

    public Volcanico(int radio, int cristalesHidrogeno, int floresDeSodio, int platino, int temperatura, float consumoEnergia) {
        // Llamar al constructor de la superclase (Planeta)
        super(radio, cristalesHidrogeno, floresDeSodio, platino, temperatura, consumoEnergia);
        
        // Inicializar atributos específicos de Volcanico
        this.temperatura = temperatura;
        this.platino = platino;
        this.setTipo("Volcanico");
        this.setDescripcion("Este planeta de lejos puede parecer un sol, si no fuese por la nube de cenizas que "
                + "cubre toda la superficie. Los grandes volcanes producen un aumento en la temperatura "
                + "de la superficie que es incapaz de albergar vida, por lo que el exotraje lucha por tu "
                + "supervivencia. La lava de estos planetas al solidificarse es rica en Platino, un "
                + "elemento muy cotizado por los asentamientos de otros planetas, se puede intercambiar "
                + "este elemento por mejoras en los asentamientos.");
    }

    //*************************************************** GETTERS ***************************************************//

    public int getPlatino() {
        return platino;
    }

    public int getTemperatura() {
        return temperatura;
    }

    //*************************************************** SETTERS ***************************************************//

    public void setPlatino(int platino) {
        this.platino = platino;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    //*************************************************** METODOS ***************************************************//

    @Override
    public boolean visitar(Jugador jugador) {
        /**
         * Permite al jugador visitar el planeta actual.
         *
         * Este método define la lógica que se ejecuta cuando un jugador 
         * decide visitar este planeta. Actualmente, no se ha implementado 
         * ninguna lógica específica, y siempre retorna true, 
         * indicando que la visita se ha realizado con éxito.
         *
         * @param jugador El objeto Jugador que está intentando visitar el planeta.
         * @return true si la visita se ha realizado con éxito, 
         *         false si hay condiciones que impiden la visita (no implementado).
         */
        // Implementación por definir
        return true;
    }

    @Override
    public int extraerRecursos(int tipo) {
        /**
         * Extrae recursos del planeta basado en el tipo especificado.
         *
         * Este método verifica el tipo de recurso solicitado por el jugador
         * y, si es del tipo correcto (en este caso, Platino), ejecuta la lógica 
         * de extracción de recursos. Si el tipo no es válido, no se realiza
         * ninguna extracción y se delega la llamada al método de la superclase.
         *
         * @param tipo El tipo de recurso a extraer. 
         *             Debe ser un entero que representa el tipo de recurso.
         *             En este caso, 4 representa el Platino.
         * @return La cantidad de recursos extraídos, o 0 si el tipo no es válido.
         */
        if (tipo == 4) { // Platino
            System.out.println("¡Extrayendo Plantino del planeta volcanico!");
            return super.extraerRecursos(tipo); // Delegamos la lógica de extracción
        }
        return super.extraerRecursos(tipo); // Si no es un recurso válido, no se extrae nada
    }

    @Override
    public boolean salir() {
        /**
         * Método que maneja la salida del jugador de este planeta.
         * 
         * Este método puede realizar la limpieza de recursos o estados
         * asociados a la interacción con el planeta. Además, puede proporcionar
         * una narrativa sobre lo que ocurre al salir.
         * 
         * @return true si la salida fue exitosa; false si no se puede salir.
         */
        // Implementación por definir
        return true;
    }

 
}
