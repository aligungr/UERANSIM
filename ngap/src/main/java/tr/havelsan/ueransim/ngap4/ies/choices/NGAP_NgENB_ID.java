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

public class NGAP_NgENB_ID extends NgapChoice {

    public NgapBitString macroNgENB_ID;
    public NgapBitString shortMacroNgENB_ID;
    public NgapBitString longMacroNgENB_ID;

    @Override
    protected String getAsnName() {
        return "NgENB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "NgENB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"macroNgENB-ID", "shortMacroNgENB-ID", "longMacroNgENB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"macroNgENB_ID", "shortMacroNgENB_ID", "longMacroNgENB_ID"};
    }
}
