/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.air;

import tr.havelsan.ueransim.app.common.TargetPduSession;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwPduSessionEstablishment;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.simctx.AirSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionType;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TunBridgeTask extends NtsTask {

    private final AirSimContext ctx;
    private final Map<Integer, TargetPduSession> ipRoute;

    private InetAddress localhost;
    private DatagramSocket bridge;

    public TunBridgeTask(AirSimContext ctx) {
        this.ctx = ctx;
        this.ipRoute = new HashMap<>();
    }

    @Override
    public void main() {
        try {
            this.localhost = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            bridge = new DatagramSocket(49972, localhost);
        } catch (SocketException e) {
            Log.error(Tag.CONN, "TUN Bridge connection could not established: " + e.getMessage());
            System.exit(1);
            return;
        }

        ctx.sim.triggerOnConnected(ctx, EConnType.TUN_BRIDGE);
        Log.info(Tag.CONN, "TUN Bridge has been started.");

        var receiverThread = new Thread(this::receiverThread);
        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(getThread()));
        receiverThread.start();

        while (true) {
            var msg = take();
            if (msg instanceof IwPduSessionEstablishment) {
                handleSessionEstablishment((IwPduSessionEstablishment) msg);
            } else if (msg instanceof IwDownlinkData) {
                handleDownlinkData((IwDownlinkData) msg);
            }
        }
    }

    private void receiverThread() {
        var buffer = new byte[65535];

        var datagram = new DatagramPacket(buffer, buffer.length);

        while (true) {
            try {
                bridge.receive(datagram);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (datagram.getPort() != 49971)
                continue;
            if (!datagram.getAddress().equals(localhost))
                continue;

            var ipData = new OctetString(datagram.getData(), datagram.getOffset(), datagram.getLength());
            handleUplinkData(ipData);
        }
    }

    private void handleSessionEstablishment(IwPduSessionEstablishment msg) {
        var address = msg.pduSession.pduAddress;
        if (!address.sessionType.equals(EPduSessionType.IPV4))
            return;

        var addrBytes = address.pduAddressInformation.toByteArray();

        int addr = (addrBytes[0] << 24) | (addrBytes[1] << 16) | (addrBytes[2] << 8) | addrBytes[3];
        var ueId = msg.ueId;
        var psi = msg.pduSession.id.intValue();

        if (ipRoute.get(addr) != null) {
            Log.warning(Tag.TUN, "Another PDU Session target with IP %s already exists. Overwriting.", Utils.byteArrayToIpString(addrBytes));
        }

        var ue = ctx.sim.findUe(ueId);
        if (ue != null) {
            Log.info(Tag.TUN, "IPv4 PDU session established (%s, %s)", ue.nodeName, Utils.byteArrayToIpString(addrBytes));
            ipRoute.put(addr, new TargetPduSession(ueId, psi));
        }
    }

    private void handleUplinkData(OctetString ipData) {
        if ((ipData.get(0) >> 4 & 0xF) != 4) {
            // ignore non IPv4 packets
            return;
        }

        int addr = (ipData.get(12) << 24) | (ipData.get(13) << 16) | (ipData.get(14) << 8) | ipData.get(15);
        var target = ipRoute.get(addr);
        if (target == null) {
            Log.warning(Tag.TUN, "Tun bridge routing failed, target not found for: %s", Utils.int32ToIp4String(addr));
            return;
        }

        var ue = ctx.sim.findUe(target.ueId);
        if (ue == null) {
            Log.error(Tag.TUN, "UE with id '%s' not found.", target.ueId);
            return;
        }

        ue.nts.findTask(ItmsId.UE_TASK_MR).push(new IwUplinkData(target.ueId, target.psi, ipData));
    }

    private void handleDownlinkData(IwDownlinkData msg) {
        var data = msg.ipPacket.toByteArray();
        var pck = new DatagramPacket(data, data.length, localhost, 49971);

        try {
            bridge.send(pck);
            //packetMeter.notify(data.length);
            //System.err.println(packetMeter.speedMbsPerSec());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
