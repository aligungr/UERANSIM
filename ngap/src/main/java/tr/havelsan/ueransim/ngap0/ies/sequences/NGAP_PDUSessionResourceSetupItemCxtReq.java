package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceSetupItemCxtReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_NAS_PDU nAS_PDU;
    public NGAP_S_NSSAI s_NSSAI;
    public NGAP_OctetString pDUSessionResourceSetupRequestTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupItemCxtReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupItemCxtReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "nAS-PDU", "s-NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "nAS_PDU", "s_NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }
}
