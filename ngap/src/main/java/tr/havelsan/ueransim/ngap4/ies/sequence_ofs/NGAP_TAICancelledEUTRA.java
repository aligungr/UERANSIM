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

public class NGAP_TAICancelledEUTRA extends NgapSequenceOf<NGAP_TAICancelledEUTRA_Item> {

    public NGAP_TAICancelledEUTRA() {
        super();
    }

    public NGAP_TAICancelledEUTRA(List<NGAP_TAICancelledEUTRA_Item> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TAICancelledEUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledEUTRA";
    }

    @Override
    public Class<NGAP_TAICancelledEUTRA_Item> getItemType() {
        return NGAP_TAICancelledEUTRA_Item.class;
    }
}
