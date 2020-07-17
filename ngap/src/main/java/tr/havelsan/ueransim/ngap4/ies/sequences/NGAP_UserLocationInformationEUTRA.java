package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

import java.util.List;

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
