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

public class NGAP_ForbiddenAreaInformation extends NgapSequenceOf<NGAP_ForbiddenAreaInformation_Item> {

    public NGAP_ForbiddenAreaInformation() {
        super();
    }

    public NGAP_ForbiddenAreaInformation(List<NGAP_ForbiddenAreaInformation_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    public String getXmlTagName() {
        return "ForbiddenAreaInformation";
    }

    @Override
    public Class<NGAP_ForbiddenAreaInformation_Item> getItemType() {
        return NGAP_ForbiddenAreaInformation_Item.class;
    }
}
