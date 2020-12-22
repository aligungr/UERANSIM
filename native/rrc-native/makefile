BASE_FLAGS = -Wall -O2 -fPIC -DASN_DISABLE_OER_SUPPORT

librrc-native.so: librrc-generated.a librrc-basis.a
	g++ -std=c++14 -shared $(BASE_FLAGS) -o librrc-native.so -Wl,--whole-archive librrc-basis.a -Wl,--no-whole-archive librrc-generated.a

librrc-generated.a: $(wildcard src/asn_generated/*.c) $(wildcard src/asn_generated/*.h)
	cd src/asn_generated && gcc -c $(BASE_FLAGS) -I. *.c
	cd src/asn_generated && ar rcs librrc-generated.a *.o
	mv src/asn_generated/librrc-generated.a ./

librrc-basis.a: $(wildcard src/*.cpp) $(wildcard src/*.h)
	cd src/ && g++ -c $(BASE_FLAGS) -std=c++14 -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux -I./asn_generated *.cpp
	cd src/ && ar rcs librrc-basis.a *.o
	mv src/librrc-basis.a ./

clean:
	rm -f ./src/asn_generated/*.o
	rm -f ./src/asn_generated/*.a
	rm -f ./src/*.o
	rm -f ./src/*.a
	rm -f *.o *.so *.a
