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
sudo apt update
sudo apt upgrade
sudo apt install make
sudo apt install g++
sudo apt install openjdk-11-jdk
sudo apt install maven
sudo apt install libsctp-dev lksctp-tools
```

## Building
```
./build.sh
```

## Usage
1. Select a configuration profile by modifying `config/root.yaml`.
2. (Optional)  You can further modify the profile configurations if you want. (`config/havelsan`, `config/free5gc`, etc.)
3. Execute `./run.sh` to start the application.
4. Use `tail -f app.log` to monitor logs realtime.
5. Use terminal to trigger test events such as `initial-registration`