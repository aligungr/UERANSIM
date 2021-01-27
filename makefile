GREEN=\033[0;1;92m
NC=\033[0m

build: FORCE
	mkdir -p build
	cmake -DCMAKE_BUILD_TYPE=Debug -G "CodeBlocks - Unix Makefiles" ./src -B build/debug
	cmake -DCMAKE_BUILD_TYPE=Release -G "CodeBlocks - Unix Makefiles" ./src -B build/release
	cmake --build build/debug --target all
	cmake --build build/release --target all
	@printf "${GREEN}UERANSIM successfully built.${NC}\n"

FORCE:
