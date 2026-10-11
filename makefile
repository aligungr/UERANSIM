GREEN=\033[0;1;92m
NC=\033[0m
# Parallel build jobs, e.g. `make build JOBS=2` on a machine short of memory.
JOBS ?= $(shell nproc)

build: FORCE
	rm -fr logs # Old version log files
	mkdir -p build
	rm -fr build/*
	
	# cmake -DCMAKE_BUILD_TYPE=Debug -G "CodeBlocks - Unix Makefiles" . -B cmake-build-debug
	cmake -DCMAKE_BUILD_TYPE=Release -G "CodeBlocks - Unix Makefiles" . -B cmake-build-release
	# cmake --build cmake-build-debug --target all
	cmake --build cmake-build-release --target all --parallel $(JOBS)
	
	cp cmake-build-release/nr-gnb build/
	cp cmake-build-release/nr-ue build/
	cp cmake-build-release/nr-cli build/
	cp cmake-build-release/libdevbnd.so build/
	cp tools/nr-binder build/

	@printf "${GREEN}UERANSIM successfully built.${NC}\n"

test: FORCE
	cmake -DCMAKE_BUILD_TYPE=Release -G "CodeBlocks - Unix Makefiles" . -B cmake-build-release
	cmake --build cmake-build-release --target nr-tests --parallel $(JOBS)
	
	cd cmake-build-release && ctest --output-on-failure --no-tests=error

	@printf "${GREEN}All tests passed.${NC}\n"

FORCE:

clean:
	rm -fr build
	rm -fr cmake-build-release/