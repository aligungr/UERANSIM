SRC_DIR := ./src
OBJ_DIR := ./
SRC_FILES := $(wildcard $(SRC_DIR)/*.cpp)
OBJ_FILES := $(patsubst $(SRC_DIR)/%.cpp,$(OBJ_DIR)/%.o,$(SRC_FILES))
INC_FLAGS = -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux

libcrypto-native.so: $(OBJ_FILES)
	g++ -shared -fPIC -o $@ $^

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp
	g++ -fPIC -c $(INC_FLAGS) -o $@ $<

clean:
	rm -f *.o *.so
