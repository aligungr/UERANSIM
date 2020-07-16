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

public class NGAP_TAIListForInactive extends NgapSequenceOf<NGAP_TAIListForInactiveItem> {

    public NGAP_TAIListForInactive() {
        super();
    }

    public NGAP_TAIListForInactive(List<NGAP_TAIListForInactiveItem> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TAIListForInactive";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForInactive";
    }

    @Override
    public Class<NGAP_TAIListForInactiveItem> getItemType() {
        return NGAP_TAIListForInactiveItem.class;
    }
}
