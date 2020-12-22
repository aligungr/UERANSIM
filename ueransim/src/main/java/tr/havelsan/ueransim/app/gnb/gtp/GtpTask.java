/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.PduSessionResource;
import tr.havelsan.ueransim.app.common.contexts.GtpContext;
import tr.havelsan.ueransim.app.common.contexts.GtpUeContext;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.gtp.GtpDecoder;
import tr.havelsan.ueransim.gtp.GtpEncoder;
import tr.havelsan.ueransim.gtp.GtpMessage;
import tr.havelsan.ueransim.gtp.ext.PduSessionContainerExtHeader;
import tr.havelsan.ueransim.gtp.pdusup.UlPduSessionInformation;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
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
import java.util.UUID;

public class GtpTask extends NtsTask {

    private final GtpContext ctx;
    private final IRateLimiter rateLimiter;

    public GtpTask(GnbSimContext ctx) {
        this.ctx = new GtpContext(ctx);
        this.rateLimiter = new RateLimiter();
    }

    private static void udpReceiverThread(DatagramSocket socket, NtsTask gtpTask) {
        var buffer = new byte[65535];

        var datagram = new DatagramPacket(buffer, buffer.length);

        while (true) {
            try {
                socket.receive(datagram);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gtpTask.push(new IwGtpDownlink(new OctetString(datagram.getData(), datagram.getOffset(), datagram.getLength()), datagram.getAddress(), datagram.getPort()));
        }
    }

    @Override
    protected void main() {
        ctx.mrTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_MR);
        try {
            ctx.socket = new DatagramSocket(2152, InetAddress.getByName(ctx.gnbCtx.config.gtpIp));

        } catch (Exception e) {
            Log.error(Tag.CONN, "Failed to bind UDP/GTP socket %s:%s (%s)", ctx.gnbCtx.config.gtpIp, 2152, e.toString());
            return;
        }
        var receiverThread = new Thread(() -> udpReceiverThread(ctx.socket, this));
        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(getThread()));
        receiverThread.start();

        while (true) {
            var msg = take();
            if (msg instanceof IwUeContextCreate) {
                handleContextCreate((IwUeContextCreate) msg);
            } else if (msg instanceof IwUeContextUpdate) {
                handleContextUpdate((IwUeContextUpdate) msg);
            } else if (msg instanceof IwPduSessionResourceCreate) {
                handleSessionCreate(((IwPduSessionResourceCreate) msg).pduSessionResource);
            } else if (msg instanceof IwUplinkData) {
                handleUplinkData((IwUplinkData) msg);
            } else if (msg instanceof IwGtpDownlink) {
                handleDownlinkGtp((IwGtpDownlink) msg);
            }
        }
    }

    private void handleContextCreate(IwUeContextCreate msg) {
        var ueCtx = new GtpUeContext(msg.ueId);
        ueCtx.ambr = msg.ambr;

        ctx.ueMap.put(ueCtx.ueId, ueCtx);

        updateAmbrForUe(ueCtx.ueId);
    }

    private void handleContextUpdate(IwUeContextUpdate msg) {
        var ueCtx = ctx.ueMap.get(msg.ueId);
        if (ueCtx == null) {
            Log.error(Tag.GTP, "GTP UE context not found.");
            return;
        }

        if (msg.ambr != null) {
            ueCtx.ambr = msg.ambr;
        }

        updateAmbrForUe(ueCtx.ueId);
    }

    private void handleSessionCreate(PduSessionResource pduSession) {
        var ueId = pduSession.ueId;
        var ueCtx = ctx.ueMap.get(ueId);
        if (ueCtx == null) {
            Log.error(Tag.GTP, "GTP UE context not found.");
            return;
        }

        ctx.pduSessions.insert(pduSession);

        updateAmbrForUe(ueCtx.ueId);
        updateAmbrForSession(pduSession);
    }

    private void handleUplinkData(IwUplinkData msg) {
        var data = msg.ipData;

        if ((data.get(0) >> 4 & 0xF) != 4) {
            // ignore non IPv4 packets
            return;
        }
        var pduSession = ctx.pduSessions.findBySessionId(msg.ueId, msg.pduSessionId);
        if (pduSession == null) {
            Log.error(Tag.GTP, "TEID not found on GTP-U Uplink");
            return;
        }

        if (rateLimiter.allowUplinkPacket(pduSession, data.length)) {
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

            var gtpData = GtpEncoder.encode(gtp);
            var address = pduSession.upLayer.gTPTunnel.transportLayerAddress.value.toByteArray();

            try {
                ctx.socket.send(new DatagramPacket(gtpData, gtpData.length, InetAddress.getByAddress(address), 2152));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleDownlinkGtp(IwGtpDownlink msg) {
        var gtp = GtpDecoder.decode(msg.data);

        var pduSession = ctx.pduSessions.findByDownTeid(gtp.teid.longValue());
        if (pduSession == null) {
            Log.error(Tag.GTP, "TEID not found on GTP-U Downlink");
            return;
        }

        if (gtp.msgType.intValue() != GtpMessage.MT_G_PDU) {
            Log.error(Tag.NIMPL, "Unhandled GTP-U message type: " + gtp.msgType);
            return;
        }

        if (rateLimiter.allowDownlinkPacket(pduSession, gtp.payload.length)) {
            ctx.mrTask.push(new IwDownlinkData(pduSession.ueId, pduSession.pduSessionId, gtp.payload));
        }
    }

    private void updateAmbrForUe(UUID ueId) {
        var ueCtx = ctx.ueMap.get(ueId);
        if (ueCtx == null) {
            return;
        }

        long ueAmbrDownlink = -1;
        long ueAmbrUplink = -1;

        if (ueCtx.ambr != null) {
            if (ueCtx.ambr.uEAggregateMaximumBitRateDL != null) {
                ueAmbrDownlink = ueCtx.ambr.uEAggregateMaximumBitRateDL.value;
            }
            if (ueCtx.ambr.uEAggregateMaximumBitRateUL != null) {
                ueAmbrUplink = ueCtx.ambr.uEAggregateMaximumBitRateUL.value;
            }
        }

        rateLimiter.updateUeUplinkLimit(ueId, ueAmbrUplink);
        rateLimiter.updateUeDownlinkLimit(ueId, ueAmbrDownlink);
    }

    private void updateAmbrForSession(PduSessionResource session) {
        long ueAmbrDownlink = -1;
        long ueAmbrUplink = -1;

        var ambr = session.sessionAggregateMaximumBitRate;
        if (ambr != null) {
            if (ambr.pDUSessionAggregateMaximumBitRateDL != null) {
                ueAmbrDownlink = ambr.pDUSessionAggregateMaximumBitRateDL.value;
            }
            if (ambr.pDUSessionAggregateMaximumBitRateUL != null) {
                ueAmbrUplink = ambr.pDUSessionAggregateMaximumBitRateUL.value;
            }
        }

        rateLimiter.updateSessionUplinkLimit(session, ueAmbrUplink);
        rateLimiter.updateSessionDownlinkLimit(session, ueAmbrDownlink);
    }
}
