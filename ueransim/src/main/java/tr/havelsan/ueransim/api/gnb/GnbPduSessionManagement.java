package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.ngap0.NgapDataUnitType;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RANPagingPriority;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_PDUSessionResourceSetupListSUReq;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceSetupRequestTransfer;
import tr.havelsan.ueransim.ngap0.msg.NGAP_PDUSessionResourceSetupRequest;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;

public class GnbPduSessionManagement {

    // todo
    public static void receiveResourceSetupRequest(GnbSimContext ctx, NGAP_PDUSessionResourceSetupRequest message) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling PDU Session Resource Setup Request");

        var associatedUe = GnbUeManagement.findAssociatedUeIdDefault(ctx, message);

        var pagingPriority = message.getProtocolIe(NGAP_RANPagingPriority.class);
        var nasPdu = message.getProtocolIe(NGAP_NAS_PDU.class);

        var list = message.getProtocolIe(NGAP_PDUSessionResourceSetupListSUReq.class);

        for (var item : list.list) {
            var transfer = (NGAP_PDUSessionResourceSetupRequestTransfer)
                    NgapEncoding.decodeAper(item.pDUSessionResourceSetupRequestTransfer.value,
                            NgapDataUnitType.PDUSessionResourceSetupRequestTransfer);
        }

        Logging.funcOut();
    }
}
