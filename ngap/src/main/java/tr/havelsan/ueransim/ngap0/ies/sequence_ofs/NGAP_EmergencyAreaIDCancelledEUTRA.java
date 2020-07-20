package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_EmergencyAreaIDCancelledEUTRA extends NGAP_SequenceOf<NGAP_EmergencyAreaIDCancelledEUTRA_Item> {

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
