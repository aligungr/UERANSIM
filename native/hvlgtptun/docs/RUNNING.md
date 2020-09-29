## 1. Creating TUN Device

In order to create TUN device, run the following commands:

```
sudo ip tuntap add name hvlgtptun mode tun
sudo ip addr add 10.81.0.0/16 dev hvlgtptun
sudo ip link set hvlgtptun up
```
---

## 2. Run TUN Device

Run the following command to compile and run the TUN device at once.

```
sudo sh run_hvlgtptun.sh
```

TUN device should be remain opened, now switch to another terminal.

---

## 3. Configure Routing

First we need to create a routing table called `hvlgtptable` if not exist. Use `nano` or `echo` to create the table.

Run:
```
sudo nano /etc/iproute2/rt_tables
```

and insert the following line to the bottom of the file:
```
1453 hvlgtptable
```

---
Now run this command to create a rule:

```
sudo ip rule add from 10.81.0.0/16 table hvlgtptable
```

You can validate it with `sudo ip rule` and make sure the priority is "low" enough. (Low means high.)

---

Now run this command to create a route:
```
sudo ip route add default dev hvlgtptun table hvlgtptable
```

You can validate it with `sudo ip route list table hvlgtptable`

---
## 4. Close All Firefox Processes

First we need to kill Firefox processes. (You can replace Firefox with another browser. But Firefox is assumed in this document.)

---

## 5. Run Binded Firefox

Lastly run the following command to start Firefox with GTP TUN interface:

```
sh ./run_binder.sh firefox
```

---

## 6. Conclusion

Now you can normally use Firefox and browse the internet. All IP traffic will be forwarded to RAN.

**NOTE:** If you want to rollback all of these commands, look at ROLLBACK.md


