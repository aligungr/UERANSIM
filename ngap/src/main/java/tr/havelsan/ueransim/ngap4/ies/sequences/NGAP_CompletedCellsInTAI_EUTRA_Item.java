package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_CompletedCellsInTAI_EUTRA_Item extends NgapSequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    protected String getAsnName() {
        return "CompletedCellsInTAI-EUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInTAI-EUTRA-Item";
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
