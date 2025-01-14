# Nombre del compilador
CC = gcc

# Flags de compilación
CFLAGS = -Wall -Wextra -g -Isrc

# Directorios
SRC_DIR = src
BUILD_DIR = build

# Archivos de origen
SRC = $(wildcard $(SRC_DIR)/*.c)

# Archivos objeto (se generan automáticamente a partir de los archivos de origen)
OBJ = $(SRC:$(SRC_DIR)/%.c=$(BUILD_DIR)/%.o)

# Nombre del ejecutable final
TARGET = CityDefender

# Regla por defecto (se invoca cuando se ejecuta `make` sin argumentos)
all: $(TARGET)

# Regla para compilar el ejecutable
$(TARGET): $(OBJ)
	$(CC) $(OBJ) -o $(TARGET)

# Regla para compilar archivos .c a .o
$(BUILD_DIR)/%.o: $(SRC_DIR)/%.c
	@mkdir -p $(BUILD_DIR)
	$(CC) $(CFLAGS) -c $< -o $@

# Regla para limpiar los archivos generados
clean:
	rm -rf $(BUILD_DIR) $(TARGET)

# Regla para ejecutar el programa
run: $(TARGET)
	./$(TARGET)

# Regla para ejecutar Valgrind y verificar fugas de memoria
valgrind: $(TARGET)
	valgrind --leak-check=full --track-origins=yes ./$(TARGET)
