package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_BroadcastPLMNItem extends NGAP_Sequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_SliceSupportList tAISliceSupportList;

    @Override
    public String getAsnName() {
        return "BroadcastPLMNItem";
    }

    @Override
    public String getXmlTagName() {
        return "BroadcastPLMNItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "tAISliceSupportList"};
    }
}
