SRC_DIR := ./src/tun-agent
OBJ_DIR := ./obj/tun-agent
SRC_FILES := $(wildcard $(SRC_DIR)/*.cpp)
OBJ_FILES := $(patsubst $(SRC_DIR)/%.cpp,$(OBJ_DIR)/%.o,$(SRC_FILES))
INC_FLAGS = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux

tun-agent: $(OBJ_FILES)
	g++ -Wall -O2 -std=c++14 -o $@ $^

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.cpp
	g++ -c $(INC_FLAGS) -Wall -O2 -std=c++14 -o $@ $<

clean:
	rm -f tun-agent
	rm -f ./obj/ue-binder/*.o ./obj/ue-binder/*.so