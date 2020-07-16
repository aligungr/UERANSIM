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

public class NGAP_E_RABInformationList extends NgapSequenceOf<NGAP_E_RABInformationItem> {

    public NGAP_E_RABInformationList() {
        super();
    }

    public NGAP_E_RABInformationList(List<NGAP_E_RABInformationItem> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "E-RABInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "E-RABInformationList";
    }

    @Override
    public Class<NGAP_E_RABInformationItem> getItemType() {
        return NGAP_E_RABInformationItem.class;
    }
}
