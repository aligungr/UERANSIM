package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBsToQosFlowsMappingItem;

public class NGAP_DRBsToQosFlowsMappingList extends NgapSequenceOf<NGAP_DRBsToQosFlowsMappingItem> {

    @Override
    protected String getAsnName() {
        return "DRBsToQosFlowsMappingList";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBsToQosFlowsMappingList";
    }

    @Override
    public Class<NGAP_DRBsToQosFlowsMappingItem> getItemType() {
        return NGAP_DRBsToQosFlowsMappingItem.class;
    }
}
