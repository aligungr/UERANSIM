package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_OverloadResponse;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_TrafficLoadReductionIndication;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_SliceOverloadList;

public class NGAP_OverloadStartNSSAIItem extends NgapSequence {

    public NGAP_SliceOverloadList sliceOverloadList;
    public NGAP_OverloadResponse sliceOverloadResponse;
    public NGAP_TrafficLoadReductionIndication sliceTrafficLoadReductionIndication;

    @Override
    protected String getAsnName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    protected String getXmlTagName() {
        return "OverloadStartNSSAIItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"sliceOverloadList", "sliceOverloadResponse", "sliceTrafficLoadReductionIndication"};
    }
}
