# UERANSIM
Open source 5G UE and RAN (gNodeB) simulator. This tool can be used as UE/RAN emulator with additional capability of load testing.

##  General Info  
**3GPP Release:** 15.2.0  
**Access Type:** 5G-SA (Standalone Access)  
**Connection Type:** 3GPP Access  

## Features 
- UE | Registration
  - Initial Registration
  - Periodic Registration
- UE | Authentication
  - EAP AKA'
  - 5G AKA
- UE | MM Common Procedures
  - Security Mode Control
  - Identification
  - Generic UE Configuration Update
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
- PDU Session Establishment
  - ***in progress***
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
1. Make sure that `JAVA_HOME` variable is set.
2. Run the following command:
    ```
    ./build.sh
    ```


## Running
1. Select a configuration profile by modifying `config/root.yaml`.
2. (Optional)  You can further modify the profile configurations if you want. (`config/open5gs`, `config/free5gc`, etc.)
3. Execute `./run.sh` to start the application.
4. Use `tail -f app.log` to monitor logs realtime.
5. Use terminal to trigger test events such as `initial-registration`

## FAQ

**Q1. Why am I getting java.net.SocketException: Protocol not supported exception?**  

This error usually happens if you are using some Linux VM container in Windows. Windows does not support SCTP protokol, therefore *physical Linux machine is required*. Otherwise SCTP won't work.

If you are using physical Linux machine, but still encounter this issue, make sure that you have Ubuntu 16.04 or later.

**Q2. I can't build native libraries.**

Make sure that you set the `JAVA_HOME` environment variable, and have correct version of gcc/g++. You can check the current version with `gcc -v`. In order to upgrade gcc/g++ run the following command:
```
sudo apt update
sudo apt upgrade
sudo apt install g++
``` 

**Q3. How to increase the number of UE and RANs?**

Multiple UE and RAN feature (as well as load testing) is currently under development. However if you want to pre-access this feature, just check the LoadTest.java file. You can directly run the main method of tr.havelsan.ueransim.app.LoadTest class.

Also don't forget to check app.log and loadtest.log files with tail -f command.

**Q4. Are user plane functionalities supported?**

Not yet, but in progress now. 