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

public class NGAP_TAICancelledEUTRA_Item extends NgapSequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_EUTRA cancelledCellsInTAI_EUTRA;

    @Override
    protected String getAsnName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledEUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-EUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_EUTRA"};
    }
}
