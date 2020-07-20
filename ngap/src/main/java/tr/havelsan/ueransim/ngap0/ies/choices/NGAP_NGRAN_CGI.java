package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_NGRAN_CGI extends NGAP_Choice {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    public String getAsnName() {
        return "NGRAN-CGI";
    }

    @Override
    public String getXmlTagName() {
        return "NGRAN-CGI";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI", "eUTRA-CGI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "eUTRA_CGI"};
    }
}
