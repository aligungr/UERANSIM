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

public class NGAP_ServiceAreaInformation extends NgapSequenceOf<NGAP_ServiceAreaInformation_Item> {

    @Override
    protected String getAsnName() {
        return "ServiceAreaInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "ServiceAreaInformation";
    }

    @Override
    public Class<NGAP_ServiceAreaInformation_Item> getItemType() {
        return NGAP_ServiceAreaInformation_Item.class;
    }
}
