package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceReleasedItemPSFail extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pathSwitchRequestUnsuccessfulTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedItemPSFail";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedItemPSFail";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }
}
