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
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
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

// TODO: this class is curently POC.
public class GtpTask extends ItmsTask {

    private final GnbSimContext ctx;
    private GtpUContext gtpCtx;
    private PduSessionResource pduSession;
    private DatagramSocket socket;

    public GtpTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        this.gtpCtx = ctx.gtpUCtx = new GtpUContext();
        try {
            this.socket = new DatagramSocket(ctx.config.gtpPort, InetAddress.getByName(ctx.config.host));
        } catch (Exception e) {
            Log.error(Tag.CONNECTION, "Failed to bind GTP/UDP socket %s:%s (%s)", ctx.config.gtpPort, ctx.config.host, e.toString());
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
                itms.sendMessage(ItmsId.GNB_TASK_GTP, new IwGtpDownlink(new OctetString(datagram.getData(), datagram.getOffset(), datagram.getLength()), datagram.getAddress(), datagram.getPort()));
            }
        });

        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(thread));

        receiverThread.start();

        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof IwUplinkData) {
                handleUplinkData((IwUplinkData) msg);
            } else if (msg instanceof IwPduSessionResourceCreate) {
                this.pduSession = ((IwPduSessionResourceCreate) msg).pduSessionResource;
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

        if (pduSession == null)
            return;

        var gtp = new GtpMessage();
        gtp.payload = data;
        gtp.msgType = new Octet(GtpMessage.MT_G_PDU);
        gtp.teid = pduSession.upLayer.gTPTunnel.gTP_TEID.value.get4(0);
        gtp.extHeaders = new ArrayList<>();

        var ul = new UlPduSessionInformation();
        ul.qfi = new Bit6(1);

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
        if (gtp.msgType.intValue() != GtpMessage.MT_G_PDU) {
            Log.warning(Tag.NOT_IMPL_YET, "Unhandled GTP-U message type: " + gtp.msgType);
            return;
        }

        var ipPacket = gtp.payload;
        itms.sendMessage(ItmsId.GNB_TASK_TUN, new IwDownlinkData(ipPacket));
    }
}