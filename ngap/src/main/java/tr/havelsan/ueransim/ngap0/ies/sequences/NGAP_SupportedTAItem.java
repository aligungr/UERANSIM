package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_SupportedTAItem extends NGAP_Sequence {

    public NGAP_TAC tAC;
    public NGAP_BroadcastPLMNList broadcastPLMNList;

    @Override
    public String getAsnName() {
        return "SupportedTAItem";
    }

    @Override
    public String getXmlTagName() {
        return "SupportedTAItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }
}
