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

public class NGAP_UEPagingIdentity extends NGAP_Choice {

    public NGAP_FiveG_S_TMSI fiveG_S_TMSI;

    @Override
    public String getAsnName() {
        return "UEPagingIdentity";
    }

    @Override
    public String getXmlTagName() {
        return "UEPagingIdentity";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"fiveG-S-TMSI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"fiveG_S_TMSI"};
    }
}
