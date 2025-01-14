#include <stdio.h>
#include <stdlib.h>
#include "Tablero.h"
#include "Cartas.h"

int main(int argc, char const *argv[]) {
    (void)argc;
    (void)argv;
    
    // Variables principales del juego
    int tamano = 0; // Tamaño del tablero
    int opcion = 0; // Opción seleccionada por el usuario
    int turnosRestantes = 0; // Número de turnos restantes
    int turnoActual = 1; // Contador de turnos
    
    while (1) {
        // Mostrar el menú de selección de dificultad
        printf("Selecciona la Dificultad:\n");
        printf("1. Facil -> 11 X 11, 5 Barcos\n");
        printf("2. Medio -> 17 X 17, 7 Barcos\n");
        printf("3. Dificil -> 21 X 21, 9 Barcos\n");
        printf("4. Salir\n");
        
        // Leer la opción ingresada por el usuario
        printf("Ingrese Numero: ");
        int resultado = scanf("%d", &opcion);

        // Verificar si la entrada es válida y limpiar el buffer si no lo es
        if (resultado != 1) {
            printf("Opción no válida. Inténtelo de nuevo.\n");

            // Limpiar el buffer de entrada para evitar bucles infinitos
            while (getchar() != '\n');
            continue; // Volver a mostrar el menú
        }

        // Procesar la opción seleccionada y configurar el juego en consecuencia
        switch (opcion) {
            case 1:
                tamano = 11;
                turnosRestantes = 30; // Límite de turnos para Fácil
                break;
            case 2:
                tamano = 17;
                turnosRestantes = 35; // Límite de turnos para Medio
                break;
            case 3:
                tamano = 21;
                turnosRestantes = 15; // Límite de turnos para Difícil
                break;
            case 4:
                // Opción de salida, termina el juego
                printf("Saliendo...\n");
                return 0; // Salir del programa
            default:
                // Manejo de una opción no válida
                printf("Opción no válida. Inténtelo de nuevo.\n");
                continue; // Volver a mostrar el menú
        }
        
        // Inicializar el tablero de juego con el tamaño seleccionado
        printf("\nTurnos restantes: %d\n", turnosRestantes);
        inicializarTablero(tamano); // Configurar el tablero según la dificultad seleccionada
        colocarBarcos(tamano); // Colocar los barcos en el tablero

        // Inicializar las funciones y el mazo de cartas
        inicializarFunciones();
        inicializarMazo();

        // Bucle principal de los turnos del juego
        while (turnosRestantes > 0) {
            printf("\n--- Turno %d ---\n", turnoActual);
            mostrarTablero(); // Mostrar el tablero en el turno actual

            usarCarta(); // Permite usar una carta
            
            turnosRestantes--; // Disminuir el número de turnos restantes
            turnoActual++;      // Incrementar el contador de turnos

            // Verificar si todos los barcos han sido destruidos
            if (verificarJuegoTerminado()) {
                printf("¡Todos los barcos han sido destruidos! Fin del juego.\n");

                printf("\n--- Tablero Final ---\n");
                mostrarTableroFinal(); // Mostrar el estado final del tablero
                
                liberarTablero(); // Liberar la memoria del tablero
                liberarMazo();    // Liberar la memoria del mazo de cartas
                break; // Salir del bucle de turnos
            }
            
            printf("\nTurnos restantes: %d\n", turnosRestantes);

            // Verificar si se han agotado los turnos
            if (turnosRestantes == 0) {
                printf("Se han agotado los turnos.\n");
                if (verificarJuegoTerminado()) {
                    printf("¡Todos los barcos han sido destruidos! Fin del juego.\n");
                }
                else {
                    printf("No has logrado destruir todos los barcos. Fin del juego.\n");
                }

                printf("\n--- Tablero Final ---\n");
                mostrarTableroFinal(); // Mostrar el estado final del tablero
                liberarTablero(); // Liberar la memoria del tablero
                liberarMazo();    // Liberar la memoria del mazo de cartas
                break; // Salir del bucle de turnos
            }
        }
        
        // Reiniciar el contador de turnos para un nuevo juego
        turnoActual = 1;
        
        // Esperar la entrada del usuario para continuar o salir
        printf("Presione Enter para continuar...");
        getchar(); // Para consumir el '\n' pendiente del scanf
        getchar(); // Para esperar la entrada del usuario
    }
}
