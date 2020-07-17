package tr.havelsan.ueransim.ngap4.ies.choices;

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

public class NGAP_PWSFailedCellIDList extends NGAP_Choice {

    public NGAP_EUTRA_CGIList eUTRA_CGI_PWSFailedList;
    public NGAP_NR_CGIList nR_CGI_PWSFailedList;

    @Override
    public String getAsnName() {
        return "PWSFailedCellIDList";
    }

    @Override
    public String getXmlTagName() {
        return "PWSFailedCellIDList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"eUTRA-CGI-PWSFailedList", "nR-CGI-PWSFailedList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI_PWSFailedList", "nR_CGI_PWSFailedList"};
    }
}
