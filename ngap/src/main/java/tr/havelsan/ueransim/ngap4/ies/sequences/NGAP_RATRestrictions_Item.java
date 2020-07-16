package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_RATRestrictionInformation;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_RATRestrictions_Item extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_RATRestrictionInformation rATRestrictionInformation;

    @Override
    protected String getAsnName() {
        return "RATRestrictions-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "RATRestrictions-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "rATRestrictionInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "rATRestrictionInformation"};
    }
}
