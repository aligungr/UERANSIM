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

public class NGAP_EmergencyAreaIDCancelledEUTRA extends NgapSequenceOf<NGAP_EmergencyAreaIDCancelledEUTRA_Item> {

    public NGAP_EmergencyAreaIDCancelledEUTRA() {
        super();
    }

    public NGAP_EmergencyAreaIDCancelledEUTRA(List<NGAP_EmergencyAreaIDCancelledEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDCancelledEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDCancelledEUTRA";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDCancelledEUTRA_Item> getItemType() {
        return NGAP_EmergencyAreaIDCancelledEUTRA_Item.class;
    }
}
