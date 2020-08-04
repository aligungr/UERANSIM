# UERANSIM
5G UE/gNodeB Simulator

##  General Info  
**3GPP Version:** 15.2.0  
**Access Type:** 5G-SA (Standalone Access)  
**Connection Type:** 3GPP Access  

## Features 
- UE | Registration
  - Initial Registration
  - Periodic Registration
- UE | Authentication
  - EAP AKA'
  - 5G AKA
- gNB | Interface Management
  - NG Setup
  - Error Indication
- gNB | UE Context Management
  - Initial Context Setup
  - UE Context Release (AMF initiated)
  - UE Context Modification
- gNB | NAS Transport
  - Initial UE Message
  - Uplink/Downlink NAS Transport
- ***todo...***
      
##  Requirements
1. Ubuntu 16.04 or later 
2. OpenJDK 11 or later
3. g++/gcc version 6.4.0 or later

## Dependencies
```
$ sudo apt update
$ sudo apt upgrade
$ sudo apt install make
$ sudo apt install g++
$ sudo apt install openjdk-11-jdk
$ sudo apt install maven
$ sudo apt install libsctp-dev lksctp-tools
```

## Building
You should be able to compile maven project located at `UERANSIM/pom.xml`  

All native libraries and Java source codes are compiled automatically on `maven compile`.

## Usage
1. Make sure that `config` folder is located at the same directory with `ueransim-1.0.8.jar`
2. Select a configuration profile by modifying `config/root.yaml`.
3. (Optional)  You can further modify the profile configurations if you want. (`config/custom`, `config/havelsan`, etc.)
4. Run `java -jar ueransim-1.0.8.jar` to start the application.
5. Use `tail -f app.log` to monitor logs realtime.
6. Use terminal to trigger test events such as `initial-registration`