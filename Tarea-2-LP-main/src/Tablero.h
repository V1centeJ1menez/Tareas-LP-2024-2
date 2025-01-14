#ifndef H_TABLERO
#define H_TABLERO

extern int tamanoTablero; // Declaración externa de la variable global

/**
 * @brief Matriz cuadrada que almacena la información de cada celda.
 *
 * `tablero` es un puntero triple que representa una matriz cuadrada dinámica.
 * Cada celda de esta matriz contiene información específica relacionada con 
 * el estado o contenido de una celda en particular del tablero.
 *
 * - **Dimensiones**: Es una matriz de `N x N` donde `N` representa el tamaño 
 *   del tablero.
 * - **Tipo de datos**: `void***` permite almacenar punteros a punteros a 
 *   cualquier tipo de dato, lo que proporciona flexibilidad para almacenar 
 *   diferentes tipos de estructuras en cada celda.
 *
 * @note Dado que es una variable global, debe asegurarse que está 
 *       correctamente inicializada antes de su uso.
 */
extern void ***tablero;

/**
 * @brief Inicializa el tablero cuadrado para un tamaño dado.
 *
 * Esta función se encarga de crear y configurar el tablero, reservando memoria 
 * para cada celda en una matriz cuadrada de tamaño `tamano x tamano`. 
 * Cada celda se inicializa a un estado por defecto que representa que no ha sido disparada.
 *
 * @param tamano Tamaño del lado del tablero (número de filas y columnas).
 * @return Un puntero triple (`void***`) que apunta al tablero inicializado. 
 *         Si la inicialización falla, retorna `NULL`.
 *
 * @note Es responsabilidad del usuario liberar la memoria asignada al tablero
 *       una vez que ya no sea necesaria.
 */
void inicializarTablero(int tamano);

/**
 * @brief Verifica si el juego ha terminado.
 *
 * Esta función comprueba el estado del tablero para determinar si todos los barcos
 * han sido destruidos o si el número de turnos ha llegado a su límite. 
 * Devuelve un valor entero que indica el estado del juego:
 * - `0`: El juego continúa.
 * - `1`: El juego ha terminado.
 *
 * @return Un entero que indica si el juego ha terminado o no.
 *
 * @note Asegúrese de que el tablero y el número de turnos estén correctamente
 *       actualizados antes de llamar a esta función para obtener una evaluación
 *       precisa del estado del juego.
 */
int verificarJuegoTerminado(); 

/**
 * @brief Imprime el estado actual del tablero en la salida estándar.
 *
 * Esta función recorre cada celda del tablero y muestra su estado en la consola.
 * Los estados posibles de cada celda son:
 * - `'.'`: La celda no ha sido disparada.
 * - `'O'`: El disparo ha fallado.
 * - `'X'`: El disparo ha acertado.
 *
 * @param tablero Puntero triple (`void***`) que apunta al tablero a mostrar.
 * @param tamano Tamaño del lado del tablero (número de filas y columnas).
 *
 * @note Asegúrese de que el tablero ha sido inicializado antes de llamar a esta 
 *       función para evitar comportamientos indefinidos.
 */
void mostrarTablero();

/**
 * @brief Imprime el estado final del tablero en la salida estándar.
 *
 * Esta función muestra el estado final del tablero después de que el juego ha terminado.
 * Los estados posibles de cada celda son:
 * - `' '`: La celda no ha sido disparada.
 * - `'O'`: El disparo ha fallado.
 * - `'X'`: El disparo ha acertado.
 * - `'2'`, `'3'`, `'4'`, `'5'`: Parte del barco no destruida, representando el número del barco.
 *
 * @param tablero Puntero triple (`void***`) que apunta al tablero a mostrar.
 * @param tamano Tamaño del lado del tablero (número de filas y columnas).
 *
 * @note Asegúrese de que el tablero ha sido correctamente actualizado antes de llamar a
 *       esta función para reflejar el estado final del juego.
 */
void mostrarTableroFinal();

/**
 * @brief Coloca barcos en el tablero de manera aleatoria.
 *
 * Esta función coloca barcos de diferentes tamaños en el tablero de forma aleatoria, 
 * asegurando que no se superpongan y que se ajusten dentro de los límites del tablero.
 *
 * - Barcos de tamaño 1x2, 1x3, 1x4 y 1x5 se colocan en el tablero con caracteres distintivos.
 * - La cantidad de barcos y su tamaño varía según el tamaño del tablero, representando 
 *   diferentes niveles de dificultad (Facil, Medio, Dificil).
 *
 * @param tamano Tamaño del lado del tablero (número de filas y columnas).
 *
 * @note Asegúrese de que el tablero ha sido inicializado antes de llamar a esta función. 
 *       La función usa números aleatorios para colocar los barcos, por lo que el 
 *       posicionamiento puede variar en cada ejecución.
 */
void colocarBarcos(int tamano);

/**
 * @brief Libera la memoria asignada para el tablero.
 *
 * Esta función recorre cada celda del tablero y libera la memoria asignada para cada 
 * celda y para las filas y columnas de la matriz.
 *
 * @note Es importante llamar a esta función una vez que el tablero ya no sea necesario
 *       para evitar fugas de memoria.
 */
void liberarTablero();

#endif
