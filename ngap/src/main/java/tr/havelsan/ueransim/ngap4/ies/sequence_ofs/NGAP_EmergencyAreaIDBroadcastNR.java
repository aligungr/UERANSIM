package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_EmergencyAreaIDBroadcastNR_Item;

public class NGAP_EmergencyAreaIDBroadcastNR extends NgapSequenceOf<NGAP_EmergencyAreaIDBroadcastNR_Item> {

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
