package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceModifyItemModInd extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceModifyIndicationTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyItemModInd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyItemModInd";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyIndicationTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyIndicationTransfer"};
    }
}
