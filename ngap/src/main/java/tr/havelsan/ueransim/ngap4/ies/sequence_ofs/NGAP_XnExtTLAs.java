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

public class NGAP_XnExtTLAs extends NgapSequenceOf<NGAP_XnExtTLA_Item> {

    public NGAP_XnExtTLAs() {
        super();
    }

    public NGAP_XnExtTLAs(List<NGAP_XnExtTLA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "XnExtTLAs";
    }

    @Override
    public String getXmlTagName() {
        return "XnExtTLAs";
    }

    @Override
    public Class<NGAP_XnExtTLA_Item> getItemType() {
        return NGAP_XnExtTLA_Item.class;
    }
}
