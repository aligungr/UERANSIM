package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.itms.IwDownlinkData;
import tr.havelsan.ueransim.app.common.itms.IwUplinkData;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TunTask extends ItmsTask {

    private final GnbSimContext ctx;
    private DatagramSocket bridge;
    private int bridgeEndpointAddr;
    private int bridgeEndpointPort;
    private InetAddress bridgeEndpointAddrBytes;
    private PacketMeter packetMeter;

    public TunTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
        this.packetMeter = new PacketMeter();
    }

    @Override
    public void main() {
        try {
            bridge = new DatagramSocket(ctx.config.tunPort, InetAddress.getByName(ctx.config.host));
        } catch (Exception e) {
            Log.error(Tag.CONNECTION, "TUN Bridge connection could not established: " + e.getMessage());
            return;
        }

        Log.info(Tag.TUN, "Listening TUN Bridge.");

        var receiverThread = new Thread(this::receiverThread);
        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(thread));
        receiverThread.start();

        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof IwDownlinkData) {
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

            var addr = datagram.getAddress().getAddress();
            if (addr.length != 4) {
                Log.error(Tag.TUN, "bad address");
                return;
            }

            int port = datagram.getPort();
            int address = (addr[0] << 24) | (addr[1] << 16) | (addr[2] << 8) | (addr[3]);

            if (bridgeEndpointPort == 0) {
                bridgeEndpointAddr = address;
                bridgeEndpointPort = port;
                bridgeEndpointAddrBytes = datagram.getAddress();
            } else {
                if (port != bridgeEndpointPort || address != bridgeEndpointAddr) {
                    Log.error(Tag.TUN, "inconsistent addresses in TunTask");
                    return;
                }
            }

            itms.sendMessage(ItmsId.GNB_TASK_MR, new IwUplinkData(new OctetString(datagram.getData(), datagram.getOffset(), datagram.getLength())));
        }
    }

    private void handleDownlinkData(IwDownlinkData msg) {
        if (bridgeEndpointAddrBytes == null) {
            Log.error(Tag.TUN, "'bridgeEndpointAddrBytes == null' in TunTask");
            return;
        }

        var data = msg.ipPacket.toByteArray();
        var pck = new DatagramPacket(data, data.length, bridgeEndpointAddrBytes, bridgeEndpointPort);

        try {
            bridge.send(pck);

            //packetMeter.notify(data.length);
            //System.err.println(packetMeter.speedMbsPerSec());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}