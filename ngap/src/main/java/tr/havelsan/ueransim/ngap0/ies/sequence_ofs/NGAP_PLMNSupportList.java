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

public class NGAP_PLMNSupportList extends NGAP_SequenceOf<NGAP_PLMNSupportItem> {

    public NGAP_PLMNSupportList() {
        super();
    }

    public NGAP_PLMNSupportList(List<NGAP_PLMNSupportItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PLMNSupportList";
    }

    @Override
    public String getXmlTagName() {
        return "PLMNSupportList";
    }

    @Override
    public Class<NGAP_PLMNSupportItem> getItemType() {
        return NGAP_PLMNSupportItem.class;
    }
}
