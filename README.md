# ue-ran-sim
5G UE/RAN Simulator 

---

## Installation

### 1. Clone repository from github

```
$ cd Desktop
$ git clone https://github.com/aligungr/ue-ran-sim
$ cd ue-ran-sim
```

### 2. Either buy or download trial version of [Marben ASNSDK TCE-Java](https://www.marben-products.com/freetrial-download-asnsdk/?project=asnsdk).

### 3. Run ASN Compiler
1. Extract the package and put your license to `delivery-COMP-XX/Compiler/license` folder with name `license.dat`.
2. `cd` to `delivery-COMP-XX/Compiler/bin` directory.
3. Run following command to run ASN Compiler:
```
java -jar asnci.jar
```
4. Select from menu `File` -> `Open`.
5. Open project file at `ue-ran-sim/misc/marben/project.pra`.
6. Select `Parameters` tab at the right panel.
7. Specify output directory as `ue-ran-sim/backend/src/main/java/com/runsim/backend/ngap`. If `ngap` folder is not present, create it with `mkdir`.
8. Finally click `Run` -> `Compile`.  
This will generate necessary .java source files for NGAP protocol.

### 4. Move `asntable.dat` file
Move the file located at:
```
ue-ran-sim/backend/src/main/java/com/runsim/backend/ngap/asntable.dat
```
to following location:
```
ue-ran-sim/backend/src/main/resources/asntable.dat
```

### 5. Prepare Marben Runtime Library
1. `cd` to marben runtime directory `delivery-RT-XX/RuntimeSpe/lib`
2. copy the jar file `jrntspe.jar` to `ue-ran-sim/misc/marben/lib` folder. if `lib` folder is not present create it with `mkdir`.

### 6. Done
Now you should be able to compile maven project located at `ue-ran-sim/backend/pom.xml`