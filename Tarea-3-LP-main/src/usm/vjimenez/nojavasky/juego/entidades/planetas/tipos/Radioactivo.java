package usm.vjimenez.nojavasky.juego.entidades.planetas.tipos;

import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;

public class Radioactivo extends Planeta {

    //*************************************************** ATRIBUTOS ***************************************************//

    private int radiacion;
    private int uranio;

    //*************************************************** CONSTRUCTOR ***************************************************//

    public Radioactivo(int radio, int cristalesHidrogeno, int floresDeSodio, int radiacion, int uranio, float consumoEnergia) {
        // Llamada al constructor de la superclase Planeta
        super(radio, cristalesHidrogeno, floresDeSodio, uranio, 0, consumoEnergia); // Pasar '0' para platino si no se usa
        this.radiacion = radiacion;
        this.setTipo("Radioactivo");
        this.setDescripcion("Estos planetas están llenos de vida muy exótica. Pero la atmósfera es muy densa en "
            + "componentes radiactivos incompatibles con tu sistema, por lo que el traje consume "
            + "mucha energía para evitar los efectos letales. Esta radiación proviene de los grandes "
            + "yacimientos ricos en Uranio, un elemento muy cotizado por los asentamientos de otros "
            + "planetas, se puede intercambiar este elemento por mejoras en los asentamientos.");
    }

    //*************************************************** GETTERS ***************************************************//

    public int getUranio() {
        return uranio;
    }
    public int getRadiacion() {
        return radiacion;
    }

    //*************************************************** SETTERS ***************************************************//

    public void setUranio(int uranio) {
        this.uranio = uranio;
    }

    public void setRadiacion(int radiacion) {
        this.radiacion = radiacion;
    }

    //*************************************************** METODOS ***************************************************//
    @Override
    public boolean visitar(Jugador jugador) {
        /**
         * Permite al jugador visitar el planeta.
         *
         * Este método debe ser implementado para definir la lógica que ocurre
         * cuando un jugador visita este planeta en particular. Actualmente, 
         * se retorna true, lo que indica que la visita fue exitosa.
         *
         * @param jugador El jugador que está visitando el planeta.
         * @return true si la visita fue exitosa, false si hay condiciones que
         *         impiden la visita (implementación por definir).
         */
        // Implementación por definir
        return true;
    }

    @Override
    public int extraerRecursos(int tipo) {
        /**
         * Extrae recursos del planeta según el tipo especificado.
         *
         * Este método permite la extracción de recursos del planeta. 
         * Si el tipo de recurso es 3 (Platino), se imprime un mensaje específico 
         * y se delega la lógica de extracción a la implementación en la clase padre.
         *
         * @param tipo El tipo de recurso a extraer. 
         *             (Por ejemplo, 3 para Platino.)
         * @return La cantidad de recursos extraídos. 
         *         Si el tipo no es válido, se retorna 0.
         */
        if (tipo == 3) { // Platino
            System.out.println("¡Extrayendo Uranio del planeta Radioactivo!");;
            return super.extraerRecursos(tipo); // Delegamos la lógica de extracción
        }
        return super.extraerRecursos(tipo); // Si no es un recurso válido, no se extrae nada
    }

    @Override
    public boolean salir() {
        /**
         * Permite salir del planeta actual.
         *
         * Este método proporciona la funcionalidad para que un jugador 
         * abandone el planeta en el que se encuentra. Actualmente, 
         * no se ha implementado ninguna lógica adicional, 
         * por lo que siempre retorna true, indicando que la salida es exitosa.
         *
         * @return true si la salida se ha realizado con éxito, 
         *         false si hay condiciones que impiden la salida (no implementado).
         */
        // Implementación por definir
        return true;
    }

}
