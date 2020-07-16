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

public class NGAP_TAICancelledNR extends NgapSequenceOf<NGAP_TAICancelledNR_Item> {

    public NGAP_TAICancelledNR() {
        super();
    }

    public NGAP_TAICancelledNR(List<NGAP_TAICancelledNR_Item> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TAICancelledNR";
    }

    @Override
    protected String getXmlTagName() {
        return "TAICancelledNR";
    }

    @Override
    public Class<NGAP_TAICancelledNR_Item> getItemType() {
        return NGAP_TAICancelledNR_Item.class;
    }
}
