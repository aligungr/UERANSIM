package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_NumberOfBroadcasts;

public class NGAP_CancelledCellsInEAI_NR_Item extends NgapSequence {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_NumberOfBroadcasts numberOfBroadcasts;

    @Override
    protected String getAsnName() {
        return "CancelledCellsInEAI-NR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInEAI-NR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nR-CGI", "numberOfBroadcasts"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "numberOfBroadcasts"};
    }
}
