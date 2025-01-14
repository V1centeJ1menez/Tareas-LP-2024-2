#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tablero.h"

// Definir la variable global para almacenar el tablero y el tamaño del mismo
void ***tablero = NULL; // Puntero triple para representar un tablero 2D dinámico
int tamanoTablero = 0;  // Variable global para almacenar el tamaño del tablero

// Función para inicializar el tablero con un tamaño dado
void inicializarTablero(int tamano) {
    tamanoTablero = tamano; // Almacenar el tamaño en la variable global

    // Asignar memoria para las filas del tablero (array de punteros a punteros)
    tablero = (void ***)malloc(tamano * sizeof(void **));
    if (tablero == NULL) {
        perror("Error al asignar memoria para las filas del tablero");
        exit(EXIT_FAILURE); // Terminar el programa si hay error de asignación
    }

    // Asignar memoria para cada fila y cada celda del tablero
    for (int i = 0; i < tamano; i++) {
        tablero[i] = (void **)malloc(tamano * sizeof(void *)); // Asignar memoria para las columnas
        if (tablero[i] == NULL) {
            perror("Error al asignar memoria para las columnas del tablero");
            exit(EXIT_FAILURE); // Terminar el programa si hay error de asignación
        }

        for (int j = 0; j < tamano; j++) {
            tablero[i][j] = malloc(sizeof(char)); // Asignar memoria para cada celda
            if (tablero[i][j] == NULL) {
                perror("Error al asignar memoria para la celda del tablero");
                exit(EXIT_FAILURE); // Terminar el programa si hay error de asignación
            }
            *(char *)(tablero[i][j]) = ' '; // Inicializar la celda con un espacio en blanco
        }
    }
}

// Función para verificar si el juego ha terminado (todos los barcos destruidos)
int verificarJuegoTerminado() {
    for (int i = 0; i < tamanoTablero; i++) {
        for (int j = 0; j < tamanoTablero; j++) {
            char celda = *(char *)(tablero[i][j]); // Obtener el contenido de la celda
            if ((celda >= '2' && celda <= '5') || celda == '!') {
                // Si hay alguna parte de un barco sin destruir, el juego no ha terminado
                return 0; // El juego continúa
            }
        }
    }
    return 1; // Todos los barcos han sido destruidos, el juego ha terminado
}

// Función para mostrar el tablero durante el juego (ocultando barcos)
void mostrarTablero() {
    // Imprimir números de columna
    printf("   ");
    for (int j = 0; j < tamanoTablero; j++) {
        if (j < 9) {
            printf("| %d ", j + 1); // Ajustar formato para columnas de 1 a 9
        } else {
            printf("|%d ", j + 1); // Ajustar formato para columnas de 10 en adelante
        }
    }
    printf("|\n---");
    for (int j = 0; j < tamanoTablero; j++) {
        printf("|---"); // Imprimir separadores entre celdas
    }
    printf("|\n");

    // Imprimir cada fila del tablero
    for (int i = 0; i < tamanoTablero; i++) {
        printf("%2d ", i + 1); // Imprimir número de fila (1-indexado)
        for (int j = 0; j < tamanoTablero; j++) {
            char celda = *(char *)(tablero[i][j]);
            if (celda >= '2' && celda <= '5') {
                printf("|   "); // Ocultar los barcos
            } else {
                printf("| %c ", celda); // Mostrar disparos y espacios en blanco
            }
        }
        printf("|\n---");
        for (int j = 0; j < tamanoTablero; j++) {
            printf("|---"); // Imprimir separadores entre celdas
        }
        printf("|\n");
    }
}

// Función para mostrar el tablero al final del juego (revelando la ubicación de los barcos)
void mostrarTableroFinal() {
    // Imprimir números de columna
    printf("   ");
    for (int j = 0; j < tamanoTablero; j++) {
        if (j < 9) {
            printf("| %d ", j + 1); // Ajustar formato para columnas de 1 a 9
        } else {
            printf("|%d ", j + 1); // Ajustar formato para columnas de 10 en adelante
        }
    }
    printf("|\n---");
    for (int j = 0; j < tamanoTablero; j++) {
        printf("|---"); // Imprimir separadores entre celdas
    }
    printf("|\n");

    // Imprimir cada fila del tablero
    for (int i = 0; i < tamanoTablero; i++) {
        printf("%2d ", i + 1); // Imprimir número de fila (1-indexado)
        for (int j = 0; j < tamanoTablero; j++) {
            char celda = *(char *)(tablero[i][j]);
            if (celda == 'X') {
                printf("| X "); // Mostrar partes de barcos destruidos
            } else if (celda == 'O') {
                printf("| O "); // Mostrar disparos fallidos
            } else if (celda >= '2' && celda <= '5') {
                printf("| %c ", celda); // Mostrar partes de barcos no destruidos
            } else if (celda == '!'){
                printf("| ! "); //Dibujar celdas vizualizadas
            } else{
                printf("|   "); // Dejar en blanco las celdas no disparadas
            }
        }
        printf("|\n---");
        for (int j = 0; j < tamanoTablero; j++) {
            printf("|---"); // Imprimir separadores entre celdas
        }
        printf("|\n");
    }
}

// Función para colocar los barcos en el tablero de manera aleatoria
void colocarBarcos(int tamano) {
    srand(time(NULL)); // Inicializar el generador de números aleatorios

    // Definir los barcos con su tamaño y símbolo correspondiente
    struct {
        int cantidad;
        int tamaño;
        char simbolo;
    } barcos[] = {
        {2, 2, '2'},  // 2 barcos de tamaño 1x2
        {1, 3, '3'},  // 1 barco de tamaño 1x3
        {1, 4, '4'},  // 1 barco de tamaño 1x4
        {1, 5, '5'}   // 1 barco de tamaño 1x5
    };

    // Ajustar la cantidad de barcos según la dificultad
    if (tamano == 11) { // Fácil
        barcos[0].cantidad = 2;
        barcos[1].cantidad = 1;
        barcos[2].cantidad = 1;
        barcos[3].cantidad = 1;
    } else if (tamano == 17) { // Medio
        barcos[0].cantidad = 3;
        barcos[1].cantidad = 2;
        barcos[2].cantidad = 1;
        barcos[3].cantidad = 1;
    } else if (tamano == 21) { // Difícil
        barcos[0].cantidad = 3;
        barcos[1].cantidad = 2;
        barcos[2].cantidad = 2;
        barcos[3].cantidad = 2;
    }

    int numBarcos = sizeof(barcos) / sizeof(barcos[0]); // Número total de tipos de barcos

    // Colocar cada barco en el tablero
    for (int b = 0; b < numBarcos; b++) {
        for (int k = 0; k < barcos[b].cantidad; k++) {
            int largo = barcos[b].tamaño;
            char simbolo = barcos[b].simbolo;
            int x, y;
            int orientacion = rand() % 2; // 0 = horizontal, 1 = vertical
            int colocado = 0;

            while (!colocado) {
                if (orientacion == 0) { // Colocación horizontal
                    x = rand() % tamano;
                    y = rand() % (tamano - largo + 1);

                    // Verificar si hay espacio libre para el barco
                    int espacioLibre = 1;
                    for (int l = 0; l < largo; l++) {
                        if (*(char *)(tablero[x][y + l]) != ' ') {
                            espacioLibre = 0;
                            break;
                        }
                    }

                    if (espacioLibre) {
                        // Colocar el barco en las celdas disponibles
                        for (int l = 0; l < largo; l++) {
                            *(char *)(tablero[x][y + l]) = simbolo;
                        }
                        colocado = 1; // Marcar el barco como colocado
                    }
                } else { // Colocación vertical
                    x = rand() % (tamano - largo + 1);
                    y = rand() % tamano;

                    // Verificar si hay espacio libre para el barco
                    int espacioLibre = 1;
                    for (int l = 0; l < largo; l++) {
                        if (*(char *)(tablero[x + l][y]) != ' ') {
                            espacioLibre = 0;
                            break;
                        }
                    }

                    if (espacioLibre) {
                        // Colocar el barco en las celdas disponibles
                        for (int l = 0; l < largo; l++) {
                            *(char *)(tablero[x + l][y]) = simbolo;
                        }
                        colocado = 1; // Marcar el barco como colocado
                    }
                }
            }
        }
    }
}

// Función para liberar la memoria asignada al tablero
void liberarTablero() {
    if (tablero != NULL) {
        // Liberar cada celda del tablero
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < tamanoTablero; j++) {
                free(tablero[i][j]);
            }
            free(tablero[i]); // Liberar cada fila del tablero
        }
        free(tablero); // Liberar el array de filas del tablero
        tablero = NULL; // Asegurarse de que el puntero global apunte a NULL
    }
}
