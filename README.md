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
  - NG Setup proc.
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

## Dependencies
```
$ sudo apt install git
$ sudo apt install make
$ sudo apt install g++
$ sudo apt install openjdk-11-jdk
$ sudo apt install maven
$ sudo apt install libsctp-dev lksctp-tools
```

## Building
You should be able to compile maven project located at `ue-ran-sim/pom.xml`  

All native libraries and Java source codes are compiled automatically on `maven compile`.