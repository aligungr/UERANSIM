package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_EmergencyAreaIDCancelledNR extends NGAP_SequenceOf<NGAP_EmergencyAreaIDCancelledNR_Item> {

    public NGAP_EmergencyAreaIDCancelledNR() {
        super();
    }

    public NGAP_EmergencyAreaIDCancelledNR(List<NGAP_EmergencyAreaIDCancelledNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDCancelledNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDCancelledNR_Item.class;
    }
}
