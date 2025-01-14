package usm.vjimenez.nojavasky.juego.controladores;

//import usm.vjimenez.nojavasky.juego.entidades.planetas.Planeta;

import usm.vjimenez.nojavasky.utilidad.RandomNumberGenerator;
import usm.vjimenez.nojavasky.hud.estados.GameState;

public class Nave {

    //*************************************************** ATRIBUTOS ***************************************************//

    private float unidadesCombustible; // Capacidad inicial de 100.0 unidades de energía
    private float capcidadTotalCombustible;
    private float eficienciaPropulsor; // Eficiencia inicial de 0.0%
    
    //*************************************************** CONSTRUCTOR ***************************************************//

    public Nave(){
        this.unidadesCombustible = 100;
        this.capcidadTotalCombustible = 100;
        this.eficienciaPropulsor = 0;
    }

    //*************************************************** GETTERS ***************************************************//
    public float getUnidadesCombustible(){
        return unidadesCombustible;
    }

    public float getEficienciaPropulsor(){
        return eficienciaPropulsor;
    }

    public float getCapcidadTotalCombustible() {
        return capcidadTotalCombustible;
    }
    //*************************************************** SETTERS ***************************************************//
    public void setEficienciaPropulsor(float eficienciaPropulsor) {
        this.eficienciaPropulsor = eficienciaPropulsor;
    }

    public void setUnidadesCombustible(float unidadesCombustible) {
        this.unidadesCombustible = unidadesCombustible;
    }
    public void setCapcidadTotalCombustible(float capcidadTotalCombustible) {
        this.capcidadTotalCombustible = capcidadTotalCombustible;
    }

    //*************************************************** METODOS ***************************************************//
    
    public boolean viajarPlaneta(MapaGalactico MG, int direccion, int tamanoSalto) {

        /**
         * Método para viajar a un nuevo planeta en el mapa galáctico.
         *
         * Este método calcula una nueva posición en el mapa galáctico 
         * basándose en la dirección y el tamaño del salto. Verifica si 
         * el salto es válido y si hay suficiente combustible para realizarlo. 
         * Si es necesario, genera un nuevo planeta en la nueva posición.
         *
         * @param MG El mapa galáctico en el que se desea viajar.
         * @param direccion La dirección del salto (1 para adelante, -1 para atrás).
         * @param tamanoSalto El tamaño del salto a realizar.
         * @return true si el viaje es exitoso; false si no se puede realizar el salto.
         */
        int posicionActual = MG.getPosicionActual();
        int nuevaPosicion = posicionActual + direccion * tamanoSalto;
    
        // Verificar si la nueva posición es válida (no debe ir hacia atrás si estamos en el origen)
        if (nuevaPosicion < 0 || (posicionActual == 0 && direccion == -1)) {
            System.out.println("No puedes saltar a esa posición.");
            return false;
        }
    
        // Calcular el consumo de combustible basado en el tamaño del salto
        float unidadesConsumidas = (float)(0.75 * Math.pow(tamanoSalto, 2) * (1 - eficienciaPropulsor));
        float unidadesConsumidasEnUnSalto = (float)(0.75 * Math.pow(1, 2) * (1 - eficienciaPropulsor));
    
        // Verificar si hay suficiente combustible para hacer el salto
        if (unidadesCombustible >= unidadesConsumidas) {
            // Descontar el combustible consumido
            this.unidadesCombustible -= unidadesConsumidas;
            

            if(unidadesCombustible < unidadesConsumidasEnUnSalto || unidadesCombustible < 0){ unidadesCombustible = 0;}
          
    
            // Si el combustible llega a cero, mover a posición segura
            if (unidadesCombustible == 0) {
                System.out.println("Te has quedado sin combustible. Moviéndote a una posición segura...");
                moverAPosicionSegura(MG);
                return false;
            }
    
            // Expandir la lista si es necesario, llenando con "no visitados" (null)
            while (nuevaPosicion >= MG.getPlanetas().size()) {
                MG.getPlanetas().add(null);  // Añadir un espacio "no visitado"
            }
    
            // Actualizar la posición actual del jugador
            MG.setPosicionActual(nuevaPosicion);
            System.out.println("Has saltado a la posición " + nuevaPosicion);
    
            // Generar el planeta en la nueva posición si es necesario (si es null, se genera al visitarlo)
            if (MG.getPlanetas().get(nuevaPosicion) == null) {
                MG.generadorPlaneta(); // Generar un nuevo planeta
            }
    
            return true; // Salto exitoso
        } else {
            System.out.println("No tienes suficiente combustible para este salto.");
            return false; // Salto fallido
        }
    }
    
    // Método que mueve la nave a una posición segura con narrativa
    private void moverAPosicionSegura(MapaGalactico MG) {
        /**
         * Método que mueve la nave a una posición segura y proporciona una narrativa.
         *
         * Este método establece la posición de la nave en el origen (posición 0) 
         * y restablece las unidades de combustible a su capacidad total. 
         * También incluye un relato que describe la experiencia del jugador 
         * al quedarse sin combustible y ser llevado de vuelta al origen.
         *
         * @param MG El mapa galáctico donde se encuentra la nave.
         */
        GameState.limpiarPantalla();
        MG.setPosicionActual(0);
        this.unidadesCombustible = capcidadTotalCombustible;
        this.capcidadTotalCombustible = 100;
        this.eficienciaPropulsor = 0;

        System.out.println("***************************************************");
        System.out.println("*                                                 *");
        System.out.println("*           ~ Un Sueño en el Vacío ~              *");
        System.out.println("*                                                 *");
        System.out.println("* Te encuentras solo en la vasta inmensidad del    *");
        System.out.println("* espacio. Tu nave se vuelve más pesada, el motor  *");
        System.out.println("* ruge cada vez más débil. El medidor de           *");
        System.out.println("* combustible parpadea en rojo.                    *");
        System.out.println("*                                                 *");
        System.out.println("* De repente, el silencio. Tu nave flota sin       *");
        System.out.println("* rumbo, inmóvil en el frío vacío. Lentamente,     *");
        System.out.println("* el cansancio te atrapa y caes en un profundo     *");
        System.out.println("* sueño.                                           *");
        System.out.println("*                                                 *");
        System.out.println("* En tu sueño, caminas por un lugar extraño,       *");
        System.out.println("* donde el tiempo parece no tener sentido. Una     *");
        System.out.println("* voz suave te susurra: 'Tu viaje no termina aquí'.*");
        System.out.println("*                                                 *");
        System.out.println("* De repente, te despiertas. Las luces parpadean,  *");
        System.out.println("* el panel de control te indica que has sido       *");
        System.out.println("* movido al origen (posición 0). Estás a salvo,    *");
        System.out.println("* pero tu aventura continúa...                     *");
        System.out.println("***************************************************");
        System.out.println();
        System.out.println("Has sido movido al origen (posición 0) para recargar combustible.\nHas perdido las mejoras de tu nave...");
    }

    

    public int analizarSaltos(float tamañoSalto) {
        /**
         * Método que analiza la cantidad máxima de saltos que se pueden realizar 
         * con el combustible y la eficiencia del propulsor actuales.
         *
         * Este método calcula el consumo de combustible para un salto basado en 
         * el tamaño del salto proporcionado. Luego, determina cuántos saltos 
         * se pueden realizar con el combustible disponible.
         *
         * @param tamañoSalto El tamaño del salto a realizar.
         * @return La cantidad máxima de saltos que se pueden realizar con 
         *         el combustible disponible.
         */
        float combustibleActual = this.unidadesCombustible; // Combustible disponible
        float eficiencia = this.eficienciaPropulsor; // Eficiencia del propulsor
        int saltos = 0;
    
        // Calcular el consumo de combustible para un salto según el tamaño del salto ingresado
        float consumoDeUnSalto = (float) (0.75 * Math.pow(tamañoSalto, 2) * (1 - eficiencia));
    
        // Mientras tengas combustible suficiente para realizar saltos
        while (combustibleActual >= consumoDeUnSalto) {
            combustibleActual -= consumoDeUnSalto; // Restar el combustible necesario para un salto
            saltos++; // Incrementar el número de saltos posibles
        }
    
        return saltos; // Devolver la cantidad máxima de saltos que puede realizar
    }
    

    public void recargarPropulsores(int hidrogeno) {
        /**
         * Método que recarga las unidades de combustible de la nave 
         * basándose en la cantidad de hidrógeno disponible y la eficiencia 
         * del propulsor.
         *
         * Este método calcula las unidades de combustible que se pueden 
         * recargar en función del hidrógeno suministrado. Asegura que 
         * el total de combustible no supere la capacidad máxima de la nave.
         *
         * @param hidrogeno La cantidad de hidrógeno disponible para la recarga.
         */
        // Calcular las unidades de combustible recargadas basadas en el hidrógeno y la eficiencia del propulsor
        float unidadesRecargadas = (float)(0.6 * hidrogeno * (1 + eficienciaPropulsor));
        
        // Aumentar las unidades de combustible en la nave
        this.unidadesCombustible += unidadesRecargadas;

        // Asegurarse de que la energía no exceda la capacidad total
        if (unidadesCombustible > capcidadTotalCombustible) {
            unidadesCombustible = capcidadTotalCombustible;
        }
    
        System.out.println("Has recargado " + unidadesRecargadas + " unidades de combustible.");
        System.out.println("Combustible actual en la nave: " + unidadesCombustible + " unidades.");
    }

    // Método para mejorar la capacidad total de combustible de la nave
    public void mejorarCapacidad() {
        /**
         * Método que mejora la capacidad total de combustible de la nave
         * aumentando su capacidad en un 15%.
         *
         * Este método multiplica la capacidad total de combustible actual
         * de la nave por 1.15, reflejando una mejora en la capacidad de
         * la nave para almacenar combustible. Se notifica al jugador sobre
         * el nuevo valor de capacidad.
         */
        // Aumenta la capacidad total de combustible en un 15% (por ejemplo)
        this.capcidadTotalCombustible *= 1.15;
        System.out.println("Capacidad total de combustible de la nave mejorada a: " + this.capcidadTotalCombustible);
    }

    
    public void mejorarEficiencia() {

        /**
         * Método que mejora la eficiencia del propulsor de la nave
         * al incrementar su eficiencia energética mediante un valor
         * aleatorio entre 0.5 y 10.0.
         *
         * Este método permite al jugador aumentar la eficiencia del
         * propulsor, lo que puede resultar en un menor consumo de
         * combustible para los saltos. La eficiencia máxima se limita
         * a 100%.
         */
        float a = (float) 0.5;
        float b = (float) 10.0;

        float incremento = RandomNumberGenerator.randF(a, b);
    
        // Aumenta la eficiencia energética
        this.eficienciaPropulsor+= incremento;
    
        // Limitar la eficiencia máxima a 100%
        if (this.eficienciaPropulsor > 100) {
            this.eficienciaPropulsor = 100;  // Limitar a 100%
        }

        // Determinar el mensaje según el incremento
        if (incremento <= 5) {
            System.out.println("Eficiencia del propulsor mejorada a: " + (this.eficienciaPropulsor * 100) + "% - ¡Lo mejor que se pudo hacer!");
        } else if (incremento > 5 && incremento <= 7) {
            System.out.println("Eficiencia del propulsor mejorada a: " + (this.eficienciaPropulsor * 100) + "% - ¡Nada mal!");
        } else if (incremento > 7) {
            System.out.println("Eficiencia del propulsor mejorada a: " + (this.eficienciaPropulsor * 100) + "% - ¡Éxitoso!");
        }
    }
    
}
