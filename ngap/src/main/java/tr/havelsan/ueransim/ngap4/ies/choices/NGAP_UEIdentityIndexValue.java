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

public class NGAP_UEIdentityIndexValue extends NGAP_Choice {

    public NGAP_BitString indexLength10;

    @Override
    public String getAsnName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String getXmlTagName() {
        return "UEIdentityIndexValue";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"indexLength10"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"indexLength10"};
    }
}
