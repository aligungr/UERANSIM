package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceToReleaseItemHOCmd extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString handoverPreparationUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseItemHOCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseItemHOCmd";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverPreparationUnsuccessfulTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverPreparationUnsuccessfulTransfer"};
    }
}
