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

public class NGAP_PDUSessionResourceInformationList extends NGAP_SequenceOf<NGAP_PDUSessionResourceInformationItem> {

    public NGAP_PDUSessionResourceInformationList() {
        super();
    }

    public NGAP_PDUSessionResourceInformationList(List<NGAP_PDUSessionResourceInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceInformationItem> getItemType() {
        return NGAP_PDUSessionResourceInformationItem.class;
    }
}
