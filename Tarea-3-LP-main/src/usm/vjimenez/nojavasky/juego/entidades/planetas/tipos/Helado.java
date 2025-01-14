package usm.vjimenez.nojavasky.juego.entidades.planetas.tipos;

import usm.vjimenez.nojavasky.juego.entidades.Alienigena;
import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tieneAsentamientos;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;

public class Helado extends Planeta implements tieneAsentamientos {

    //*************************************************** ATRIBUTOS ***************************************************//

    private int temperatura;
    private Alienigena alienigenaPersistente; 

    //*************************************************** CONSTRUCTOR ***************************************************//

    public Helado(int radio, int cristalesHidrogeno, int floresDeSodio, int temperatura, float consumoEnergia) {
        // Llamada al constructor de la superclase Planeta
        super(radio, cristalesHidrogeno, floresDeSodio, 0, 0, consumoEnergia); // Pasando 0 para uranio y platino
        this.temperatura = temperatura; // Inicializar el atributo temperatura
        this.setTipo("Helado");
        this.setDescripcion("Estos planetas son una tundra congelada, donde uno siempre verá nieve y hielo. "
                + "Son muy ricos en Cristales de Hidrógeno y puede haber asentamientos de especies "
                + "alienígenas que están dispuestas a intercambiar recursos por mejoras para tu exotraje "
                + "o para tu nave.");
    }
    //*************************************************** GETTERS ***************************************************//
    public int getTemperatura() {
        return temperatura;
    }
    public Alienigena getAlienigenaPersistente() {
        return alienigenaPersistente;
    }
    //*************************************************** SETTERS ***************************************************//
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    public void setAlienigenaPersistente(Alienigena alienigenaPersistente) {
        this.alienigenaPersistente = alienigenaPersistente;
    }

    //*************************************************** METODOS ***************************************************//
   
    // Métodos de la clase abstracta Planeta
    @Override
    public boolean visitar(Jugador jugador) {
        /**
         * Método que permite a un jugador visitar el planeta.
         * Este método debe ser implementado en las subclases de Planeta 
         * para definir la lógica específica de interacción entre el 
         * jugador y el planeta.
         *
         * @param jugador El jugador que está intentando visitar el planeta.
         * @return true si la visita se realizó con éxito, false en caso contrario.
         */
        // Implementación por definir
        return true;
    }

    @Override
    public int extraerRecursos(int tipo) {
        /**
         * Método que permite extraer recursos del planeta basado en el tipo especificado.
         * Este método debe ser implementado en las subclases de Planeta para definir la 
         * lógica específica de extracción de recursos en función de su tipo.
         *
         * @param tipo El tipo de recurso que se desea extraer.
         * @return La cantidad de recursos extraídos.
         */
        // Implementación por definir
        return super.extraerRecursos(tipo);
    }

    @Override
    public boolean salir() {
        /**
         * Método que permite salir del planeta actual.
         * Este método debe ser implementado en las subclases de Planeta para definir
         * la lógica específica de salida de cada tipo de planeta.
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
         * @return true si el planeta tiene asentamientos, false en caso contrario.
         */
        return true; // Este planeta tiene asentamientos
    }

    // Método de la interfaz tieneAsentamientos
    @Override
    public void visitarAsentamientos(Jugador jugador) {
        /**
         * Permite al jugador visitar asentamientos en el planeta.
         * Si no existe un alienígena persistente, se genera uno nuevo.
         *
         * @param jugador El jugador que visita los asentamientos.
         */
        // Si no existe un alienígena persistente, lo generamos
        if (alienigenaPersistente == null) {
            Alienigena generador = new Alienigena("", "Glaciarido", " ", " ", " "); // Crear una instancia
            alienigenaPersistente = generador.generarAlienigena();  // Generar el alienígena
        }

    }

}

