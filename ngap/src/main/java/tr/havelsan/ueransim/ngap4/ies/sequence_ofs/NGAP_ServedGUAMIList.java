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

public class NGAP_ServedGUAMIList extends NgapSequenceOf<NGAP_ServedGUAMIItem> {

    @Override
    protected String getAsnName() {
        return "ServedGUAMIList";
    }

    @Override
    protected String getXmlTagName() {
        return "ServedGUAMIList";
    }

    @Override
    public Class<NGAP_ServedGUAMIItem> getItemType() {
        return NGAP_ServedGUAMIItem.class;
    }
}
