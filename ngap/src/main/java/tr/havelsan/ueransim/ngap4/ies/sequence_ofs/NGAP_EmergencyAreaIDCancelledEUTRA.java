package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EmergencyAreaIDCancelledEUTRA_Item;

public class NGAP_EmergencyAreaIDCancelledEUTRA extends NgapSequenceOf<NGAP_EmergencyAreaIDCancelledEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDCancelledEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDCancelledEUTRA";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDCancelledEUTRA_Item> getItemType() {
        return NGAP_EmergencyAreaIDCancelledEUTRA_Item.class;
    }
}
