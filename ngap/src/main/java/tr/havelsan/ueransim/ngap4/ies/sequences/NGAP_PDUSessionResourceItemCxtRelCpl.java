package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceItemCxtRelCpl extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceItemCxtRelCpl";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceItemCxtRelCpl";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID"};
    }
}
