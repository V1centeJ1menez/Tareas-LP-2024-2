#ifndef H_CARTAS
#define H_CARTAS

extern int coordenadaX;       // Variable global que almacena la coordenada X seleccionada por el usuario.
extern int coordenadaY;       // Variable global que almacena la coordenada Y seleccionada por el usuario.
extern int flag_500KG_usada;  // Variable global que indica si la carta 'disparo500KG' ha sido usada (1 si ha sido usada, 0 si no).

// Implementación de las cartas
/**
 * @brief Estructura que representa la mano del jugador.
 *
 * La estructura `Mano` almacena las cartas actuales del jugador y 
 * la cantidad de cartas disponibles para usar.
 */
typedef struct Mano {
    void **carta;        /**< Array de punteros a cartas actuales en la mano. */
    int disponibles;     /**< Número de cartas disponibles para usar. */
} Mano;

/**
 * @brief Variable global que representa la mano del jugador.
 *
 * `Cartas` es una variable global que contiene la estructura `Mano`, 
 * la cual almacena las cartas actuales que tiene el jugador en su mano.
 */
extern Mano Cartas;

/**
 * @brief Inicializa el mazo del jugador con cinco cartas de disparo simple.
 *
 * Esta función prepara la mano inicial del jugador, agregando cinco cartas 
 * de disparo simple.
 */
void inicializarMazo();

/**
 * @brief Muestra en pantalla las cartas disponibles en la mano del jugador.
 *
 * Esta función imprime en la salida estándar una lista de las cartas 
 * actualmente disponibles en la mano del jugador.
 */
void mostrarMazo();

/**
 * @brief Usa una carta de la mano disponible.
 *
 * Esta función permite al jugador usar una de las cartas disponibles en su mano,
 * eliminándola de la lista de cartas disponibles.
 */
void usarCarta();

/**
 * @brief Libera la memoria ocupada por la mano del jugador.
 *
 * Esta función libera la memoria asignada para la mano del jugador, incluyendo 
 * las cartas y cualquier otro recurso asociado.
 */
void liberarMazo();

/**
 * @brief Dispara un misil con un área de efecto de 1x1 celdas.
 *
 * La función dispara un misil en las coordenadas (x, y), afectando
 * únicamente esa celda específica.
 *
 * @param x Coordenada X del disparo.
 * @param y Coordenada Y del disparo.
 * @return Un puntero void que indica el resultado del disparo.
 */
void *disparoSimple(int x, int y);

/**
 * @brief Dispara un gran misil con un área de efecto de 3x3 celdas.
 *
 * La función dispara un misil que afecta un área de 3x3 celdas,
 * centrada en las coordenadas (x, y).
 *
 * @param x Coordenada X del centro del área afectada.
 * @param y Coordenada Y del centro del área afectada.
 * @return Un puntero void que indica el resultado del disparo.
 */
void *disparoGrande(int x, int y);

/**
 * @brief Dispara múltiples misiles en un área de 1x5 o 5x1 celdas.
 *
 * La función dispara misiles que afectan un área de 1x5 celdas 
 * (en horizontal) o 5x1 celdas (en vertical), centrada en las 
 * coordenadas (x, y).
 *
 * @param x Coordenada X del centro del área afectada.
 * @param y Coordenada Y del centro del área afectada.
 * @return Un puntero void que indica el resultado del disparo.
 */
void *disparoLineal(int x, int y);

/**
 * @brief Dispara un misil radar que revela información sobre un área de 5x5 celdas.
 *
 * La función dispara un misil radar que no daña el área, pero revela
 * si hay presencia de barcos enemigos en un área de 5x5 celdas, 
 * centrada en las coordenadas (x, y).
 *
 * @param x Coordenada X del centro del área escaneada.
 * @param y Coordenada Y del centro del área escaneada.
 * @return Un puntero void que indica el resultado del disparo.
 */
void *disparoRadar(int x, int y);

/**
 * @brief Dispara un misil de 500KG que afecta un área de 11x11 celdas.
 *
 * Este misil es el más grande disponible y afecta un área de 11x11
 * celdas centrada en las coordenadas (x, y). Debido a su tamaño,
 * no retorna ninguna carta y deja incapacitado el cañón que lo disparó.
 *
 * @param x Coordenada X del centro del área afectada.
 * @param y Coordenada Y del centro del área afectada.
 * @return Un puntero void que indica el resultado del disparo.
 */
void *disparo500KG(int x, int y);

/**
 * @brief Inicializa las funciones de disparo.
 *
 * Esta función asigna las funciones correspondientes a cada tipo de disparo
 * en el array de punteros a función `funciones`.
 */
void inicializarFunciones();

/**
 * @brief Tipo para puntero a función que recibe dos enteros y retorna un puntero void.
 */
typedef void* (*func_ptr)(int, int);

/**
 * @brief Enum de los tipos de funciones de disparo.
 *
 * Enum que define los diferentes tipos de funciones de disparo disponibles.
 */
typedef enum {
    Simple,
    Grande,
    Lineal,
    Radar,
    _500KG,
    count
} Funciones;

/**
 * @brief Array de punteros a funciones de disparo.
 *
 * `funciones` es un array de punteros a funciones que representan las
 * diferentes acciones de disparo disponibles.
 */
extern func_ptr funciones[count];

/**
 * @brief Genera un número aleatorio entre 0 y el valor máximo dado.
 *
 * Esta función genera un número aleatorio entre 0 y el valor máximo dado.
 *
 * @param max Valor máximo para el número aleatorio.
 * @return Número aleatorio generado.
 */
int generarNumeroAleatorio(int max); 

/**
 * @brief Cambia una carta en la mano del jugador.
 *
 * Esta función reemplaza una carta en la mano del jugador con una nueva carta
 * del tipo especificado.
 *
 * @param indiceCarta Índice de la carta en la mano que se debe reemplazar.
 * @param tipoCarta Tipo de carta con el que se reemplazará la carta actual.
 */
void cambiarCarta(int indiceCarta, int tipoCarta); 

#endif
