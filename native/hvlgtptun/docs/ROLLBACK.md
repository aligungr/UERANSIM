## 1. Close All Firefox Windows

First close all Firefox processes just in case.

---

## 2. Terminate TUN Device

Send `Ctrl+C` to kill TUN Device process where you start the device with running `sudo sh run_hvlgtptun.sh`

---

## 3. Delete Routing Configs

Run following commands

```
sudo ip route del default table hvlgtptable
sudo ip rule del from 10.81.0.0/16
```
---

Then delete the routing table if you want:

```
sudo nano /etc/iproute2/rt_tables
````

and remove the following line:

```
1453 hvlgtptable
```

## 4. Delete TUN Interface

Run the following command to delete TUN interface:

```
sudo ip link delete hvlgtptun
```