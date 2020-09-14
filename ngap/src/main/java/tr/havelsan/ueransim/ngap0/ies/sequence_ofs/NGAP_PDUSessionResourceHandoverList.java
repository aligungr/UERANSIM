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

public class NGAP_PDUSessionResourceHandoverList extends NGAP_SequenceOf<NGAP_PDUSessionResourceHandoverItem> {

    public NGAP_PDUSessionResourceHandoverList() {
        super();
    }

    public NGAP_PDUSessionResourceHandoverList(List<NGAP_PDUSessionResourceHandoverItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceHandoverList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceHandoverList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceHandoverItem> getItemType() {
        return NGAP_PDUSessionResourceHandoverItem.class;
    }
}
