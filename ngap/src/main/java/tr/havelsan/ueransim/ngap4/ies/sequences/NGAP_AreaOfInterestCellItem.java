package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_NGRAN_CGI;

public class NGAP_AreaOfInterestCellItem extends NgapSequence {

    public NGAP_NGRAN_CGI nGRAN_CGI;

    @Override
    protected String getAsnName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nGRAN-CGI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nGRAN_CGI"};
    }
}
