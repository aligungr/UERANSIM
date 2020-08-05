package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

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
