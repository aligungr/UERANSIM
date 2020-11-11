#
# MIT License
#
# Copyright (c) 2020 ALİ GÜNGÖR
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
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
