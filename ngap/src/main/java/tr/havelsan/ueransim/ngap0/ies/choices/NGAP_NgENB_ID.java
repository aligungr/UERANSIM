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

public class NGAP_NgENB_ID extends NGAP_Choice {

    public NGAP_BitString macroNgENB_ID;
    public NGAP_BitString shortMacroNgENB_ID;
    public NGAP_BitString longMacroNgENB_ID;

    @Override
    public String getAsnName() {
        return "NgENB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "NgENB-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"macroNgENB-ID", "shortMacroNgENB-ID", "longMacroNgENB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"macroNgENB_ID", "shortMacroNgENB_ID", "longMacroNgENB_ID"};
    }
}
