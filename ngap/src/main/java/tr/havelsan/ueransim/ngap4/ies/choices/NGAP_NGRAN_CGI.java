package tr.havelsan.ueransim.ngap4.ies.choices;

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

import java.util.List;

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
