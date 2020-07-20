package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_UserLocationInformationNR extends NGAP_Sequence {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_TAI tAI;
    public NGAP_TimeStamp timeStamp;

    @Override
    public String getAsnName() {
        return "UserLocationInformationNR";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformationNR";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nR-CGI", "tAI", "timeStamp"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "tAI", "timeStamp"};
    }
}
