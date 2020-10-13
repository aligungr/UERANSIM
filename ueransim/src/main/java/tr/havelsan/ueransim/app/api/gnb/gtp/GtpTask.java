package tr.havelsan.ueransim.app.api.gnb.gtp;

import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.itms.wrappers.PduSessionResourceCreateWrapper;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkDataWrapper;
import tr.havelsan.ueransim.app.structs.GtpUContext;
import tr.havelsan.ueransim.app.structs.PduSessionResource;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;
import tr.havelsan.ueransim.gtp.GtpMessage;
import tr.havelsan.ueransim.gtp.ext.PduSessionContainerExtHeader;
import tr.havelsan.ueransim.gtp.pdusup.UlPduSessionInformation;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;

import java.util.ArrayList;

// TODO: this class is curently POC.
public class GtpTask extends ItmsTask {

    private final GnbSimContext ctx;
    private GtpUContext gtpCtx;
    private PduSessionResource pduSession;

    public GtpTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        this.gtpCtx = ctx.gtpUCtx = new GtpUContext();

        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof UplinkDataWrapper) {
                var data = ((UplinkDataWrapper) msg).ipData;
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

            } else if (msg instanceof PduSessionResourceCreateWrapper) {
                this.pduSession = ((PduSessionResourceCreateWrapper) msg).pduSessionResource;
            }
        }
    }

    private void sendGtp(GtpMessage msg, byte[] address) {

    }
}