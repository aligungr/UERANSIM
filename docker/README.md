### Deploy and test UERANSIM in Docker environment ###

Step 1. Build docker image
```bash
UERANSIM$ docker build --target ueransim --tag ueransim:latest -f docker/Dockerfile.ubuntu.18.04 .
```

Step 2. Edit gnb and ue config parameters in `docker/docker-compose.yaml`

Step 3. Launch UERANSIM container
```bash
UERANSIM$ docker-compose -f docker/docker-compose.yaml up -d
Creating ueransim ... done
```

Step 4. Verify deployment
```bash
rohan@rohan:~/temp/UERANSIM$ docker logs ueransim 
Now setting these variables '@GTP_IP@ @IGNORE_STREAM_IDS@ @LINK_IP@ @MCC@ @MNC@ @NCI@ @NGAP_IP@ @NGAP_PEER_IP@ @SD@ @SST@ @TAC@'
Now setting these variables '@AMF_VALUE@ @APN@ @GNB_IP_ADDRESS@ @IMEI@ @IMEI_SV@ @IMSI@ @KEY@ @MCC@ @MNC@ @OP@ @OP_TYPE@ @PDU_TYPE@ @SD@ @SD_C@ @SD_D@ @SST@ @SST_C@ @SST_D@'
Done setting the configuration
### Running ueransim ###
Running gnb
UERANSIM v3.2.4
[2021-11-28 22:36:26.755] [sctp] [info] Trying to establish SCTP connection... (192.168.70.132:38412)
[2021-11-28 22:36:26.762] [sctp] [info] SCTP connection established (192.168.70.132:38412)
[2021-11-28 22:36:26.762] [sctp] [debug] SCTP association setup ascId[51]
[2021-11-28 22:36:26.763] [ngap] [debug] Sending NG Setup Request
[2021-11-28 22:36:26.769] [ngap] [debug] NG Setup Response received
[2021-11-28 22:36:26.769] [ngap] [info] NG Setup procedure is successful
Running ue
UERANSIM v3.2.4
[2021-11-28 22:36:27.708] [nas] [info] UE switches to state [MM-DEREGISTERED/PLMN-SEARCH]
[2021-11-28 22:36:27.709] [rrc] [debug] UE[1] new signal detected
[2021-11-28 22:36:27.709] [rrc] [debug] New signal detected for cell[1], total [1] cells in coverage
[2021-11-28 22:36:27.709] [nas] [info] Selected plmn[208/95]
[2021-11-28 22:36:27.709] [rrc] [info] Selected cell plmn[208/95] tac[40960] category[SUITABLE]
[2021-11-28 22:36:27.709] [nas] [info] UE switches to state [MM-DEREGISTERED/PS]
[2021-11-28 22:36:27.709] [nas] [info] UE switches to state [MM-DEREGISTERED/NORMAL-SERVICE]
[2021-11-28 22:36:27.709] [nas] [debug] Initial registration required due to [MM-DEREG-NORMAL-SERVICE]
[2021-11-28 22:36:27.710] [nas] [debug] UAC access attempt is allowed for identity[0], category[MO_sig]
[2021-11-28 22:36:27.710] [nas] [debug] Sending Initial Registration
[2021-11-28 22:36:27.710] [nas] [info] UE switches to state [MM-REGISTER-INITIATED]
[2021-11-28 22:36:27.710] [rrc] [debug] Sending RRC Setup Request
[2021-11-28 22:36:27.711] [rrc] [info] RRC Setup for UE[1]
[2021-11-28 22:36:27.711] [rrc] [info] RRC connection established
[2021-11-28 22:36:27.711] [rrc] [info] UE switches to state [RRC-CONNECTED]
[2021-11-28 22:36:27.711] [nas] [info] UE switches to state [CM-CONNECTED]
[2021-11-28 22:36:27.711] [ngap] [debug] Initial NAS message received from UE[1]
[2021-11-28 22:36:27.742] [nas] [debug] Authentication Request received
[2021-11-28 22:36:27.762] [nas] [debug] Security Mode Command received
[2021-11-28 22:36:27.763] [nas] [debug] Selected integrity[1] ciphering[1]
[2021-11-28 22:36:27.776] [ngap] [debug] Initial Context Setup Request received
[2021-11-28 22:36:27.779] [nas] [debug] Registration accept received
[2021-11-28 22:36:27.779] [nas] [info] UE switches to state [MM-REGISTERED/NORMAL-SERVICE]
[2021-11-28 22:36:27.779] [nas] [debug] Sending Registration Complete
[2021-11-28 22:36:27.779] [nas] [info] Initial Registration is successful
[2021-11-28 22:36:27.779] [nas] [debug] Sending PDU Session Establishment Request
[2021-11-28 22:36:27.779] [nas] [debug] UAC access attempt is allowed for identity[0], category[MO_sig]
[2021-11-28 22:36:28.013] [ngap] [info] PDU session resource(s) setup for UE[1] count[1]
[2021-11-28 22:36:28.015] [nas] [debug] PDU Session Establishment Accept received
[2021-11-28 22:36:28.015] [nas] [info] PDU Session establishment is successful PSI[1]
[2021-11-28 22:36:28.037] [app] [info] Connection setup for PDU session[1] is successful, TUN interface[uesimtun0, 12.1.1.2] is up.

```

Step 5. Test user traffic
```bash
UERANSIM$ docker exec ueransim ping -I uesimtun0 google.com
PING google.com (172.217.21.14) from 12.1.1.7 uesimtun0: 56(84) bytes of data.
64 bytes from mrs09s10-in-f14.1e100.net (172.217.21.14): icmp_seq=1 ttl=114 time=20.7 ms
64 bytes from mrs09s10-in-f14.1e100.net (172.217.21.14): icmp_seq=2 ttl=114 time=19.9 ms
64 bytes from mrs09s10-in-f14.1e100.net (172.217.21.14): icmp_seq=3 ttl=114 time=21.3 ms
```
