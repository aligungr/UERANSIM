if [ -z "$JAVA_HOME" ]; then
  echo "JAVA_HOME is not defined" 1>&2
  exit 1
fi

make clean
make
cp -u libngap-native.so ../../ngap/src/main/resources
