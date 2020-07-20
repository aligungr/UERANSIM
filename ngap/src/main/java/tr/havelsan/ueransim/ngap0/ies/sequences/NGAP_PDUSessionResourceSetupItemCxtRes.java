package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceSetupItemCxtRes extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceSetupResponseTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupItemCxtRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupItemCxtRes";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupResponseTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupResponseTransfer"};
    }
}
