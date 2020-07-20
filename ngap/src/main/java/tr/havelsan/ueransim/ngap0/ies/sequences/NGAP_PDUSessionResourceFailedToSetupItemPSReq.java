package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceFailedToSetupItemPSReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestSetupFailedTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupItemPSReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupItemPSReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestSetupFailedTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestSetupFailedTransfer"};
    }
}
