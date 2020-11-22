/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.PduSessionResource;
import tr.havelsan.ueransim.app.common.contexts.GtpUContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwGtpDownlink;
import tr.havelsan.ueransim.app.common.itms.IwPduSessionResourceCreate;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.gtp.GtpDecoder;
import tr.havelsan.ueransim.gtp.GtpEncoder;
import tr.havelsan.ueransim.gtp.GtpMessage;
import tr.havelsan.ueransim.gtp.ext.PduSessionContainerExtHeader;
import tr.havelsan.ueransim.gtp.pdusup.UlPduSessionInformation;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class GtpTask extends NtsTask {

    private final GnbSimContext ctx;
    private GtpUContext gtpCtx;
    private DatagramSocket socket;
    private NtsTask mrTask;

    public GtpTask(GnbSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        this.gtpCtx = ctx.gtpUCtx;
        this.mrTask = ctx.nts.findTask(ItmsId.GNB_TASK_MR);

        try {
            this.socket = new DatagramSocket(ctx.config.gtpPort, InetAddress.getByName(ctx.config.host));
        } catch (Exception e) {
            Log.error(Tag.CONN, "Failed to bind UDP/GTP socket %s:%s (%s)", ctx.config.gtpPort, ctx.config.host, e.toString());
            return;
        }

        var receiverThread = new Thread(() -> {
            var buffer = new byte[65535];

            var datagram = new DatagramPacket(buffer, buffer.length);

            while (true) {
                try {
                    socket.receive(datagram);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                push(new IwGtpDownlink(new OctetString(datagram.getData(), datagram.getOffset(), datagram.getLength()), datagram.getAddress(), datagram.getPort()));
            }
        });

        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(getThread()));

        receiverThread.start();

        while (true) {
            var msg = take();
            if (msg instanceof IwPduSessionResourceCreate) {
                handleTunnelCreate(((IwPduSessionResourceCreate) msg).pduSessionResource);
            } else if (msg instanceof IwUplinkData) {
                handleUplinkData((IwUplinkData) msg);
            } else if (msg instanceof IwGtpDownlink) {
                handleDownlinkGtp((IwGtpDownlink) msg);
            }
        }
    }

    private void handleUplinkData(IwUplinkData msg) {
        var data = msg.ipData;
        if ((data.get(0) >> 4 & 0xF) != 4) {
            // ignore non IPv4 packets
            return;
        }

        var pduSessionOpt = this.gtpCtx.pduSessions.stream()
                .filter(r -> r.ueId.equals(msg.ueId) && r.pduSessionId == msg.pduSessionId)
                .findFirst();
        if (pduSessionOpt.isEmpty()) {
            Log.error(Tag.GTP, "TEID not found on GTP-U Uplink");
            return;
        }

        var pduSession = pduSessionOpt.get();

        var gtp = new GtpMessage();
        gtp.payload = data;
        gtp.msgType = new Octet(GtpMessage.MT_G_PDU);
        gtp.teid = pduSession.upLayer.gTPTunnel.gTP_TEID.value.get4(0);
        gtp.extHeaders = new ArrayList<>();

        var ul = new UlPduSessionInformation();
        // TODO: currently using first QSI
        ul.qfi = new Bit6(pduSession.qosFlows.get(0).qosFlowIdentifier.value);

        var cont = new PduSessionContainerExtHeader();
        cont.pduSessionInformation = ul;
        gtp.extHeaders.add(cont);

        sendGtp(gtp, pduSession.upLayer.gTPTunnel.transportLayerAddress.value.toByteArray());
    }

    private void sendGtp(GtpMessage msg, byte[] address) {
        try {
            var data = GtpEncoder.encode(msg);
            var pck = new DatagramPacket(data, data.length, InetAddress.getByAddress(address), 2152);
            socket.send(pck);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDownlinkGtp(IwGtpDownlink msg) {
        var gtp = GtpDecoder.decode(msg.data);

        var pduSession = this.gtpCtx.pduSessions.stream()
                .filter(r -> r.upLayer.gTPTunnel.gTP_TEID.value.equals(gtp.teid.toOctetString()))
                .findFirst();
        if (pduSession.isEmpty()) {
            Log.error(Tag.GTP, "TEID not found on GTP-U Downlink");
            return;
        }

        if (gtp.msgType.intValue() != GtpMessage.MT_G_PDU) {
            Log.error(Tag.NIMPL, "Unhandled GTP-U message type: " + gtp.msgType);
            return;
        }

        var ueId = pduSession.get().ueId;
        var ipPacket = gtp.payload;

        mrTask.push(new IwDownlinkData(ueId, ipPacket));
    }

    private void handleTunnelCreate(PduSessionResource pduSession) {
        this.gtpCtx.pduSessions.add(pduSession);
    }
}