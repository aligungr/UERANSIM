SRC_DIR := ./src/ue-binder
OBJ_DIR := ./obj/ue-binder
SRC_FILES := $(wildcard $(SRC_DIR)/*.cpp)
OBJ_FILES := $(patsubst $(SRC_DIR)/%.cpp,$(OBJ_DIR)/%.o,$(SRC_FILES))
INC_FLAGS = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux

libue-binder.so: $(OBJ_FILES)
	g++ -Wall -O2 -std=c++14 -nostartfiles -fpic -shared -D_GNU_SOURCE -o $@ $^ -ldl

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp
	g++ -c $(INC_FLAGS) -Wall -O2 -std=c++14 -nostartfiles -fpic -shared -D_GNU_SOURCE -o $@ $< -ldl

clean:
	rm -f libue-binder.so
	rm -f ./obj/ue-binder/*.o ./obj/ue-binder/*.so