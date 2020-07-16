package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_NGRAN_CGI;

public class NGAP_RecommendedCellItem extends NgapSequence {

    public NGAP_NGRAN_CGI nGRAN_CGI;
    public long timeStayedInCell;

    @Override
    protected String getAsnName() {
        return "RecommendedCellItem";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedCellItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nGRAN-CGI", "timeStayedInCell"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nGRAN_CGI", "timeStayedInCell"};
    }
}
