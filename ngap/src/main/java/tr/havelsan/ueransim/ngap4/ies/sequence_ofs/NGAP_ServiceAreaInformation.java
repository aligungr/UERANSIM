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

public class NGAP_ServiceAreaInformation extends NGAP_SequenceOf<NGAP_ServiceAreaInformation_Item> {

    public NGAP_ServiceAreaInformation() {
        super();
    }

    public NGAP_ServiceAreaInformation(List<NGAP_ServiceAreaInformation_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ServiceAreaInformation";
    }

    @Override
    public String getXmlTagName() {
        return "ServiceAreaInformation";
    }

    @Override
    public Class<NGAP_ServiceAreaInformation_Item> getItemType() {
        return NGAP_ServiceAreaInformation_Item.class;
    }
}
