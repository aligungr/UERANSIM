package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;

public class NGAP_EmergencyAreaIDList extends NgapSequenceOf<NGAP_EmergencyAreaID> {

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDList";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDList";
    }

    @Override
    public Class<NGAP_EmergencyAreaID> getItemType() {
        return NGAP_EmergencyAreaID.class;
    }
}
