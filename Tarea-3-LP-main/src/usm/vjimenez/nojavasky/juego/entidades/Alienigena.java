package usm.vjimenez.nojavasky.juego.entidades;

import java.util.Random;

public class Alienigena {


    //*************************************************** ATRIBUTOS ***************************************************//
    private String nombre;
    private String especie;
    private String mensaje;
    private String arteASCII;
    private String oferta;  // Nueva propiedad para almacenar las ofertas de este alienígena

    //*************************************************** CONSTRUCTOR ***************************************************//
    public Alienigena(String nombre, String especie, String mensaje, String arteASCII, String oferta) {
        this.nombre = nombre;
        this.especie = especie;
        this.mensaje = mensaje;
        this.arteASCII = arteASCII;
        this.oferta = oferta;
    }

    //*************************************************** GETTERS ***************************************************//
    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getArteASCII() {
        return arteASCII;
    }

    public String getOferta() {
        return oferta;
    }

    //*************************************************** SETTERS ***************************************************//
    public void setArteASCII(String arteASCII) {
        this.arteASCII = arteASCII;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setOferta(String oferta) {
        this.oferta = oferta;
    }
    

    //*************************************************** METODOS ***************************************************//
    public Alienigena generarAlienigena() {

        /**
         * Método que genera un alienígena con características aleatorias.
         * 
         * Este método determina el tipo de alienígena que se generará en función de 
         * la especie seleccionada (Glaciarido o Aqualuxian). Para cada tipo de 
         * alienígena, se elige un nombre y un mensaje aleatorio de una lista predefinida,
         * así como un arte ASCII que representa al alienígena. Además, se selecciona 
         * una oferta aleatoria que el alienígena puede hacer al jugador.
         *
         * @return Un objeto Alienigena que contiene un nombre, especie, mensaje, arte 
         *         ASCII y una oferta aleatoria.
         */
        String arteAsignado;
        String mensajeAleatorio;
        String nombreAleatorio;
        Random random = new Random();

        if ( especie == "Glaciarido") {

            String[] nombresGlaciaridos = {
                "Glaciáridos", "Cryonex", "Frigidans", "Helioformis", "Glacium",
                "Cryomorfos", "Frigipods", "Iceforgeans", "Frostbenders", "Gelidans",
                "Hielonautas", "Frigostrians", "Cryonitas", "Iceweavers", "Hieloides",
                "Frosticrux", "Cryomantics", "Glaciocrats", "Chilltarians", "Frostbiters",
                "Glacialons", "Cryonovans", "Gelidiforms", "Frigidox", "Hieloformas",
                "Cryomites", "Frostikons", "Glacifex", "Iceborns", "Hielocons",
                "Frigiforms", "Cryonicas", "Glaciopods", "Frosticians", "Icebringers",
                "Glacimorphs", "Cryotarians", "Frigidnors", "Frostwalkers", "Helionautas",
                "Cryolites", "Glacialites", "Hieloniks", "Frostwaves", "Icekeepers",
                "Gelidonianos", "Cryoceanos", "Glaciforms", "Frigidex", "Hieloclasts",
                "Frostmorphs", "Cryomorphans", "Iceconstructs", "Gelidox", "Frigidbenders",
                "Hielocrones", "Cryogens", "Glaciocreators", "Frostknights", "Iceforgers",
                "Glacialists", "Cryomancers", "Frigimorphs", "Hielomancers", "Frostgolems",
                "Gelidryx", "Icecladans", "Cryokinetics", "Glacialonauts", "Frostfinders",
                "Hieloformans", "Frigidroids", "Cryotech", "Iceimprints", "Gelidmasters",
                "Glaciarchs", "Frostogons", "Hielotons", "Cryocrafts", "Glacialkin",
                "Frostmist", "Icewelders", "Gelidforge", "Hielozoids", "Cryoventors",
                "Frostshapers", "Glaciagons", "Icebounders", "Hielopods", "Cryogenics",
                "Glacialbeings", "Frostwardens", "Icechillers", "Gelidminions", "Frigidmechs",
                "Hielonautas", "Frostvolans", "Glacitechs", "Cryoforms", "Iceharbingers"
            };
            String[] mensajesGlaciaridos = {
                "mppssssssss... mpp mp mp...\nhsssssss... clank clank...\nbrrrrrrr... psssssss... zzzzt...\nmpp mpp mpp... hsssssss... ding...",
                "hssssssss... brrrrrrrr... clang clang...\nmp mp mp... pssssssss... mppp...\nmppssss... zzzzt... hssss...\nbrrrr... ding ding... mpp mpp...",
                "mpp mpp mpp... hsssssss... beep beep...\nbrrrrrrr... clang clang... zzzzt...\nhssss... pssssssss... mp mp...\nmppssssssss... brrrrrr... hsssssss...",
                "ding ding... mpppp... hsssssss...\nbrrrr... pssss... mpp mpp...\nhssssssss... zzzzt... clang...\nmpp mp mp... hssss... beep...",
                "mpp mpp mpp... hissssss... clang clang...\nbrrrrrrrr... pssssssss... hsssssss...\nmpppp... zzzzt... ding ding...\nhssss... beep beep... brrrrrr..."
            };
             // Generar un número aleatorio para seleccionar un nombre
        
            int indiceNombreAleatorio = random.nextInt(nombresGlaciaridos.length);
            int indiceMensajeAleatorio = random.nextInt(mensajesGlaciaridos.length);
            mensajeAleatorio = mensajesGlaciaridos[indiceMensajeAleatorio];
            nombreAleatorio = (nombresGlaciaridos[indiceNombreAleatorio]);
            arteAsignado = "                            ______________               \n" +
            "                                       __/_|_|_|_|_|_|_\\__               \n" +
            "                                      /                   \\     .           \n" +
            "                 .       ____________/   ____               \\   :            \n" +
            "                 :    __/_|_|_|_|_|_(   |    |               )   |           \n" +
            "                 |   /               \\ | () |()  ()  ()  ()/    *          \n" +
            "                 *  /  ____           \\|____|_____________/            \n" +
            "    .              (  |    |            \\_______________/                   \n" +
            "    :               \\ | () |()  ()  ()    \\___________/                     \n" +
            "    |                \\|____|____________ /   \\______/     .                \n" +
            "    *                  \\_______________/       \\  /       :                \n" +
            "                    .    \\___________/         (__)        |    .          \n" +
            "                    :       \\______/           /  \\       *    :          \n" +
            "                    |         \\  /            /    \\           |          \n" +
            "                    *         (__)           /      \\           *          \n" +
            "                              /  \\          /        \\                  \n" +
            "                             /    \\        /          \\                  \n" +
            "                            /      \\      /            \\                  \n" +
            "                           /        \\    /              \\                  \n" +
            "                          /          \\  /                \\                  \n" +
            "________     ___________ /           _\\/__________________\\__              ";

            
            
        } else{

    
            String[] nombresAqualuxians = {
                "Zephyra", "Morphix", "Luminara", "Aquor", "Thalassa", 
                "Nereid", "Coralyn", "Marisol", "Delphinor", "Oceandis", 
                "Tritonix", "Aquaflora", "Cyanea", "Vortigon", "Plesios", 
                "Sirenai", "Aquanox", "Abyssara", "Kelpith", "Nautilox", 
                "Lumisqua", "Selketh", "Aqualith", "Mistral", "Abyssalon", 
                "Phaeos", "Glistara", "Nautica", "Okeanos", "Brineus", 
                "Myrkel", "Cnidara", "Hydrantis", "Thalassor", "Fathomir", 
                "Dorsalis", "Marithra", "Riptide", "Glacialis", "Aquarion", 
                "Vortexa", "Oceandor", "Serephin", "Skydancer", "Sirenon", 
                "Lumisfin", "Pelagor", "Tsunara", "Hydrox", "Coralith", 
                "Mistralix", "Ocealux", "Selenor", "Aqualon", "Thalassia", 
                "Marinara", "Fathomos", "Nautikron", "Calypsor", "Abyssian", 
                "Ocealith", "Sirenith", "Aquaflix", "Hydrathor", "Benthix", 
                "Planktonar", "Zephyron", "Coralax", "Tidalus", "Brackith", 
                "Luminarae", "Neptulon", "Aquatide", "Vortigonis", "Glimmeris", 
                "Pelagia", "Aquarious", "Coralithor", "Marisca", "Sirenus", 
                "Abyssina", "Reefor", "Marisian", "Aquaflux", "Thalasson", 
                "Brinara", "Lumiglow", "Kelpida", "Nautilara", "Hydrolyn", 
                "Thalassael", "Oceanith", "Sirenelle", "Briskara", "Coralithix", 
                "Luminaris", "Thalassir", "Abyssane", "Cyaneael", "Aqualar"
            };
            String[] mensajesAqualuxians = {
                "Las profundidades son nuestro hogar y el silencio es nuestra canción.",
                "Atraviesa las corrientes y descubre los secretos que se esconden en el abismo.",
                "En el océano, cada burbuja cuenta una historia de vida y misterio.",
                "Navegamos entre los arrecifes, donde la luz se encuentra con la oscuridad.",
                "La marea trae mensajes de antiguos viajeros del tiempo."
            };
            int indiceNombreAleatorio = random.nextInt(nombresAqualuxians.length);
            int indiceMensajeAleatorio = random.nextInt(mensajesAqualuxians.length);
            mensajeAleatorio = mensajesAqualuxians[indiceMensajeAleatorio];
            nombreAleatorio = (nombresAqualuxians[indiceNombreAleatorio]);
            arteAsignado = 
            "                 ______\n" +
            "                /     /\\\n" +
            "               /     /##\\\n" +
            "              /     /####\\\n" +
            "             /     /######\\\n" +
            "            /     /########\\\n" +
            "           /     /##########\\\n" +
            "          /     /#####/\\#####\\\n" +
            "         /     /#####/++\\#####\\\n" +
            "        /     /#####/++++\\#####\\\n" +
            "       /     /#####/\\+++++\\#####\\\n" +
            "      /     /#####/  \\+++++\\#####\\\n" +
            "     /     /#####/    \\+++++\\#####\\\n" +
            "    /     /#####/      \\+++++\\#####\\\n" +
            "   /     /#####/        \\+++++\\#####\\\n" +
            "  /     /#####/__________\\+++++\\#####\\\n" +
            " /                        \\+++++\\#####\\\n" +
            "/__________________________\\+++++\\####/\n" +
            "\\+++++++++++++++++++++++++++++++++\\##/\n" +
            " \\+++++++++++++++++++++++++++++++++\\/ \n" +
            "  ``````````````````````````````````";



        }
        
      
        String[][] ofertas = {
            {"Puedo mejorar la **capacidad de tu nave** por platino y uranio", "Te ayudaré a aumentar la **eficiencia de tu nave**."},  // Ofertas para Zorblax
            {"Te ofrezco mejorar la **capacidad de energía de tu traje**", "Acepta mi oferta de mejora en la **eficiencia energética de tu traje**"},  // Ofertas para Krithor
            {"Tengo mejoras para la **eficiencia de tu nave**", "Podemos hacer tu nave más rápida ¿Te interesa mejorar la **eficiencia de tu nave**?"},  // Ofertas para Xaltran
            {"Necesitas una mejora en la **eficiencia energética de tu traje**","Puedo mejorar la **capacidad de tu nave** por platino y uranio","Parece ser que necesitas mayor **capacidad de energía de tu traje**, ¿Te interesa?"}  // Ofertas para Frygon
        };

        int indiceAlienigena = random.nextInt(ofertas.length); // Selecciona un alienígena aleatorio
        int indiceOferta = random.nextInt(ofertas[indiceAlienigena].length); // Selecciona una oferta aleatoria del alienígena seleccionado

        String ofertaAleatoria = ofertas[indiceAlienigena][indiceOferta];

        return new Alienigena(nombreAleatorio, especie, mensajeAleatorio, arteAsignado, ofertaAleatoria);
    }
}
