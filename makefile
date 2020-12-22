RED=\033[0;31m
GREEN=\033[0;32m
CYAN=\033[1;36m
NC=\033[0m

build: FORCE
	@printf "${CYAN}UERANSIM is building. Please wait. This may take a while.${NC}\n"
	mkdir -p build
	(cd native/ngap-native && make && cp -f libngap-native.so ../../build)
	(cd native/rrc-native && make && cp -f librrc-native.so ../../build)
	(cd native/crypto-native && make && cp -f libcrypto-native.so ../../build)
	(cd native/app-native && make && cp -f libapp-native.so ../../build)
	(cd native/uesimtun && make -f ue-binder.mk && cp -f libue-binder.so ../../build)
	mvn package
	cp ueransim/target/ueransim-release.jar build/
	cp utils/target/utils-release.jar build/
	cp crypto/target/crypto-release.jar build/
	cp gtp/target/gtp-release.jar build/
	cp nas/target/nas-release.jar build/
	cp ngap/target/ngap-release.jar build/
	cp rrc/target/rrc-release.jar build/
	cp icmp/target/icmp-release.jar build/
	cp itms/target/itms-release.jar build/
	cp mts/target/mts-release.jar build/
	cp sctp/target/sctp-release.jar build/
	cp asn/target/asn-release.jar build/
	cp misc/version build/
	@printf "${GREEN}UERANSIM successfully built.${NC}\n"

clean: FORCE
	mkdir -p build
	rm -fr build/*
	(cd native/ngap-native && make clean)
	(cd native/rrc-native && make clean)
	(cd native/crypto-native && make clean)
	(cd native/app-native && make clean)
	(cd native/uesimtun && make -f ue-binder.mk clean)
	mvn -q clean
	@printf "Clean operation is done.${NC}\n"

FORCE:
