package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceHandoverItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString handoverCommandTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceHandoverItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceHandoverItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverCommandTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverCommandTransfer"};
    }
}
