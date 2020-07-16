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

import java.util.List;

public class NGAP_EmergencyAreaIDBroadcastNR extends NgapSequenceOf<NGAP_EmergencyAreaIDBroadcastNR_Item> {

    public NGAP_EmergencyAreaIDBroadcastNR() {
        super();
    }

    public NGAP_EmergencyAreaIDBroadcastNR(List<NGAP_EmergencyAreaIDBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDBroadcastNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDBroadcastNR_Item.class;
    }
}
