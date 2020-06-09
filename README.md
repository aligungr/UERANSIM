# UERANSIM
5G UE/gNodeB Simulator 

##  Requirements
1. Ubuntu 16.04 or later 
2. OpenJDK 11 or later

## Building

#### 1. Install required dependencies
```
$ sudo apt install git
$ sudo apt install make
$ sudo apt install g++
$ sudo apt install openjdk-11-jdk
$ sudo apt install maven
$ sudo apt install libsctp-dev lksctp-tools
```

#### 2. Clone repository from github

```
$ cd Desktop
$ git clone https://github.com/aligungr/ue-ran-sim
$ cd ue-ran-sim
```

#### 3. Either buy or download trial version of [Marben ASNSDK TCE-Java](https://www.marben-products.com/freetrial-download-asnsdk/?project=asnsdk).

#### 4. Run ASN Compiler
1. Extract the package and put your license to `delivery-COMP-XX/Compiler/license` folder with name `license.dat`.
2. `cd` to `delivery-COMP-XX/Compiler/bin` directory.
3. Run following command to run ASN Compiler:
```
java -jar asnci.jar
```
4. Select from menu `File` -> `Open`.
5. Open project file at `ue-ran-sim/misc/marben/project.pra`.
6. Select `Parameters` tab at the right panel.
7. Specify output directory as `ue-ran-sim/ngap/main/java/tr/havelsan/ueransim/ngap`. If `ngap` folder is not present, create it with `mkdir`.
8. Finally click `Run` -> `Compile`.  
This will generate necessary .java source files for NGAP protocol.

#### 5. Move `asntable.dat` file
Move the file located at:
```
ue-ran-sim/ngap/src/main/java/tr/havelsan/ueransim/ngap/asntable.dat
```
to following location:
```
ue-ran-sim/ueransim/src/main/resources/asntable.dat
```

####  6. Prepare Marben Runtime Library
1. `cd` to marben runtime directory `delivery-RT-XX/RuntimeSpe/lib`
2. Copy the jar file `jrntspe.jar` to `ue-ran-sim/misc/marben/lib` folder. if `lib` folder is not present create it with `mkdir`.
3. Execute the script located at `ue-ran-sim/misc/marben/mvn_install_lib.sh`, and make sure that it successfuly installs the jar file to local repository.

#### 7. Done
Now you should be able to compile maven project located at `ue-ran-sim/pom.xml`
