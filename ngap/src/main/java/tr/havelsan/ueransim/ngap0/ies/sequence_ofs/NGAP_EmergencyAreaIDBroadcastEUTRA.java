package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_EmergencyAreaIDBroadcastEUTRA extends NGAP_SequenceOf<NGAP_EmergencyAreaIDBroadcastEUTRA_Item> {

    public NGAP_EmergencyAreaIDBroadcastEUTRA() {
        super();
    }

    public NGAP_EmergencyAreaIDBroadcastEUTRA(List<NGAP_EmergencyAreaIDBroadcastEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_EmergencyAreaIDBroadcastEUTRA_Item.class;
    }
}
