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

public class NGAP_EUTRA_CGIList extends NgapSequenceOf<NGAP_EUTRA_CGI> {

    @Override
    protected String getAsnName() {
        return "EUTRA-CGIList";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRA-CGIList";
    }

    @Override
    public Class<NGAP_EUTRA_CGI> getItemType() {
        return NGAP_EUTRA_CGI.class;
    }
}
