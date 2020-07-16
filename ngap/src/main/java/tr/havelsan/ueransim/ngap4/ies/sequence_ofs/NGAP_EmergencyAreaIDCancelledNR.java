package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EmergencyAreaIDCancelledNR_Item;

public class NGAP_EmergencyAreaIDCancelledNR extends NgapSequenceOf<NGAP_EmergencyAreaIDCancelledNR_Item> {

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDCancelledNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDCancelledNR_Item.class;
    }
}
