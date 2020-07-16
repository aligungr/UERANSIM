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

public class NGAP_CompletedCellsInTAI_EUTRA_Item extends NgapSequence {

    public NGAP_EUTRA_CGI eUTRA_CGI;

    @Override
    protected String getAsnName() {
        return "CompletedCellsInTAI-EUTRA-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInTAI-EUTRA-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"eUTRA-CGI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"eUTRA_CGI"};
    }
}
