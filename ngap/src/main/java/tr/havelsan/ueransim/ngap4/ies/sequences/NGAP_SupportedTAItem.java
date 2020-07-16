package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_TAC;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_BroadcastPLMNList;

public class NGAP_SupportedTAItem extends NgapSequence {

    public NGAP_TAC tAC;
    public NGAP_BroadcastPLMNList broadcastPLMNList;

    @Override
    protected String getAsnName() {
        return "SupportedTAItem";
    }

    @Override
    protected String getXmlTagName() {
        return "SupportedTAItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }
}
