package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_DRBsSubjectToStatusTransferItem extends NGAP_Sequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_DRBStatusUL dRBStatusUL;
    public NGAP_DRBStatusDL dRBStatusDL;

    @Override
    public String getAsnName() {
        return "DRBsSubjectToStatusTransferItem";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsSubjectToStatusTransferItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRB-ID", "dRBStatusUL", "dRBStatusDL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "dRBStatusUL", "dRBStatusDL"};
    }
}
