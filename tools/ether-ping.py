#!/usr/bin/env python3
"""TAP Interface Ping — Ethernet PDU E2E Test

Sends ICMP pings over a TAP interface created by a UERANSIM.
UERANSIM handles GTP-U encapsulation; this script just injects
raw Ethernet frames into the TAP to verify end-to-end connectivity
through the UPF.

Flow:
  1. Send ARP request on the TAP to trigger MAC learning
  2. Listen for ARP replies and ICMP echo replies
  3. Send ICMP Echo Requests, print Echo Replies with RTT
"""
import sys
import time
import threading
from scapy.all import *

if len(sys.argv) < 4:
    print("Usage: python tap-ping.py <ue_ip> <target_ip> <tap_iface> [ue_mac] [count]")
    print()
    print("  ue_ip       UE IP address assigned to the PDU session")
    print("  target_ip   IP to ping on the N6 / data network")
    print("  tap_iface   TAP interface name (e.g. uesimtun0)")
    print("  ue_mac      UE MAC address (default: TAP interface MAC)")
    print("  count       Number of pings, 0=infinite (default: 4)")
    sys.exit(1)

ue_ip = sys.argv[1]
target_ip = sys.argv[2]
tap = sys.argv[3]
ue_mac = sys.argv[4] if len(sys.argv) > 4 else get_if_hwaddr(tap)
count = int(sys.argv[5]) if len(sys.argv) > 5 else 4

target_mac = None
pending = {}
lock = threading.Lock()
stats = {'sent': 0, 'recv': 0, 'rtt': []}


def handle_pkt(pkt):
    global target_mac

    if not pkt.haslayer(Ether):
        return

    if pkt.haslayer(ARP):
        arp = pkt[ARP]
        if arp.op == 1 and arp.pdst == ue_ip:
            print(f"  << ARP who-has {arp.pdst}? tell {arp.psrc} [{arp.hwsrc}]")
            if target_mac is None:
                target_mac = arp.hwsrc
            reply = (Ether(dst=arp.hwsrc, src=ue_mac) /
                     ARP(op=2, hwsrc=ue_mac, psrc=ue_ip,
                         hwdst=arp.hwsrc, pdst=arp.psrc))
            sendp(reply, iface=tap, verbose=False)
            print(f"  >> ARP reply: {ue_ip} is-at {ue_mac}")
        elif arp.op == 2 and arp.pdst == ue_ip:
            print(f"  << ARP reply: {arp.psrc} is-at {arp.hwsrc}")
            target_mac = arp.hwsrc

    elif pkt.haslayer(IP) and pkt.haslayer(ICMP):
        ip_l = pkt[IP]
        icmp_l = pkt[ICMP]

        if icmp_l.type == 0 and ip_l.dst == ue_ip:
            seq = icmp_l.seq
            with lock:
                send_time = pending.pop(seq, None)
                stats['recv'] += 1
            rtt_ms = (time.time() - send_time) * 1000 if send_time else 0
            if send_time:
                stats['rtt'].append(rtt_ms)
            print(f"  << ICMP reply from {ip_l.src}: seq={seq} time={rtt_ms:.1f}ms")

        elif icmp_l.type == 8 and ip_l.dst == ue_ip:
            print(f"  << ICMP request from {ip_l.src}: seq={icmp_l.seq}")
            reply = (Ether(dst=pkt[Ether].src, src=ue_mac) /
                     IP(src=ip_l.dst, dst=ip_l.src) /
                     ICMP(type=0, id=icmp_l.id, seq=icmp_l.seq) /
                     Raw(load=raw(icmp_l.payload)))
            sendp(reply, iface=tap, verbose=False)
            print(f"  >> ICMP reply to {ip_l.src}")


print(f"PING {target_ip} from {ue_ip} [{ue_mac}] via {tap}")
print()

sniffer = AsyncSniffer(iface=tap, prn=handle_pkt, store=False)
sniffer.start()
time.sleep(0.3)

arp_req = (Ether(dst='ff:ff:ff:ff:ff:ff', src=ue_mac) /
           ARP(op=1, hwsrc=ue_mac, psrc=ue_ip, pdst=target_ip))
sendp(arp_req, iface=tap, verbose=False)
print(f"  >> ARP who-has {target_ip}?")
time.sleep(1)

try:
    seq = 1
    while count == 0 or seq <= count:
        dst = target_mac or 'ff:ff:ff:ff:ff:ff'
        ping = (Ether(dst=dst, src=ue_mac) /
                IP(src=ue_ip, dst=target_ip) /
                ICMP(type=8, id=0x1234, seq=seq))
        with lock:
            pending[seq] = time.time()
            stats['sent'] += 1
        sendp(ping, iface=tap, verbose=False)
        print(f"  >> ICMP request to {target_ip}: seq={seq}")
        seq += 1
        time.sleep(1)
except KeyboardInterrupt:
    pass

time.sleep(2)
sniffer.stop()

print(f"\n--- {target_ip} ping statistics ---")
loss = 100 - (stats['recv'] / max(stats['sent'], 1) * 100)
print(f"{stats['sent']} sent, {stats['recv']} received, {loss:.0f}% loss")
if stats['rtt']:
    print(f"rtt min/avg/max = {min(stats['rtt']):.1f}/{sum(stats['rtt'])/len(stats['rtt']):.1f}/{max(stats['rtt']):.1f} ms")
