package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_SliceSupportList;

public class NGAP_BroadcastPLMNItem extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList tAISliceSupportList;

    @Override
    protected String getAsnName() {
        return "BroadcastPLMNItem";
    }

    @Override
    protected String getXmlTagName() {
        return "BroadcastPLMNItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }
}
