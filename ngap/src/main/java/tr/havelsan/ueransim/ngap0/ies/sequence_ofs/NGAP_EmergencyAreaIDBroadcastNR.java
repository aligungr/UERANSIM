package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_EmergencyAreaIDBroadcastNR extends NGAP_SequenceOf<NGAP_EmergencyAreaIDBroadcastNR_Item> {

    public NGAP_EmergencyAreaIDBroadcastNR() {
        super();
    }

    public NGAP_EmergencyAreaIDBroadcastNR(List<NGAP_EmergencyAreaIDBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDBroadcastNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDBroadcastNR_Item.class;
    }
}
