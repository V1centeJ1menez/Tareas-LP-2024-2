JC = javac
SRC_DIR = src/usm/vjimenez/nojavasky
BIN_DIR = bin
MAIN_CLASS = usm.vjimenez.nojavasky.NoJavaSky

CLASSES = \
    $(SRC_DIR)/*.java \
    $(SRC_DIR)/hud/*.java \
    $(SRC_DIR)/hud/estados/*.java \
    $(SRC_DIR)/juego/controladores/*.java \
    $(SRC_DIR)/juego/entidades/*.java \
    $(SRC_DIR)/juego/entidades/planetas/*.java \
    $(SRC_DIR)/juego/entidades/planetas/tipos/*.java \
    $(SRC_DIR)/juego/inventario/*.java \
    $(SRC_DIR)/utilidad/*.java

default: classes

classes:
	mkdir -p $(BIN_DIR)
	$(JC) -d $(BIN_DIR) $(CLASSES)

run: classes
	java -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)/*.class