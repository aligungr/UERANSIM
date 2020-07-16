package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_DRB_ID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AssociatedQosFlowList;

public class NGAP_DRBsToQosFlowsMappingItem extends NgapSequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_AssociatedQosFlowList associatedQosFlowList;

    @Override
    protected String getAsnName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRB-ID", "associatedQosFlowList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "associatedQosFlowList"};
    }
}
