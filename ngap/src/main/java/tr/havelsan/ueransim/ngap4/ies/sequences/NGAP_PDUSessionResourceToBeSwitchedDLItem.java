package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceToBeSwitchedDLItem extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pathSwitchRequestTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToBeSwitchedDLItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToBeSwitchedDLItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestTransfer"};
    }
}
