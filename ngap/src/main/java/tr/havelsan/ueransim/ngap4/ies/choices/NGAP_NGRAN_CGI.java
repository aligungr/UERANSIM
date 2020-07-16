package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EUTRA_CGI;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_NR_CGI;

public class NGAP_NGRAN_CGI extends NgapChoice {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    protected String getAsnName() {
        return "NGRAN-CGI";
    }

    @Override
    protected String getXmlTagName() {
        return "NGRAN-CGI";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nR-CGI", "eUTRA-CGI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "eUTRA_CGI"};
    }
}
