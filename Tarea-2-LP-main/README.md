# CityDefender 

### Autor: Vicente Andrés Jiménez Sepúlveda  
### Rol: 202373523-K

## Descripción

**CityDefender** es un juego basado en el clásico Battleship, donde los jugadores intentan destruir los barcos del enemigo. Utiliza un tablero representado por un puntero triple para almacenar la información de cada celda. El juego permite disparos de diferentes tipos, desde simples hasta ataques masivos que afectan áreas grandes del tablero. 

Este proyecto pone en práctica conceptos avanzados de manejo de punteros en C, como el uso de punteros triples para la gestión dinámica de matrices 2D.

## Estructura del Proyecto

El proyecto se organiza de la siguiente manera:

Tarea2LP_202373523-k/

├── src/

│   ├── CityDefender.c        

│   ├── Tablero.c             

│   ├── Tablero.h    

│   ├── Cartas.c               

│   ├── Cartas.h              

├── README.md          

├── Makefile           

- **src/CityDefender.c**: Archivo principal del programa.
- **src/Tablero.c**: Implementación de las funciones relacionadas con el tablero.
- **src/Tablero.h**: Declaraciones de las funciones y estructuras relacionadas con el tablero.
- **src/Cartas.c**: Implementación de las funciones relacionadas con las cartas.
- **src/Cartas.h**: Declaraciones de las funciones y estructuras relacionadas con las cartas.
- **README.md**: Documentación del proyecto.
- **Makefile**: Archivo para automatizar la compilación.

## Compilación y Ejecución

Para compilar y ejecutar **CityDefender**, sigue estos pasos:

### Requisitos

- Un compilador de C (GCC recomendado).
- Sistema operativo Linux, macOS o Windows con un entorno de desarrollo compatible.
- `Make` para la automatización de la compilación.

### Instrucciones de Compilación

1. Clona el repositorio o descarga los archivos del proyecto.
2. Navega al directorio del proyecto:
   ```bash
   cd CityDefender
3. Compila el proyecto utilizando el Makefile proporcionado:
   ```bash
   make clean # Limpia los archivos de compilación previos
   make       # Compila el proyecto
4. Ejectura el juego:
   
   Para una ejecución normal:
   ```bash
   make run 
   ```
   Para ejecutar el juego con Valgrind (para análisis de memoria):
   ```bash
   make valgrind
   ``` 
### Descripción de Comandos de Makefile
- `make clean`: Elimina los archivos generados durante la compilación, limpiando el directorio build y el archivo ejecutable.
- `make`: Compila el proyecto, generando el archivo ejecutable CityDefender a partir de los archivos de origen.
- `make run`: Ejecuta el juego normalmente.
- `make valgrind`: Ejecuta el juego con Valgrind para verificar fugas de memoria y otros problemas relacionados con el manejo de memoria.
 
### Ejemplos

Al iniciar el juego, se te presentará un menú para seleccionar la dificultad:

Selecciona la Dificultad:

1. Facil -> 11 X 11, 5 Barcos
2. Medio -> 17 X 17, 7 Barcos
3. Dificil -> 21 X 21, 9 Barcos
4. Salir Ingrese Numero:

Después de seleccionar la dificultad, el juego se inicializará y se te pedirá que uses cartas para atacar el tablero enemigo. Cada turno te mostrará el estado actual del tablero y el número de turnos restantes.

## Notas
* El uso de punteros triples permite una mayor flexibilidad en la gestión de la memoria del tablero, aunque también introduce una mayor complejidad en el manejo de los datos.
* Asegúrate de liberar toda la memoria dinámica asignada para evitar fugas de memoria.
* Este juego es un excelente ejemplo de cómo aplicar estructuras avanzadas de datos en C.
