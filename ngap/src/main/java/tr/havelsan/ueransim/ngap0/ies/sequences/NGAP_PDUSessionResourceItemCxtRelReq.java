package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceItemCxtRelReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceItemCxtRelReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceItemCxtRelReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID"};
    }
}
