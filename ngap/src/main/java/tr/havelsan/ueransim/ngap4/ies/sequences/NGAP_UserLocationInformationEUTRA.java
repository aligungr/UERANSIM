package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_TimeStamp;

public class NGAP_UserLocationInformationEUTRA extends NgapSequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;
    public NGAP_TAI tAI;
    public NGAP_TimeStamp timeStamp;

    @Override
    protected String getAsnName() {
        return "UserLocationInformationEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "UserLocationInformationEUTRA";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI", "tAI", "timeStamp"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI", "tAI", "timeStamp"};
    }
}
