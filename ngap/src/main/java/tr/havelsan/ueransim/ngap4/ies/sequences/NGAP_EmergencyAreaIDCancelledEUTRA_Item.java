package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_EmergencyAreaIDCancelledEUTRA_Item extends NgapSequence {

    public NGAP_EmergencyAreaID emergencyAreaID;
    public NGAP_CancelledCellsInEAI_EUTRA cancelledCellsInEAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "EmergencyAreaIDCancelledEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyAreaIDCancelledEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"emergencyAreaID", "cancelledCellsInEAI_EUTRA"};
    }
}
