package tr.havelsan.ueransim.ngap4.ies.choices;

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

public class NGAP_BroadcastCompletedAreaList extends NgapChoice {

    public NGAP_CellIDBroadcastEUTRA cellIDBroadcastEUTRA;
    public NGAP_TAIBroadcastEUTRA tAIBroadcastEUTRA;
    public NGAP_EmergencyAreaIDBroadcastEUTRA emergencyAreaIDBroadcastEUTRA;
    public NGAP_CellIDBroadcastNR cellIDBroadcastNR;
    public NGAP_TAIBroadcastNR tAIBroadcastNR;
    public NGAP_EmergencyAreaIDBroadcastNR emergencyAreaIDBroadcastNR;

    @Override
    public String getAsnName() {
        return "BroadcastCompletedAreaList";
    }

    @Override
    public String getXmlTagName() {
        return "BroadcastCompletedAreaList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"cellIDBroadcastEUTRA", "tAIBroadcastEUTRA", "emergencyAreaIDBroadcastEUTRA", "cellIDBroadcastNR", "tAIBroadcastNR", "emergencyAreaIDBroadcastNR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"cellIDBroadcastEUTRA", "tAIBroadcastEUTRA", "emergencyAreaIDBroadcastEUTRA", "cellIDBroadcastNR", "tAIBroadcastNR", "emergencyAreaIDBroadcastNR"};
    }
}
