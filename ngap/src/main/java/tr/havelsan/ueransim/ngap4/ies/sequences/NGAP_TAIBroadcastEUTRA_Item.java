package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_TAIBroadcastEUTRA_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CompletedCellsInTAI_EUTRA completedCellsInTAI_EUTRA;

    @Override
    public String getAsnName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAIBroadcastEUTRA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "completedCellsInTAI-EUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "completedCellsInTAI_EUTRA"};
    }
}
