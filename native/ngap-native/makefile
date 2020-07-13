OTHER_FLAGS = -Wall -O2 -DASN_DISABLE_OER_SUPPORT
INC_FLAGS = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux -I./src/asn_generated

libngap-native.so:
	gcc $(OTHER_FLAGS) $(INC_FLAGS) ./src/*.c ./src/asn_generated/*.c -o $@ $^

clean:
	rm -f *.o *.so
