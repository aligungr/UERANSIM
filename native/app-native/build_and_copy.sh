if [ -z "$JAVA_HOME" ]; then
echo "JAVA_HOME is not defined" 1>&2;
exit 1
fi

make clean
make
mkdir -p ../../ueransim/src/main/resources
cp -u libapp-native.so ../../ueransim/src/main/resources
