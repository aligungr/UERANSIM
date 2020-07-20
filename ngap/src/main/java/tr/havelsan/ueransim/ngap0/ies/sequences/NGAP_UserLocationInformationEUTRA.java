package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_UserLocationInformationEUTRA extends NGAP_Sequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;
    public NGAP_TAI tAI;
    public NGAP_TimeStamp timeStamp;

    @Override
    public String getAsnName() {
        return "UserLocationInformationEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformationEUTRA";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI", "tAI", "timeStamp"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI", "tAI", "timeStamp"};
    }
}
