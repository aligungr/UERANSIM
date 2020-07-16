package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_TAIListForPagingItem extends NgapSequence {

    public NGAP_TAI tAI;

    @Override
    protected String getAsnName() {
        return "TAIListForPagingItem";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForPagingItem";
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
