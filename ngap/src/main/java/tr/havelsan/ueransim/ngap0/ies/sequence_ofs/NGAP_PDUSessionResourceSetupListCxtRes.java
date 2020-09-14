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

public class NGAP_PDUSessionResourceSetupListCxtRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemCxtRes> {

    public NGAP_PDUSessionResourceSetupListCxtRes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListCxtRes(List<NGAP_PDUSessionResourceSetupItemCxtRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtRes.class;
    }
}
