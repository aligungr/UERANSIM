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

public class NGAP_PDUSessionResourceSetupListSURes extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemSURes> {

    public NGAP_PDUSessionResourceSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListSURes(List<NGAP_PDUSessionResourceSetupItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSURes.class;
    }
}
