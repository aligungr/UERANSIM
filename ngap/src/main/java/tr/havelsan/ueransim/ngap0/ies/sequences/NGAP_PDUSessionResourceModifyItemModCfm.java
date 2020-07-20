package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceModifyItemModCfm extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceModifyConfirmTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyItemModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyItemModCfm";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyConfirmTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyConfirmTransfer"};
    }
}
