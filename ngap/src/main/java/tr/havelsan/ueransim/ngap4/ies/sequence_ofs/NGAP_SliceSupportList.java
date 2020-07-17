package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_SliceSupportList extends NGAP_SequenceOf<NGAP_SliceSupportItem> {

    public NGAP_SliceSupportList() {
        super();
    }

    public NGAP_SliceSupportList(List<NGAP_SliceSupportItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SliceSupportList";
    }

    @Override
    public String getXmlTagName() {
        return "SliceSupportList";
    }

    @Override
    public Class<NGAP_SliceSupportItem> getItemType() {
        return NGAP_SliceSupportItem.class;
    }
}
