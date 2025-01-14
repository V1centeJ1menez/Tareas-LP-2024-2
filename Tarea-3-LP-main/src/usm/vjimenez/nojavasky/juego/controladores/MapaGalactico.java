package usm.vjimenez.nojavasky.juego.controladores;

import java.util.List;
import java.util.ArrayList;
import usm.vjimenez.nojavasky.juego.entidades.planetas.*;
import usm.vjimenez.nojavasky.juego.entidades.planetas.tipos.*;
import usm.vjimenez.nojavasky.utilidad.*;

public class MapaGalactico {


    //*************************************************** ATRIBUTOS ***************************************************//
  
    private List<Planeta> planetas = new ArrayList<>();
    private int posicionActual; // Posición del jugador en el mapa galáctico
    private boolean centroGalacticoGenerado = false;

    //*************************************************** CONSTRUCTOR ***************************************************//

    public MapaGalactico(){
        this.posicionActual = 0;
        planetas.add(null);
    }

    //*************************************************** GETTERS ***************************************************//
    public List<Planeta> getPlanetas() {
        return planetas;
    }

    public Planeta getPlanetaActual(){

        return planetas.get(posicionActual);
    }
    
    public int getPosicionActual() {
        return posicionActual;
    }

    //*************************************************** SETTERS ***************************************************//

    // Método setter para cambiar la posición actual a un planeta ya descubierto
    public void setPosicionActual(int nuevaPosicion) {
        if (nuevaPosicion >= 0 && nuevaPosicion < planetas.size()) {
            posicionActual = nuevaPosicion;
            System.out.println("Te has trasladado al planeta en la posición: " + nuevaPosicion);
        } else {
            System.out.println("Posición inválida. No se puede trasladar a una posición no explorada.");
        }
    }
    public void setCentroGalacticoGenerado(boolean centroGalacticoGenerado) {
        this.centroGalacticoGenerado = centroGalacticoGenerado;
    }
    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }




    //*************************************************** METODOS ***************************************************//

    public void mostrarMapa() {
        /**
         * Muestra el mapa galáctico con el estado de los planetas.
         * 
         * Este método recorre la lista de planetas y muestra el estado de
         * cada uno, indicando si ha sido visitado o no. Si un planeta es
         * `null`, se indica como "No visitado". Para los planetas visitados,
         * se muestra su tipo y un símbolo representativo.
         */
        System.out.println("\n--- Mapa Galáctico ---");
    
        // Recorrer la lista de planetas y mostrar el estado de cada uno
        for (int i = 0; i < planetas.size(); i++) {
            Planeta planeta = planetas.get(i);
            
            if (planeta == null) {
                // Mostrar como "No visitado" si el planeta es null
                System.out.println("Planeta " + i + ": No visitado (❓)");
            } else {
                // Mostrar el tipo de planeta y su símbolo
                String simbolo = obtenerSimboloPlaneta(planeta);
                System.out.println("Planeta " + i + ": " + planeta.getTipo() + " " + simbolo);
            }
        }
    
        System.out.println("------------------------");
    }
    
    // Método auxiliar para obtener un símbolo según el tipo de planeta
    private String obtenerSimboloPlaneta(Planeta planeta) {
        /**
         * Método auxiliar para obtener un símbolo representativo según el tipo de planeta.
         *
         * Este método verifica la instancia del objeto `planeta` y devuelve un símbolo 
         * específico que representa visualmente el tipo de planeta.
         *
         * @param planeta El objeto `Planeta` para el cual se desea obtener el símbolo.
         * @return Un string que representa el símbolo del planeta.
         */
        if (planeta instanceof Helado) {
            return "(❄️)"; // Símbolo de planeta helado
        } else if (planeta instanceof Oceanico) {
            return "(🌊)"; // Símbolo de planeta oceánico
        } else if (planeta instanceof Volcanico) {
            return "(🌋)"; // Símbolo de planeta volcánico
        } else if (planeta instanceof Radioactivo) {
            return "(☢️)"; // Símbolo de planeta radioactivo
        } else if (planeta instanceof CentroGalactico) {
            return "(🌟)"; // Símbolo de centro galáctico
        } else {
            return "(🌍)"; // Símbolo genérico para otros planetas
        }
    }
    
    

    // Método para generar un nuevo planeta (solo si es necesario)
    public Planeta generadorPlaneta() {
        /**
         * Método para generar un nuevo planeta basado en un número aleatorio.
         *
         * Este método genera un número aleatorio entre 1 y 100 y, basado en ese número,
         * crea un nuevo planeta de uno de los tipos posibles: Helado, Oceánico, Radioactivo,
         * Volcánico o un Centro Galáctico. Asegura que el Centro Galáctico solo se genere
         * una vez.
         *
         * @return El nuevo planeta generado o null si no se generó ninguno.
         */
        int randomNum = RandomNumberGenerator.rand(1, 100); // Genera un número entre 1 y 100

        Planeta nuevoPlaneta = null;

        if (randomNum <= 30) {
            nuevoPlaneta = crearHelado();
        } else if (randomNum <= 60) {
            nuevoPlaneta = crearOceanico();
        } else if (randomNum <= 80) {
            nuevoPlaneta = crearRadioactivo();
        } else if (randomNum <= 99) {
            nuevoPlaneta = crearVolcanico();
        } else if (!centroGalacticoGenerado) {
            nuevoPlaneta = crearCentroGalactico();
            centroGalacticoGenerado = true; // Asegurarse de que solo se genere una vez
        }

        // Si el nuevo planeta es válido, agregarlo a la lista
        if (nuevoPlaneta != null) {
            planetas.set(this.posicionActual,nuevoPlaneta);
        }

        return nuevoPlaneta;
    }

    // Métodos de creación de planetas (sin cambios)

    private Helado crearHelado() {

        /**
         * Método para crear un nuevo planeta helado.
         *
         * Este método genera un planeta del tipo Helado con atributos
         * aleatorios. Los atributos se determinan de la siguiente manera:
         * 
         * - **Radio**: Un valor aleatorio entre 1,000 y 1,000,000 metros.
         * - **Temperatura**: Un valor aleatorio entre -120 y -30 grados Celsius.
         * - **Cristales de Hidrógeno**: Calculado como el 65% del área superficial del planeta.
         * - **Flores de Sodio**: Calculado como el 35% del área superficial del planeta.
         * - **Consumo de Energía**: Calculado como el 15% del valor absoluto de la temperatura.
         * 
         * @return Un nuevo objeto de tipo Helado con los atributos generados.
         */
        int radio = RandomNumberGenerator.rand(1000, 1000000); // rand(10^3, 10^6)
        int temperatura = RandomNumberGenerator.rand(-120, -30); // rand(-120, -30)
        int cristalesHidrogeno = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
        int floresDeSodio = (int) (0.35 * (4 * Math.PI * Math.pow(radio, 2)));
        float consumoEnergia = (float) (0.15 * Math.abs(temperatura));

        return new Helado(radio, cristalesHidrogeno, floresDeSodio, temperatura, consumoEnergia);
    }

    private Oceanico crearOceanico() {
        /**
         * Método para crear un nuevo planeta oceánico.
         *
         * Este método genera un planeta del tipo Oceanico con atributos
         * aleatorios. Los atributos se determinan de la siguiente manera:
         * 
         * - **Radio**: Un valor aleatorio entre 10,000 y 1,000,000 metros.
         * - **Profundidad**: Un valor aleatorio entre 30 y 1,000 metros.
         * - **Cristales de Hidrógeno**: Calculado como el 20% del área superficial del planeta.
         * - **Flores de Sodio**: Calculado como el 65% del área superficial del planeta.
         * - **Consumo de Energía**: Calculado como el 0.002 veces el cuadrado de la profundidad.
         * 
         * @return Un nuevo objeto de tipo Oceanico con los atributos generados.
         */
        int radio = RandomNumberGenerator.rand(10000, 1000000); // rand(10^4, 10^6)
        int profundidad = RandomNumberGenerator.rand(30, 1000); // rand(30, 103)
        int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
        int floresDeSodio = (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
        float consumoEnergia = (float) (0.002 * Math.pow(profundidad, 2));

        return new Oceanico(radio, cristalesHidrogeno, floresDeSodio, profundidad, consumoEnergia);
    }

    private Radioactivo crearRadioactivo() {
        /**
         * Método para crear un nuevo planeta radioactivo.
         *
         * Este método genera un planeta del tipo Radioactivo con atributos
         * aleatorios. Los atributos se determinan de la siguiente manera:
         * 
         * - **Radio**: Un valor aleatorio entre 10,000 y 100,000 metros.
         * - **Radiación**: Un valor aleatorio entre 10 y 50.
         * - **Cristales de Hidrógeno**: Calculado como el 20% del área superficial del planeta.
         * - **Flores de Sodio**: Calculado como el 20% del área superficial del planeta.
         * - **Uranio**: Calculado como el 25% del área superficial del planeta multiplicado por la radiación.
         * - **Consumo de Energía**: Calculado como el 0.3 veces la radiación.
         * 
         * @return Un nuevo objeto de tipo Radioactivo con los atributos generados.
         */
        int radio = RandomNumberGenerator.rand(10000, 100000); // rand(10^4, 10^5)
        int radiacion = RandomNumberGenerator.rand(10, 50); // rand(10, 50)
        int cristalesHidrogeno = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
        int floresDeSodio = (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
        int uranio = (int) (0.25 * (4 * Math.PI * Math.pow(radio, 2)) * radiacion);
        float consumoEnergia = (float) (0.3 * radiacion);

        return new Radioactivo(radio, cristalesHidrogeno, floresDeSodio, uranio, radiacion, consumoEnergia);
    }

    private Volcanico crearVolcanico() {
        /**
         * Método para crear un nuevo planeta volcánico.
         *
         * Este método genera un planeta del tipo Volcanico con atributos
         * aleatorios. Los atributos se determinan de la siguiente manera:
         * 
         * - **Radio**: Un valor aleatorio entre 1,000 y 100,000 metros.
         * - **Temperatura**: Un valor aleatorio entre 120 y 256 grados.
         * - **Cristales de Hidrógeno**: Calculado como el 30% del área superficial del planeta.
         * - **Flores de Sodio**: Se establece en 0, ya que no se generan en este tipo de planeta.
         * - **Platino**: Calculado como el 25% del área superficial del planeta menos un factor relacionado con la temperatura.
         * - **Consumo de Energía**: Calculado como el 0.08 veces la temperatura.
         * 
         * @return Un nuevo objeto de tipo Volcanico con los atributos generados.
         */
        int radio = RandomNumberGenerator.rand(1000, 100000); // rand(10^3, 10^5)
        int temperatura = RandomNumberGenerator.rand(120, 256); // rand(120, 256)
        int cristalesHidrogeno = (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
        int floresDeSodio = 0;
        int platino = (int) ((0.25 * (4 * Math.PI * Math.pow(radio, 2))) - (20.5 * Math.pow(temperatura, 2)));
        float consumoEnergia = (float) (0.08 * temperatura);

        return new Volcanico(radio, cristalesHidrogeno, floresDeSodio, platino, temperatura, consumoEnergia);
    }

    private CentroGalactico crearCentroGalactico() {
        /**
         * Método para crear un nuevo centro galáctico.
         *
         * Este método genera un objeto de tipo CentroGalactico sin 
         * ningún parámetro. El objeto creado representa el centro de la 
         * galaxia en el contexto del juego.
         *
         * @return Un nuevo objeto de tipo CentroGalactico.
         */
        return new CentroGalactico(); // Crear un CentroGalactico sin parámetros
    }
}
