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

public class NGAP_PDUSessionResourceFailedToSetupListCxtFail extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> {

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail(List<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtFail.class;
    }
}
