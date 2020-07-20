package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceReleasedItemRelRes extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceReleaseResponseTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedItemRelRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedItemRelRes";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseResponseTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseResponseTransfer"};
    }
}
