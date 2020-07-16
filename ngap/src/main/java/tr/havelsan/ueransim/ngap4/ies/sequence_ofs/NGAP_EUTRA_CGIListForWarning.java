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

public class NGAP_EUTRA_CGIListForWarning extends NgapSequenceOf<NGAP_EUTRA_CGI> {

    public NGAP_EUTRA_CGIListForWarning() {
        super();
    }

    public NGAP_EUTRA_CGIListForWarning(List<NGAP_EUTRA_CGI> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRA-CGIListForWarning";
    }

    @Override
    public Class<NGAP_EUTRA_CGI> getItemType() {
        return NGAP_EUTRA_CGI.class;
    }
}
