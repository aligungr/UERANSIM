package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceReleasedItemPSFail extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedItemPSFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedItemPSFail";
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
