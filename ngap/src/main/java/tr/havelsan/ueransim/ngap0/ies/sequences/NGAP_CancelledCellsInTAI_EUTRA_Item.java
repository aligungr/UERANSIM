package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_CancelledCellsInTAI_EUTRA_Item extends NGAP_Sequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;
    public NGAP_NumberOfBroadcasts numberOfBroadcasts;

    @Override
    public String getAsnName() {
        return "CancelledCellsInTAI-EUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInTAI-EUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI", "numberOfBroadcasts"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI", "numberOfBroadcasts"};
    }
}
