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

public class NGAP_PDUSessionResourceToBeSwitchedDLList extends NgapSequenceOf<NGAP_PDUSessionResourceToBeSwitchedDLItem> {

    public NGAP_PDUSessionResourceToBeSwitchedDLList() {
        super();
    }

    public NGAP_PDUSessionResourceToBeSwitchedDLList(List<NGAP_PDUSessionResourceToBeSwitchedDLItem> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToBeSwitchedDLItem> getItemType() {
        return NGAP_PDUSessionResourceToBeSwitchedDLItem.class;
    }
}
