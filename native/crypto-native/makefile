SRC_DIR := ./src
OBJ_DIR := ./
SRC_FILES := $(wildcard $(SRC_DIR)/*.cpp)
OBJ_FILES := $(patsubst $(SRC_DIR)/%.cpp,$(OBJ_DIR)/%.o,$(SRC_FILES))
INC_FLAGS = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux

libcrypto-native.so: $(OBJ_FILES)
	g++ -shared -fPIC -Wall -O2 -std=c++14 -o $@ $^

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp
	g++ -fPIC -c $(INC_FLAGS) -Wall -std=c++14 -O2 -o $@ $<

clean:
	rm -f *.o *.so
