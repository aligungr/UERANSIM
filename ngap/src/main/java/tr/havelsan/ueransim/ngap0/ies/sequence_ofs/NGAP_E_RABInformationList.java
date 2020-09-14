package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_E_RABInformationList extends NGAP_SequenceOf<NGAP_E_RABInformationItem> {

    public NGAP_E_RABInformationList() {
        super();
    }

    public NGAP_E_RABInformationList(List<NGAP_E_RABInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "E-RABInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "E-RABInformationList";
    }

    @Override
    public Class<NGAP_E_RABInformationItem> getItemType() {
        return NGAP_E_RABInformationItem.class;
    }
}
