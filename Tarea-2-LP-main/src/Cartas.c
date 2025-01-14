#include <stdio.h>
#include <stdlib.h>
#include "Cartas.h"
#include "Tablero.h"

// Estructura global para manejar las cartas
Mano Cartas;

// Variables globales para las coordenadas
int coordenadaX = 0;
int coordenadaY = 0;

// Bandera para verificar si la carta de 500KG ha sido usada
// 0: no usada, 1: usada
int flag_500KG_usada = 0; 

// Función de disparo simple
void *disparoSimple(int x, int y) {
    // Verificar que las coordenadas estén dentro de los límites del tablero
    if (x < 0 || x > tamanoTablero || y < 0 || y > tamanoTablero) {
        printf("Coordenadas fuera de los límites del tablero.\n");
        return NULL;
    }

    // Mostrar el disparo
    printf("Disparo Simple con (%d, %d)\n", x, y); // Para mostrar coordenadas 1-indexed

    // Verificar el estado del tablero en la coordenada especificada
    char *celda = (char *)tablero[x - 1][y - 1]; // Convertir el puntero a char
    if (*celda == ' ') {
        // Si está vacío, se considera un fallo
        printf("Falló el disparo en (%d, %d).\n", x, y);
        *celda = 'O'; // Marcamos el fallo en el tablero
    } else if (*celda == 'O') {
        // Si ya se había disparado previamente en esa coordenada
        printf("Has fallado de nuevo en (%d, %d).\n", x, y);
    } else {
        // Si hay un barco, se considera un acierto
        printf("Acierto en (%d, %d).\n", x, y);
        *celda = 'X'; // Marcamos el acierto en el tablero
    }

    return NULL;
}

// Función de disparo grande (área de 3x3)
void *disparoGrande(int x, int y) {
    // Verificar que las coordenadas estén dentro de los límites del tablero
    if (x < 1 || x > tamanoTablero || y < 1 || y > tamanoTablero) {
        printf("Coordenadas fuera de los límites del tablero.\n");
        return NULL;
    }

    // Ajustar las coordenadas para que el índice del arreglo sea 0-indexed
    int x0 = x - 1;
    int y0 = y - 1;

    printf("Disparo Grande con (%d, %d)\n", x, y);

    // Recorrer el área de efecto 3x3
    for (int i = x0 - 1; i <= x0 + 1; i++) {
        for (int j = y0 - 1; j <= y0 + 1; j++) {
            // Verificar que las coordenadas estén dentro de los límites del tablero
            if (i >= 0 && i < tamanoTablero && j >= 0 && j < tamanoTablero) {
                char *celda = (char *)tablero[i][j]; // Convertir el puntero a char
                if (*celda == ' ' || *celda == 'O') {
                    // Si está vacío, se considera un fallo
                    *celda = 'O'; // Marcamos el fallo en el tablero
                } else {
                    // Si hay algo, se considera un acierto
                    *celda = 'X'; // Marcamos el acierto en el tablero
                }
            }
        }
    }

    return NULL;
}

// Función de disparo lineal (área de 1x5 o 5x1 dependiendo de la orientación)
void *disparoLineal(int x, int y) {
    // Verificar que las coordenadas estén dentro de los límites del tablero
    if (x < 1 || x > tamanoTablero || y < 1 || y > tamanoTablero) {
        printf("Coordenadas fuera de los límites del tablero.\n");
        return NULL;
    }

    // Ajustar las coordenadas para que el índice del arreglo sea 0-indexed
    int x0 = x - 1;
    int y0 = y - 1;

    printf("Disparo Lineal con (%d, %d)\n", x, y);

    // Determinar la orientación (0 = horizontal, 1 = vertical)
    int orientacion = rand() % 2;

    // Área de efecto de 1x5 o 5x1 dependiendo de la orientación
    if (orientacion == 0) { // Horizontal
        for (int j = y0 - 2; j <= y0 + 2; j++) {
            // Verificar que la columna está dentro de los límites del tablero
            if (x0 >= 0 && x0 < tamanoTablero && j >= 0 && j < tamanoTablero) {
                char *celda = (char *)tablero[x0][j]; // Convertir el puntero a char
                if (*celda == ' ' || *celda == 'O') {
                    // Si está vacío, se considera un fallo
                    *celda = 'O'; // Marcamos el fallo en el tablero
                } else {
                    // Si hay algo, se considera un acierto
                    *celda = 'X'; // Marcamos el acierto en el tablero
                }
            }
        }
    } else { // Vertical
        for (int i = x0 - 2; i <= x0 + 2; i++) {
            // Verificar que la fila está dentro de los límites del tablero
            if (i >= 0 && i < tamanoTablero && y0 >= 0 && y0 < tamanoTablero) {
                char *celda = (char *)tablero[i][y0]; // Convertir el puntero a char
                if (*celda == ' ' || *celda == 'O') {
                    // Si está vacío, se considera un fallo
                    *celda = 'O'; // Marcamos el fallo en el tablero
                } else {
                    // Si hay algo, se considera un acierto
                    *celda = 'X'; // Marcamos el acierto en el tablero
                }
            }
        }
    }

    return NULL;
}

// Función de disparo radar (área de 5x5)
void *disparoRadar(int x, int y) {
    printf("Disparo Radar con (%d, %d)\n", x, y);

    // Ajustar las coordenadas para que el índice del arreglo sea 0-indexed
    int x0 = x - 1;
    int y0 = y - 1;

    // Recorrer el área de efecto 5x5
    for (int i = x0 - 2; i <= x0 + 2; i++) {
        for (int j = y0 - 2; j <= y0 + 2; j++) {
            // Verificar que las coordenadas estén dentro de los límites del tablero
            if (i >= 0 && i < tamanoTablero && j >= 0 && j < tamanoTablero) {
                char *celda = (char *)tablero[i][j]; // Convertir el puntero a char
                if (*celda == ' ' || *celda == 'O') {
                    // Si está vacío, se considera un fallo y se marca con 'O'
                    *celda = 'O';
                } else if (*celda == 'X') { 
                    // Si hay un barco, se marca con 'X'
                    *celda = 'X';
                } else {
                    *celda ='!';
                }
            }
        }
    }

    return NULL;
}

// Función de disparo 500KG (área de 11x11)
void *disparo500KG(int x, int y) {
    printf("Disparo 500KG con (%d, %d)\n", x, y);

    // Ajustar las coordenadas para que el índice del arreglo sea 0-indexed
    int x0 = x - 1;
    int y0 = y - 1;
    flag_500KG_usada = 1; // 0: no usada, 1: usada

    // Recorrer el área de efecto 11x11
    for (int i = x0 - 5; i <= x0 + 5; i++) {
        for (int j = y0 - 5; j <= y0 + 5; j++) {
            // Verificar que las coordenadas estén dentro de los límites del tablero
            if (i >= 0 && i < tamanoTablero && j >= 0 && j < tamanoTablero) {
                char *celda = (char *)tablero[i][j]; // Convertir el puntero a char
                if (*celda == ' ' || *celda == 'O') {
                    // Si está vacío, se considera un fallo y se marca con 'O'
                    *celda = 'O';
                } else { 
                    // Si hay un barco, se marca con 'X'
                    *celda = 'X';
                }
            }
        }
    }

    return NULL;
}

// Array de punteros a funciones para manejar las cartas
func_ptr funciones[count];

// Inicializar el array de funciones con las diferentes cartas disponibles
void inicializarFunciones() {
    funciones[Simple] = disparoSimple;
    funciones[Grande] = disparoGrande;
    funciones[Lineal] = disparoLineal;
    funciones[Radar] = disparoRadar;
    funciones[_500KG] = disparo500KG;
}

// Inicializar el mazo de cartas
void inicializarMazo() {
    Cartas.disponibles = 5; // Cantidad inicial de cartas disponibles
    Cartas.carta = (void **)malloc(Cartas.disponibles * sizeof(void *)); // Asignar memoria para el mazo
    
    if (Cartas.carta == NULL) {
        perror("Failed to allocate memory"); // Error al asignar memoria
        exit(EXIT_FAILURE);
    }

    // Inicializa el array de cartas con punteros a la función disparoSimple
    for (int i = 0; i < Cartas.disponibles; i++) {
        Cartas.carta[i] = (void *)funciones[0]; // Cast a void *
    }
}

// Mostrar el mazo de cartas
void mostrarMazo() {
    printf("Cartas:\n");
    for (int i = 0; i < Cartas.disponibles; i++) {
        func_ptr f = (func_ptr)Cartas.carta[i]; // Cast a func_ptr
        if (f == disparoSimple) {
            printf("Simple ");
        } else if (f == disparoGrande) {
            printf("Grande ");
        } else if (f == disparoLineal) {
            printf("Lineal ");
        } else if (f == disparoRadar) {
            printf("Radar ");
        } else if (f == disparo500KG) {
            printf("500KG ");
        }

        if (i != Cartas.disponibles - 1){
             printf("| ");
        }
    }
    printf("\n");
}

// Función para seleccionar y usar una carta del mazo
void usarCarta() {
    int seleccion = 0;
    int resultado;

    while (1) {
        mostrarMazo(); // Mostrar el mazo de cartas
        printf("Selecciona una Carta (1-%d): ", Cartas.disponibles);
        resultado = scanf("%d", &seleccion);

        // Verificar si la entrada es válida y si la selección está en rango
        if (resultado != 1 || seleccion < 1 || seleccion > Cartas.disponibles) {
            printf("Selección inválida. Inténtelo de nuevo.\n");

            // Limpiar el buffer de entrada para evitar bucles infinitos
            while (getchar() != '\n');
            continue; // Volver a pedir la selección de carta
        }

        seleccion--; // Ajustar a índice basado en 0
        func_ptr cartaSeleccionada = Cartas.carta[seleccion];

        // Determinar el tipo de carta actual
        int tipoCarta;
        if (cartaSeleccionada == disparoSimple) {
            tipoCarta = Simple;
        } else if (cartaSeleccionada == disparoGrande) {
            tipoCarta = Grande;
        } else if (cartaSeleccionada == disparoLineal) {
            tipoCarta = Lineal;
        } else if (cartaSeleccionada == disparoRadar) {
            tipoCarta = Radar;
        } else if (cartaSeleccionada == disparo500KG) {
            tipoCarta = _500KG;
        } else {
            printf("Carta no válida. Inténtelo de nuevo.\n");
            continue; // Volver a pedir la selección de carta
        }

        while (1) {
            // Leer coordenadas para el disparo
            printf("Ingrese coordenada X (Fila): ");
            resultado = scanf("%d", &coordenadaX);
            if (resultado != 1) {
                printf("Coordenada X inválida. Inténtelo de nuevo.\n");
                while (getchar() != '\n');
                continue;
            }

            printf("Ingrese coordenada Y (Columna): ");
            resultado = scanf("%d", &coordenadaY);
            if (resultado != 1) {
                printf("Coordenada Y inválida. Inténtelo de nuevo.\n");
                while (getchar() != '\n');
                continue;
            }

            // Verificar que las coordenadas estén dentro de los límites del tablero
            if (coordenadaX < 1 || coordenadaX > tamanoTablero || coordenadaY < 1 || coordenadaY > tamanoTablero) {
                printf("Coordenadas fuera de rango. Inténtelo de nuevo.\n");
            } else {
                // Coordenadas válidas, ejecutar la función de la carta seleccionada
                cartaSeleccionada(coordenadaX, coordenadaY);

                // Cambiar la carta según las probabilidades
                cambiarCarta(seleccion, tipoCarta);
                return; // Salir de la función después de un disparo válido
            }
        }
    }
}

// Liberar la memoria asignada al mazo de cartas
void liberarMazo() {
    free(Cartas.carta);
}

// Función para generar un número aleatorio entre 0 y max (incluido)
int generarNumeroAleatorio(int max) {
    return rand() % (max + 1);
}

// Función para cambiar la carta según las probabilidades
void cambiarCarta(int indiceCarta, int tipoCarta) {
    int random = generarNumeroAleatorio(99);

    if (tipoCarta == Simple) {
        if (random < 65) {
            Cartas.carta[indiceCarta] = (void *)funciones[Simple];
        } else if (random < 85) {
            Cartas.carta[indiceCarta] = (void *)funciones[Grande];
        } else if (random < 90) {
            Cartas.carta[indiceCarta] = (void *)funciones[Lineal];
        } else {
            Cartas.carta[indiceCarta] = (void *)funciones[Radar];
        }
         
    } else if (tipoCarta == Grande) {
        if (random < 80) {
            Cartas.carta[indiceCarta] = (void *)funciones[Simple];
        } else if (random < 83) {
            Cartas.carta[indiceCarta] = (void *)funciones[Grande];
        } else if (random < 93) {
            Cartas.carta[indiceCarta] = (void *)funciones[Lineal];
        } else if (random < 98 || flag_500KG_usada) { // 500KG es posible solo si no ha sido usada
            Cartas.carta[indiceCarta] = (void *)funciones[Radar];
        } else {
            Cartas.carta[indiceCarta] = (void *)funciones[_500KG];
        }

    } else if (tipoCarta == Lineal) {
        if (random < 85) {
            Cartas.carta[indiceCarta] = (void *)funciones[Simple];
        } else if (random < 90) {
            Cartas.carta[indiceCarta] = (void *)funciones[Grande];
        } else if (random < 92) {
            Cartas.carta[indiceCarta] = (void *)funciones[Lineal];
        } else if (random < 98 || flag_500KG_usada) { // 500KG es posible solo si no ha sido usada
            Cartas.carta[indiceCarta] = (void *)funciones[Radar];
        } else {
            Cartas.carta[indiceCarta] = (void *)funciones[_500KG];
        }

    } else if (tipoCarta == Radar) {
        if (random < 75) {
            Cartas.carta[indiceCarta] = (void *)funciones[Simple];
        } else if (random < 90) {
            Cartas.carta[indiceCarta] = (void *)funciones[Grande];
        } else if (random < 95) {
            Cartas.carta[indiceCarta] = (void *)funciones[Lineal];
        } else if (random < 97 || flag_500KG_usada) { // 500KG es posible solo si no ha sido usada
            Cartas.carta[indiceCarta] = (void *)funciones[Radar];
        } else {
            Cartas.carta[indiceCarta] = (void *)funciones[_500KG];
        }

    } else if (tipoCarta == _500KG) {
        // No se cambia la carta y se incapacita el cañón
        Cartas.disponibles--;
        if (Cartas.disponibles <= 0) {
            printf("No hay más cartas disponibles.\n");
            liberarMazo();
            exit(EXIT_FAILURE);
        }
        for (int i = indiceCarta; i < Cartas.disponibles; i++) {
            Cartas.carta[i] = Cartas.carta[i + 1];
        }
        Cartas.carta = realloc(Cartas.carta, Cartas.disponibles * sizeof(void *));
        if (Cartas.carta == NULL) {
            perror("Failed to reallocate memory");
            exit(EXIT_FAILURE);
        }
    }
}
