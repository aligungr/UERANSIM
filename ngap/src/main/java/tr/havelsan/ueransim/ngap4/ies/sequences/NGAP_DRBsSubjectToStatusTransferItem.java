package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_DRBStatusDL;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_DRBStatusUL;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_DRB_ID;

public class NGAP_DRBsSubjectToStatusTransferItem extends NgapSequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_DRBStatusUL dRBStatusUL;
    public NGAP_DRBStatusDL dRBStatusDL;

    @Override
    protected String getAsnName() {
        return "DRBsSubjectToStatusTransferItem";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBsSubjectToStatusTransferItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRB-ID", "dRBStatusUL", "dRBStatusDL"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "dRBStatusUL", "dRBStatusDL"};
    }
}
