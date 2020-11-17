RED=\033[0;31m
GREEN=\033[0;32m
CYAN=\033[1;36m
NC=\033[0m

build: FORCE
	@printf "${CYAN}UERANSIM is building. Please wait. This may take a while.${NC}\n"
	mkdir -p build
	(cd native/ngap-native && make && cp -f libngap-native.so ../../build)
	(cd native/crypto-native && make && cp -f libcrypto-native.so ../../build)
	(cd native/app-native && make && cp -f libapp-native.so ../../build)
	(cd native/uesimtun && make -f tun-agent.mk && cp -f tun-agent ../../build)
	(cd native/uesimtun && make -f ue-binder.mk && cp -f libue-binder.so ../../build)
	(cd native/uesimtun && cp -f ue-binder.sh ../../build)
	mvn package
	cp ueransim/target/ueransim-1.0.8.jar build/
	cp utils/target/utils-1.0.8.jar build/
	cp crypto/target/crypto-1.0.8.jar build/
	cp gtp/target/gtp-1.0.8.jar build/
	cp nas/target/nas-1.0.8.jar build/
	cp ngap/target/ngap-1.0.8.jar build/
	cp icmp/target/icmp-1.0.8.jar build/
	cp itms/target/itms-1.0.8.jar build/
	cp mts/target/mts-1.0.8.jar build/
	cp sctp/target/sctp-1.0.8.jar build/
	@printf "${GREEN}UERANSIM successfully built.${NC}\n"

clean: FORCE
	mkdir -p build
	rm -fr build/*
	(cd native/ngap-native && make clean)
	(cd native/crypto-native && make clean)
	(cd native/app-native && make clean)
	(cd native/uesimtun && make -f tun-agent.mk clean)
	(cd native/uesimtun && make -f ue-binder.mk clean)
	mvn clean
	@printf "Clean operation is done.${NC}\n"

FORCE: