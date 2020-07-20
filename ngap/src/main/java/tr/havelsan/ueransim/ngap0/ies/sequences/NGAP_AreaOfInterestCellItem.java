package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_AreaOfInterestCellItem extends NGAP_Sequence {

    public NGAP_NGRAN_CGI nGRAN_CGI;

    @Override
    public String getAsnName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestCellItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRAN-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRAN_CGI"};
    }
}
