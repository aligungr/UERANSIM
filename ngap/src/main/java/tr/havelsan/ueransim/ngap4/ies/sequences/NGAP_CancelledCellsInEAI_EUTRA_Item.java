package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_NumberOfBroadcasts;

public class NGAP_CancelledCellsInEAI_EUTRA_Item extends NgapSequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;
    public NGAP_NumberOfBroadcasts numberOfBroadcasts;

    @Override
    protected String getAsnName() {
        return "CancelledCellsInEAI-EUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CancelledCellsInEAI-EUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI", "numberOfBroadcasts"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI", "numberOfBroadcasts"};
    }
}
