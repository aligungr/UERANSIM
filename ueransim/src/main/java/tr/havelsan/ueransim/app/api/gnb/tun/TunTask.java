package tr.havelsan.ueransim.app.api.gnb.tun;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsId;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkDataWrapper;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TunTask extends ItmsTask {

    private final GnbSimContext ctx;

    public TunTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        DatagramSocket bridge;

        try {
            bridge = new DatagramSocket(ctx.config.tunPort, InetAddress.getByName(ctx.config.host));
        } catch (Exception e) {
            Log.error(Tag.CONNECTION, "TUN Bridge connection could not established: " + e.getMessage());
            return;
        }

        Log.info(Tag.CONNECTION, "Listening TUN Bridge.");

        var receiverThread = new Thread(() -> {
            var buffer = new byte[65535];

            var datagram = new DatagramPacket(buffer, buffer.length);

            while (true) {
                try {
                    bridge.receive(datagram);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                itms.sendMessage(ItmsId.GNB_TASK_MR, new UplinkDataWrapper(new OctetString(datagram.getData(), 0, datagram.getLength())));
            }
        });

        Log.registerLogger(receiverThread, Log.getLoggerOrDefault(thread));

        receiverThread.start();
    }
}