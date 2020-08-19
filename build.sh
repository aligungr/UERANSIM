if [ -z "$JAVA_HOME" ]; then
echo "ERROR: 'JAVA_HOME' is not defined" 1>&2;
exit 1
fi
mvn clean compile package
mkdir -p build
cp ueransim/target/ueransim-1.0.8.jar build/
