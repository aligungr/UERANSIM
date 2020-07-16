package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_CompletedCellsInEAI_EUTRA_Item extends NgapSequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-EUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-EUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI"};
    }
}
