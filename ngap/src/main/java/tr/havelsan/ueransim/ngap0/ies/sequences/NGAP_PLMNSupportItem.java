package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_PLMNSupportItem extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList sliceSupportList;

    @Override
    public String getAsnName() {
        return "PLMNSupportItem";
    }

    @Override
    public String getXmlTagName() {
        return "PLMNSupportItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "sliceSupportList"};
    }
}
