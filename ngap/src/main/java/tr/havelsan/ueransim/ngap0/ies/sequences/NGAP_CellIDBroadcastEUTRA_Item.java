package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_CellIDBroadcastEUTRA_Item extends NGAP_Sequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    public String getAsnName() {
        return "CellIDBroadcastEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI"};
    }
}
