package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_EmergencyAreaIDListForRestart extends NGAP_SequenceOf<NGAP_EmergencyAreaID> {

    public NGAP_EmergencyAreaIDListForRestart() {
        super();
    }

    public NGAP_EmergencyAreaIDListForRestart(List<NGAP_EmergencyAreaID> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDListForRestart";
    }

    @Override
    public Class<NGAP_EmergencyAreaID> getItemType() {
        return NGAP_EmergencyAreaID.class;
    }
}
