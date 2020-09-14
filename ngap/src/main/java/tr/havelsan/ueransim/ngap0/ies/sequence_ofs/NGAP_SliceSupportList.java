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
