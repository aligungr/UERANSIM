package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_CellIDBroadcastNR_Item extends NGAP_Sequence {

    public NGAP_NR_CGI nR_CGI;

    @Override
    public String getAsnName() {
        return "CellIDBroadcastNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI"};
    }
}
