package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_RATRestrictions_Item extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_RATRestrictionInformation rATRestrictionInformation;

    @Override
    public String getAsnName() {
        return "RATRestrictions-Item";
    }

    @Override
    public String getXmlTagName() {
        return "RATRestrictions-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "rATRestrictionInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "rATRestrictionInformation"};
    }
}
