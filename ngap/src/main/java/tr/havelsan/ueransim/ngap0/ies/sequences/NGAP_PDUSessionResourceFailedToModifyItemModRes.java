package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceFailedToModifyItemModRes extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceModifyUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyItemModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyItemModRes";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyUnsuccessfulTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyUnsuccessfulTransfer"};
    }
}
