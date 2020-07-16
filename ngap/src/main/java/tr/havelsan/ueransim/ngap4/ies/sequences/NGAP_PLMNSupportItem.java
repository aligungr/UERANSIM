package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_SliceSupportList;

public class NGAP_PLMNSupportItem extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList sliceSupportList;

    @Override
    protected String getAsnName() {
        return "PLMNSupportItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PLMNSupportItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }
}
