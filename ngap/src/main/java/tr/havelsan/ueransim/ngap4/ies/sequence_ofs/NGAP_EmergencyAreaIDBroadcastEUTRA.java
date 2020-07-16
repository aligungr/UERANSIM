package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EmergencyAreaIDBroadcastEUTRA_Item;

public class NGAP_EmergencyAreaIDBroadcastEUTRA extends NgapSequenceOf<NGAP_EmergencyAreaIDBroadcastEUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDBroadcastEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_EmergencyAreaIDBroadcastEUTRA_Item.class;
    }
}
