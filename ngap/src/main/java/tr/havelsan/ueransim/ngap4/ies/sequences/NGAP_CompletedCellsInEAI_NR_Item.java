package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_CompletedCellsInEAI_NR_Item extends NgapSequence {

    public NGAP_NR_CGI nR_CGI;

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-NR-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-NR-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nR-CGI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI"};
    }
}
