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

public class NGAP_TAIListForWarning extends NgapSequenceOf<NGAP_TAI> {

    public NGAP_TAIListForWarning() {
        super();
    }

    public NGAP_TAIListForWarning(List<NGAP_TAI> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TAIListForWarning";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForWarning";
    }

    @Override
    public Class<NGAP_TAI> getItemType() {
        return NGAP_TAI.class;
    }
}
