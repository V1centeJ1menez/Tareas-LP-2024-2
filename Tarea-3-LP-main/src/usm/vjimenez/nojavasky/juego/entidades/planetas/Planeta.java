package usm.vjimenez.nojavasky.juego.entidades.planetas;


import usm.vjimenez.nojavasky.juego.controladores.Jugador;
import java.util.Scanner;


public abstract class Planeta {

    //*************************************************** ATRIBUTOS ***************************************************//
    private int radio;
    private int cristalesHidrogeno;
    private int floresDeSodio;
    private int uranio;
    private int platino;
    private float consumoEnergia;
    private String tipo;
    private String descripcion;

    // Para la extracción de recursos específicos de cada planeta
    private int unidadesRecurso;
    private String nombreRecurso;

    // Scanner global
    private Scanner scanner = new Scanner(System.in);

    //*************************************************** CONSTRUCTOR ***************************************************//
    public Planeta(int radio, int cristalesHidrogeno, int floresDeSodio, int uranio, int platino, float consumoEnergia) {
        this.radio = radio;
        this.cristalesHidrogeno = cristalesHidrogeno;
        this.floresDeSodio = floresDeSodio;
        this.uranio = uranio;
        this.platino = platino;
        this.consumoEnergia = consumoEnergia;
        this.unidadesRecurso = 0;  // Inicializar unidadesRecurso según sea necesario
        this.nombreRecurso = "";    // Inicializar nombreRecurso según sea necesario
    }
    //*************************************************** GETTERS ***************************************************//
    public float getConsumoEnergia() {
        return consumoEnergia;
    }public int getCristalesHidrogeno() {
        return cristalesHidrogeno;
    }public int getFloresDeSodio() {
        return floresDeSodio;
    }public int getRadio() {
        return radio;
    }public String getTipo() {
        return tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPlatino() {
        return platino;
    }
    public int getUranio() {
        return uranio;
    }
    public int getUnidadesRecurso() {
        return unidadesRecurso;
    }
    public String getNombreRecurso() {
        return nombreRecurso;
    }
    public Scanner getScanner() {
        return scanner;
    }


    //*************************************************** SETTERS ***************************************************//
    public void setConsumoEnergia(float consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }public void setCristalesHidrogeno(int cristalesHidrogeno) {
        this.cristalesHidrogeno = cristalesHidrogeno;
    }public void setFloresDeSodio(int floresDeSodio) {
        this.floresDeSodio = floresDeSodio;
    }public void setRadio(int radio) {
        this.radio = radio;
    }public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPlatino(int platino) {
        this.platino = platino;
    }
    public void setUranio(int uranio) {
        this.uranio = uranio;
    }
    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void setUnidadesRecurso(int unidadesRecurso) {
        this.unidadesRecurso = unidadesRecurso;
    }


    //*************************************************** METODOS ***************************************************//
    // Método que se llama cuando el jugador visita el planeta
    public boolean visitar(Jugador jugador) {
        /**
         * Método que permite al jugador visitar el planeta.
         * 
         * Este método verifica si el jugador tiene suficiente energía
         * de protección y si la nave tiene combustible suficiente para 
         * realizar la visita. Si alguna de las condiciones no se cumple, 
         * la visita no se puede realizar.
         *
         * @param jugador El jugador que intenta visitar el planeta.
         * @return true si la visita es exitosa; false si no se puede visitar.
         */
        // Aqui hay que implemetar una logica con el combustible de la nave y la energia del exotraje
        // por ejemplo
        if (jugador.getUnidadesEnergiaProteccion() == 0) {
            return false;
        }
        return true;
    }

    // Método general para extraer recursos
    public int extraerRecursos(int tipo) {
        /**
         * Método general para extraer recursos del planeta.
         * 
         * Este método determina el tipo de recurso que el jugador desea extraer,
         * verifica si hay suficientes unidades disponibles y solicita la cantidad
         * que se desea extraer. Si la extracción es exitosa, se actualizan las
         * unidades del recurso en la superclase.
         *
         * @param tipo El tipo de recurso que se desea extraer:
         *             1 - Cristales de Hidrógeno
         *             2 - Flores de Sodio
         *             3 - Uranio
         *             4 - Platino
         * @return La cantidad de recursos extraídos, o -1 si no se pudo
         *         realizar la extracción por un tipo de recurso no válido,
         *         falta de unidades, o cantidad no válida.
         */
        String nombreRecurso = "";
        int unidadesRecurso = 0;

        // Determina el tipo de recurso que se va a extraer
        if (tipo == 1) {
            unidadesRecurso = getCristalesHidrogeno();
            nombreRecurso = "Cristales de Hidrógeno";
        } else if (tipo == 2) {
            unidadesRecurso = getFloresDeSodio();
            nombreRecurso = "Flores de Sodio";
        } else if (tipo == 3) {
            unidadesRecurso = getUranio();
            nombreRecurso = "Uranio";
        } else if (tipo == 4) {
            unidadesRecurso = getPlatino();
            nombreRecurso = "Platino";
        } else {
            System.out.println("Tipo de recurso no válido.");
            return -1; // Tipo de recurso no válido
        }

        // Verifica si hay recursos disponibles
        if (unidadesRecurso <= 0) {
            System.out.println("No hay suficientes " + nombreRecurso + " para extraer.");
            return -1;
        }

        // Solicita la cantidad de recursos a extraer
        System.out.println("¿Cuántas unidades de " + nombreRecurso + " deseas extraer? (Máximo " + unidadesRecurso + ")");
        int cantidadSolicitada = scanner.nextInt();

        // Verifica si la cantidad solicitada es válida
        if (cantidadSolicitada > unidadesRecurso || cantidadSolicitada <= 0) {
            System.out.println("Cantidad no válida.");
            return -1;
        }

        // Realiza la extracción, actualizando el recurso en la superclase
        unidadesRecurso -= cantidadSolicitada;  
        if (tipo == 1) {
            setCristalesHidrogeno(unidadesRecurso);
        } else if (tipo == 2) {
            setFloresDeSodio(unidadesRecurso);
        } else if (tipo == 3) {
            setUranio(unidadesRecurso);
        } else if (tipo == 4) {
            setPlatino(unidadesRecurso);
        }

        System.out.println("Has extraído " + cantidadSolicitada + " unidades de " + nombreRecurso + ".");
        return cantidadSolicitada;
    }

    // Método para salir del planeta
    public boolean salir() {
        /**
         * Método para salir del planeta.
         * 
         * Este método permite que el jugador abandone el planeta actual.
         * Se debe implementar una lógica para verificar si la nave tiene
         * suficiente combustible y si el exotraje tiene suficiente energía
         * para realizar la salida. Si alguna de las condiciones no se cumple,
         * la salida no podrá realizarse.
         *
         * @return true si la salida es exitosa; false si no se puede salir.
         */
        // Aqui hay que implemetar una logica con el combustible de la nave y la energia del exotraje
        return true;
    }

    
}

