#
# Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
# This software and all associated files are licensed under GPL-3.0.
#

################################################################################
if [ -z "$JAVA_HOME" ]; then
echo "ERROR: 'JAVA_HOME' is not defined" 1>&2;
exit 1
fi
################################################################################
mkdir -p build
################################################################################
cd native/ngap-native
make
cp libngap-native.so ../../build/
cd ../..
################################################################################
cd native/crypto-native
make
cp libcrypto-native.so ../../build/
cd ../..
################################################################################
cd native/app-native
make
cp libapp-native.so ../../build/
cd ../..
################################################################################
mvn clean compile package
################################################################################
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
################################################################################
rm -fr ueransim/build # delete maven-dependency-plugin caused unnecessary directory