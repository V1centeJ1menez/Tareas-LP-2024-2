package usm.vjimenez.nojavasky.juego.entidades.planetas.tipos;

import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;
import usm.vjimenez.nojavasky.juego.controladores.Jugador;

public class CentroGalactico extends Planeta {

    //*************************************************** CONSTRUCTOR ***************************************************//
    public CentroGalactico() {
        // Llamada al constructor de la superclase Planeta con valores predeterminados
        super(0, 0, 0, 0, 0, 0); // Pasando 0 para todos los atributos de la superclase
        this.setTipo("Centro Galáctico");
        this.setDescripcion("Solo existe un centro galáctico. Es un lugar muy hostil, estrellas parecen orbitar "
                + "un objeto estelar desconocido, y solo las mejores naves espaciales pueden llegar. Para "
                + "poder visitar este lugar es necesario una nave con una eficiencia de propulsor sobre "
                + "el 50%.");
    }


    //*************************************************** METODOS ***************************************************//
    // Métodos de la clase abstracta Planeta
    @Override
    public boolean visitar(Jugador jugador) {
        /**
         * Método que permite a un jugador visitar el planeta.
         * Este método debe ser implementado por las clases
         * concretas que heredan de la clase abstracta Planeta.
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
         * Método que permite extraer recursos del planeta según el tipo especificado.
         * Este método debe ser implementado por las clases concretas que heredan de 
         * la clase abstracta Planeta, proporcionando la lógica específica para cada 
         * tipo de planeta.
         *
         * @param tipo Un entero que representa el tipo de recurso a extraer. 
         *             Los valores específicos de tipo deben definirse en la clase 
         *             que consume este método.
         * @return La cantidad de recursos extraídos del planeta. Si no hay 
         *         recursos disponibles o el tipo es inválido, se devuelve 0.
         */
        // Implementación por definir
        return super.extraerRecursos(tipo);
    }

    @Override
    public boolean salir() {
        /**
         * Método que permite al jugador salir del estado actual o del juego.
         * Este método puede ser utilizado para gestionar la lógica de salida 
         * del juego, asegurando que se realicen las acciones necesarias antes de 
         * salir, como guardar el estado del juego o mostrar un mensaje de despedida.
         *
         * @return true si la salida se realizó con éxito, false en caso contrario.
         */
        return true;
    }

}


        
