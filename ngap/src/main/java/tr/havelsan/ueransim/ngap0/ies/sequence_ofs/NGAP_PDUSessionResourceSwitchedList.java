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

public class NGAP_PDUSessionResourceSwitchedList extends NGAP_SequenceOf<NGAP_PDUSessionResourceSwitchedItem> {

    public NGAP_PDUSessionResourceSwitchedList() {
        super();
    }

    public NGAP_PDUSessionResourceSwitchedList(List<NGAP_PDUSessionResourceSwitchedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSwitchedItem> getItemType() {
        return NGAP_PDUSessionResourceSwitchedItem.class;
    }
}
