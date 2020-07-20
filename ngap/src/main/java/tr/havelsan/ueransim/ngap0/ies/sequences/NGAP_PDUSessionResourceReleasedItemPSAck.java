package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceReleasedItemPSAck extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedItemPSAck";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedItemPSAck";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }
}
