## 1. Create TUN Device

In order to create TUN device, run the following commands:

```
sudo ip tuntap add name uesimtun mode tun
```

---

## 2. Add IP to the TUN

You need to replace {IP} with UE's IP given by core network. You can also execute multiple times to add multiple IP addreses.

```
sudo ip addr add {IP} dev uesimtun
```

**Important Warning:** Make sure that no other interface in your system uses the same IP address. For example Open5GS, free5gc and other core networks may have their own TUN interface. Using both UERANSIM and core networks in same environment may cause problems.

---

## 3. Set Up The TUN

```
sudo ip link set uesimtun up
```
---
## 4. Configure Routing

**NOTE:** This configuration is persistent unlike the others. (That is, you only need to execute once.)

Now we need to create a routing table called `uesimtable` if not exist. Use `nano` or `echo` to create the table.

Run:
```
sudo nano /etc/iproute2/rt_tables
```

and insert the following line to the bottom of the file:
```
1453 uesimtable
```

---
Now run this command to create a rule (Do not forget to replace {IP}, and also you can execute multiple times in order to add  rules for multiple IP addresses):

```
sudo ip rule add from {IP} table uesimtable
```

You can validate it with `sudo ip rule`

---

Now run this command to create a route:
```
sudo ip route add default dev uesimtun table uesimtable
```

You can validate it with `sudo ip route list table uesimtable`

---

## 5. Run the TUN Agent

