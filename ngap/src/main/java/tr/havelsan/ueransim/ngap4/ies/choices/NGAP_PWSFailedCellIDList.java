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

public class NGAP_PWSFailedCellIDList extends NgapChoice {

    public NGAP_EUTRA_CGIList eUTRA_CGI_PWSFailedList;
    public NGAP_NR_CGIList nR_CGI_PWSFailedList;

    @Override
    protected String getAsnName() {
        return "PWSFailedCellIDList";
    }

    @Override
    protected String getXmlTagName() {
        return "PWSFailedCellIDList";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI-PWSFailedList", "nR-CGI-PWSFailedList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI_PWSFailedList", "nR_CGI_PWSFailedList"};
    }
}
