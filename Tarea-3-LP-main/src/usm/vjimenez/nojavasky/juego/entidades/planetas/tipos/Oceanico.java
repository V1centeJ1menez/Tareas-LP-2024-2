package usm.vjimenez.nojavasky.juego.entidades.planetas.tipos;

import usm.vjimenez.nojavasky.juego.entidades.Alienigena;
import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tieneAsentamientos;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;


public class Oceanico extends Planeta implements tieneAsentamientos {

    //*************************************************** ATRIBUTOS ***************************************************//

    private int profundidad;
    private Alienigena alienigenaPersistente;

    //*************************************************** CONSTRUCTOR ***************************************************//

    public Oceanico(int radio, int cristalesHidrogeno, int floresDeSodio, int profundidad, float consumoEnergia) {
        // Llamada al constructor de la superclase Planeta
        super(radio, cristalesHidrogeno, floresDeSodio, 0, 0, consumoEnergia); // Pasando 0 para uranio y platino
        this.profundidad = profundidad;
        this.setTipo("Oceanico");
        this.setDescripcion("Estos planetas son un gran océano, rico en vida acuática. Pero todos los recursos de "
                + "valor están en las profundidades, si decides aventurarte bajo el agua, deberás considerar "
                + "energía para poder respirar. Son ricos en Flores de Sodio y puede haber "
                + "asentamientos de especies alienígenas que están dispuestos a intercambiar recursos "
                + "por mejoras a tu exotraje o para tu nave.");
    }

    //*************************************************** GETTERS ***************************************************//

    public int getProfundidad() {
        return profundidad;
    }
    public Alienigena getAlienigenaPersistente() {
        return alienigenaPersistente;
    }

    //*************************************************** SETTERS ***************************************************//

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
    public void setAlienigenaPersistente(Alienigena alienigenaPersistente) {
        this.alienigenaPersistente = alienigenaPersistente;
    }

    //*************************************************** METODOS ***************************************************//

    // Métodos de la clase abstracta Planeta
    @Override
    public boolean visitar(Jugador jugador) {
        /**
         * Permite al jugador visitar el planeta.
         * La implementación concreta de este método debe definir 
         * la lógica específica de la visita dependiendo del tipo de planeta.
         *
         * @param jugador El jugador que visita el planeta.
         * @return true si la visita fue exitosa; false en caso contrario.
         */
        // Implementación por definir
        return true;
    }

    @Override
    public int extraerRecursos(int tipo) {
        /**
         * Extrae recursos del planeta según el tipo especificado.
         * La implementación concreta de este método debe definir 
         * la lógica específica para la extracción de recursos 
         * según el tipo indicado.
         *
         * @param tipo El tipo de recurso que se desea extraer.
         * @return La cantidad de recursos extraídos del planeta.
         */
        // Implementación por definir
        return super.extraerRecursos(tipo);
    }

    @Override
    public boolean salir() {
        /**
         * Permite salir del planeta actual.
         * La implementación concreta de este método debe definir 
         * la lógica específica que se ejecuta al salir de un planeta,
         * como la actualización del estado del jugador o la transición 
         * a otro estado del juego.
         *
         * @return true si la salida fue exitosa, false en caso contrario.
         */
        // Implementación por definir
        return true;
    }

    @Override
    public boolean verificartieneAsentamientos() {
        /**
         * Verifica si el planeta tiene asentamientos.
         *
         * Este método devuelve true si el planeta tiene asentamientos
         * establecidos, permitiendo que los jugadores interactúen con 
         * ellos. En la implementación actual, se asume que este planeta
         * siempre tiene asentamientos.
         *
         * @return true si el planeta tiene asentamientos, false en caso contrario.
         */
        return true; // Este planeta tiene asentamientos
    }

    @Override
    public void visitarAsentamientos(Jugador jugador) {
        /**
         * Permite al jugador visitar asentamientos en el planeta.
         *
         * Este método verifica si ya existe un alienígena persistente asociado
         * al planeta. Si no existe, genera un nuevo alienígena de tipo "Aqualuxian".
         * Esto permite al jugador interactuar con el alienígena durante su visita.
         *
         * @param jugador El jugador que está visitando los asentamientos.
         */
        // Si no existe un alienígena persistente, lo generamos
        if (alienigenaPersistente == null) {
            Alienigena generador = new Alienigena("", "Aqualuxian", " ", " ", " "); // Crear una instancia
            alienigenaPersistente = generador.generarAlienigena();  // Generar el alienígena
        }

    }

}

