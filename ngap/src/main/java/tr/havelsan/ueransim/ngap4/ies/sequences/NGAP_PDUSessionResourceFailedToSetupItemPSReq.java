package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceFailedToSetupItemPSReq extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pathSwitchRequestSetupFailedTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupItemPSReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupItemPSReq";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestSetupFailedTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestSetupFailedTransfer"};
    }
}
