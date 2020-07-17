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

public class NGAP_AllowedNSSAI extends NGAP_SequenceOf<NGAP_AllowedNSSAI_Item> {

    public NGAP_AllowedNSSAI() {
        super();
    }

    public NGAP_AllowedNSSAI(List<NGAP_AllowedNSSAI_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AllowedNSSAI";
    }

    @Override
    public String getXmlTagName() {
        return "AllowedNSSAI";
    }

    @Override
    public Class<NGAP_AllowedNSSAI_Item> getItemType() {
        return NGAP_AllowedNSSAI_Item.class;
    }
}
