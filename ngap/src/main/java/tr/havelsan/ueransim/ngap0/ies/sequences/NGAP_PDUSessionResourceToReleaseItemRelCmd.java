package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceToReleaseItemRelCmd extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceReleaseCommandTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseItemRelCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseItemRelCmd";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseCommandTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseCommandTransfer"};
    }
}
