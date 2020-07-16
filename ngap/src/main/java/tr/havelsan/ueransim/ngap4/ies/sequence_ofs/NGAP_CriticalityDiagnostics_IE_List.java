package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

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

public class NGAP_CriticalityDiagnostics_IE_List extends NgapSequenceOf<NGAP_CriticalityDiagnostics_IE_Item> {

    @Override
    protected String getAsnName() {
        return "CriticalityDiagnostics-IE-List";
    }

    @Override
    protected String getXmlTagName() {
        return "CriticalityDiagnostics-IE-List";
    }

    @Override
    public Class<NGAP_CriticalityDiagnostics_IE_Item> getItemType() {
        return NGAP_CriticalityDiagnostics_IE_Item.class;
    }
}
