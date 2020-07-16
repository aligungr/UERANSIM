package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_EmergencyAreaID;

public class NGAP_EmergencyAreaIDListForRestart extends NgapSequenceOf<NGAP_EmergencyAreaID> {

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    public Class<NGAP_EmergencyAreaID> getItemType() {
        return NGAP_EmergencyAreaID.class;
    }
}
