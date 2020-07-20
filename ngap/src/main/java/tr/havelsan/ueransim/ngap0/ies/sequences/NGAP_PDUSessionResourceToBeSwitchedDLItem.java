package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceToBeSwitchedDLItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToBeSwitchedDLItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToBeSwitchedDLItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestTransfer"};
    }
}
