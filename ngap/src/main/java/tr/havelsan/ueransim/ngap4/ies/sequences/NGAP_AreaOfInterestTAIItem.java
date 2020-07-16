package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_AreaOfInterestTAIItem extends NgapSequence {

    public NGAP_TAI tAI;

    @Override
    protected String getAsnName() {
        return "AreaOfInterestTAIItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestTAIItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI"};
    }
}
