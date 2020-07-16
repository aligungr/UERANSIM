package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

public class NGAP_UserLocationInformationNR extends NgapSequence {

    public NGAP_NR_CGI nR_CGI;
    public NGAP_TAI tAI;
    public NGAP_TimeStamp timeStamp;

    @Override
    protected String getAsnName() {
        return "UserLocationInformationNR";
    }

    @Override
    protected String getXmlTagName() {
        return "UserLocationInformationNR";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nR-CGI", "tAI", "timeStamp"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nR_CGI", "tAI", "timeStamp"};
    }
}
