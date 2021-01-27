GREEN=\033[0;1;92m
NC=\033[0m

build: FORCE
	rm -fr build_tmp
	mkdir -p build_tmp
	mkdir -p build/cmake
	cp -r build/cmake build_tmp
	rm -fr build/*
	cp -r build_tmp/cmake build
	rm -fr build_tmp
	
	# cmake -DCMAKE_BUILD_TYPE=Debug -G "CodeBlocks - Unix Makefiles" ./src -B build/cmake/debug
	cmake -DCMAKE_BUILD_TYPE=Release -G "CodeBlocks - Unix Makefiles" ./src -B build/cmake/release
	# cmake --build build/cmake/debug --target all
	cmake --build build/cmake/release --target all
	
	cp build/cmake/release/nr-gnb build/
	cp build/cmake/release/nr-ue build/
	
	@printf "${GREEN}UERANSIM successfully built.${NC}\n"

FORCE:
